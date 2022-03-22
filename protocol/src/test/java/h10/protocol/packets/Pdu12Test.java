package h10.protocol.packets;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Pdu12Test {

    @Test
    public void whenApParse() {
        String in = "IWAP12,080835,13512345678,13591011121,13531415161#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        AP12 content = pdu.getContent();
        List<String> phones = content.getSosPhones();
        assertEquals(80835, content.getJournalNo());
        assertEquals("13512345678", phones.get(0));
        assertEquals("13591011121", phones.get(1));
        assertEquals("13531415161", phones.get(2));
    }

    @Test
    public void whenBpPack() {
        String expected = "IWBP12,353456789012345,080835,135XXXXXXXX,135XXXXXXXX,135XXXXXXXX#";
        BP12 content = new BP12();
        ProtocolDataUnit pdu = new ProtocolDataUnit("BP12", content);
        content.setImei("353456789012345");
        content.setJournalNo(80835);
        content
                .addSosPhone("135XXXXXXXX")
                .addSosPhone("135XXXXXXXX")
                .addSosPhone("135XXXXXXXX");
        assertEquals(expected, pdu.toPacket());
    }
}
