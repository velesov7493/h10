package h10.protocol.packets;

import h10.protocol.components.StrUtils;
import h10.protocol.components.StringCompositeUnit;
import h10.protocol.packets.defaults.DefaultJournalable;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.subunits.ContactEntry;

import java.util.List;

public class AP14 extends DefaultJournalable {

    private List<ContactEntry> contacts;

    @Override
    public void parse(String packet) {
        int unitsCount = StrUtils.countOfElements(packet, ",") + 1;
        CompositeUnit cu = new StringCompositeUnit(",", unitsCount);
        cu.parse(packet);
        setJournalNo(cu.getUnit(0).getAsInteger());
        contacts = CompositeUnit.readUnits(cu, 1, unitsCount - 1, ContactEntry.class);
    }

    @Override
    public String toPacket() {
        int unitsCount = contacts.size() + 1;
        CompositeUnit cu = new StringCompositeUnit(",", unitsCount);
        cu.getUnit(0).setInteger(getJournalNo(), 6);
        CompositeUnit.writeUnits(contacts, cu, 1);
        return cu.toPacket();
    }

    public ContactEntry getContact(int index) {
        return contacts.get(index);
    }

    public int getContactsCount() {
        return contacts.size();
    }

    public AP14 addContact(ContactEntry value) {
        if (contacts.size() >= 10) {
            throw new IllegalStateException("Можно добавить не более 10 контактов!");
        }
        contacts.add(value);
        return this;
    }
}