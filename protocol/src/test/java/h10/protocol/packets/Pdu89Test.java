package h10.protocol.packets;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Pdu89Test {

    @Test
    public void whenApParse() {
        String in = "IWAP89,080835#";
        ProtocolDataUnit pdu = new ProtocolDataUnit("AP89");
        pdu.parse(in);
        AP89 content = pdu.getContent();
        assertEquals(80835, content.getJournalNo());
    }

    @Test
    public void whenBpPack() {
        String expected = "IWBP89,353456789012345,080835,50,90,80,90,110,140#";
        BP89 content = new BP89();
        ProtocolDataUnit pdu = new ProtocolDataUnit("BP89", content);
        content.setImei("353456789012345");
        content.setJournalNo(80835);
        content.setHeartRateLow(50);
        content.setHeartRateHigh(90);
        content.setDiastolicBpLow(80);
        content.setDiastolicBpHigh(90);
        content.setSystolicBpLow(110);
        content.setSystolicBpHigh(140);
        assertEquals(expected, pdu.toPacket());
    }
}
