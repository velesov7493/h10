package h10.server.tasks;

import h10.protocol.components.Patterns;
import h10.protocol.packets.ProtocolDataUnit;
import h10.server.components.Device;
import h10.server.components.ProtocolDispatcher;
import h10.server.rules.Dispatcher;
import h10.server.rules.Observe;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.regex.Pattern;

public class ListenIncomingsTask implements Runnable {

    private final Device device;
    private final Dispatcher dispatcher;

    public ListenIncomingsTask(Device dev) {
        device = dev;
        dispatcher = new ProtocolDispatcher(device);
    }

    @Override
    public void run() {
        Socket s = device.getSocket();
        Observe<ProtocolDataUnit> outSlot = device.getPacketHandler();
        try (
                InputStream in = s.getInputStream();
                OutputStream out = s.getOutputStream();
        ) {
            while (!Thread.currentThread().isInterrupted() && s.isConnected()) {
                List<ProtocolDataUnit> packets = dispatcher.readPackets(in);
                packets.forEach((p) -> {
                    ProtocolDataUnit outPdu = dispatcher.dispatchPacket(p);
                    if (outPdu != null) {
                        dispatcher.sendPacket(out, outPdu);
                    }
                    outSlot.receive(p);
                });
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
