package h10.protocol.packets;

import h10.protocol.subunits.RequestType;
import h10.protocol.subunits.VoiceEntry;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Pdu05Test {

    @Test
    public void whenApParse() {
        String in = "IWAP05,1#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        AP05 content  = pdu.getContent();
        assertEquals(RequestType.READY, content.getRequestType());
    }

    @Test
    public void whenBP05Pack() {
        String expected = "IWBP05,6#";
        BP05 content = new BP05();
        ProtocolDataUnit pdu = new ProtocolDataUnit("BP05", content);
        content.setRecordsCount(6);
        assertEquals(expected, pdu.toPacket());
    }

    @Test
    public void whenBPVLPack() {
        String expected = "IWBPVL,2,23421@12@2017-01-01 12:00:00|312343@3@2017-01-01 13:00:00#";
        BPVL content = new BPVL();
        ProtocolDataUnit pdu = new ProtocolDataUnit("BPVL", content);
        content
                .addVoice(VoiceEntry.of(23421, 12, LocalDateTime.of(2017, 1, 1, 12, 0, 0)))
                .addVoice(VoiceEntry.of(312343, 3, LocalDateTime.of(2017, 1, 1, 13, 0, 0)));
        assertEquals(expected, pdu.toPacket());
    }
}