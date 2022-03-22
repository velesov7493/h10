package h10.protocol.packets;

import h10.protocol.subunits.LbsData;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class PduTMTest {

    @Test
    public void whenApParse() {
        String in = "IWAPTM,460,0,9750,3613#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        APTM content = pdu.getContent();
        assertEquals(460, content.getMobileCountryCode());
        assertEquals(0, content.getMobileNetworkCode());
        assertEquals(9750, content.getLocalAreaCode());
        assertEquals(3613, content.getCellId());
    }

    @Test
    public void whenBpPack() {
        String expected = "IWBPTM,20160225090909,22.123456,113.654321#";
        BPTM content = new BPTM();
        ProtocolDataUnit pdu = new ProtocolDataUnit("BPTM", content);
        content.setDateTime(LocalDateTime.of(2016, 2, 25, 9, 9, 9));
        content.setLatitude(22.123456);
        content.setLongitude(113.654321);
        assertEquals(expected, pdu.toPacket());
    }
}
