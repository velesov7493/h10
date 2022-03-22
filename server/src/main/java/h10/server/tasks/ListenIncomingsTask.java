package h10.server.tasks;

import h10.protocol.components.Patterns;
import h10.protocol.packets.ProtocolDataUnit;
import h10.server.models.Device;
import h10.server.rules.Observe;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ListenIncomingsTask implements Runnable {

    private final Device device;
    private final Pattern ptUnit;

    public ListenIncomingsTask(Device dev) {
        ptUnit = Patterns.byName("ProtocolDataUnit");
        device = dev;
    }

    private ProtocolDataUnit dispatchPacket(ProtocolDataUnit inPdu) {

    }

    @Override
    public void run() {
        Socket s = device.getSocket();
        Observe<ProtocolDataUnit> out = device.getPacketHandler();
        while (!Thread.currentThread().isInterrupted() && s.isConnected()) {
            List<ProtocolDataUnit> packets = readPackets();
            packets.forEach((p) -> {
                ProtocolDataUnit outPdu = dispatchPacket(p);
                if (outPdu != null) {
                    sendPacket(outPdu);
                }
                out.receive(p);
            });
        }
    }
}
