package h10.protocol.packets;

import h10.protocol.subunits.ContactEntry;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Pdu14Test {

    @Test
    public void whenApParse() {
        String in = "IWAP14,080835,D3590D54|135xxxxxxxxxx,D3590D54|135xxxxxxxxxx,D3590D54|135xxxxxxxxxx,D3590D54|135xxxxxxxxxx,D3590D54|135xxxxxxxxxx,D3590D54|135xxxxxxxxxx,D3590D54|135xxxxxxxxxx,D3590D54|135xxxxxxxxxx,D3590D54|135xxxxxxxxxx,D3590D54|135xxxxxxxxxx#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        AP14 content = pdu.getContent();
        ContactEntry ce = ContactEntry.of("姓名", "135xxxxxxxxxx");
        assertEquals(80835, content.getJournalNo());
        for (int i = 0; i < content.getContactsCount(); i++) {
            assertEquals(ce, content.getContact(i));
        }
    }

    @Test
    public void whenBpPack() {
        String expected = "IWBP14,353456789012345,080835,D3590D54|135xxxxxxxxxx,D3590D54|135xxxxxxxxxx,D3590D54|135xxxxxxxxxx,D3590D54|135xxxxxxxxxx,D3590D54|135xxxxxxxxxx,D3590D54|135xxxxxxxxxx,D3590D54|135xxxxxxxxxx,D3590D54|135xxxxxxxxxx,D3590D54|135xxxxxxxxxx,D3590D54|135xxxxxxxxxx#";
        BP14 content = new BP14();
        ProtocolDataUnit pdu = new ProtocolDataUnit("BP14", content);
        content.setImei("353456789012345");
        content.setJournalNo(80835);
        ContactEntry ce = ContactEntry.of("姓名", "135xxxxxxxxxx");
        for (int i = 0; i < 10; i++) {
            content.addContact(ce);
        }
        assertEquals(expected, pdu.toPacket());
    }
}
