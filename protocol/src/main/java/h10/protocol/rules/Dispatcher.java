package h10.protocol.rules;

import h10.protocol.packets.ProtocolDataUnit;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface Dispatcher {

    ProtocolDataUnit dispatchPacket(ProtocolDataUnit inPdu);
    List<ProtocolDataUnit> readPackets(InputStream in);
    void sendPacket(OutputStream out, ProtocolDataUnit pdu);
}
