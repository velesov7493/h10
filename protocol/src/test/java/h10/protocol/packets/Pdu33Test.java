package h10.protocol.packets;

import h10.protocol.subunits.WorkingMode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Pdu33Test {

    @Test
    public void whenApParse() {
        String in = "IWAP33,080835,1#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        AP33 content = pdu.getContent();
        assertEquals(80835, content.getJournalNo());
        assertEquals(WorkingMode.NORMAL, content.getWorkingMode());
    }

    @Test
    public void whenBpPack() {
        String expected = "IWBP33,353456789012345,080835,1#";
        BP33 content = new BP33();
        ProtocolDataUnit pdu = new ProtocolDataUnit("BP33", content);
        content.setImei("353456789012345");
        content.setJournalNo(80835);
        content.setMode(WorkingMode.NORMAL);
        assertEquals(expected, pdu.toPacket());
    }
}
