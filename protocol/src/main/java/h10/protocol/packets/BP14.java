package h10.protocol.packets;

import h10.protocol.components.StrUtils;
import h10.protocol.components.StringCompositeUnit;
import h10.protocol.components.StringSimpleUnit;
import h10.protocol.packets.defaults.DefaultAddressableJournalable;
import h10.protocol.rules.*;
import h10.protocol.subunits.ContactEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * 	BP14 Set white list(Phone book) numbers(ten)（respond：AP14）
 * 	BP14 Записать белый список контактов на устройство (до 10-и номеров)
 * 	Ответ - AP14
 */
public class BP14 extends DefaultAddressableJournalable {

    private List<ContactEntry> contacts;

    public BP14() {
        contacts = new ArrayList<>();
    }

    @Override
    public void parse(String packet) {
        int unitsCount = StrUtils.countOfElements(packet, ",") + 1;
        CompositeUnit cu = new StringCompositeUnit(",", unitsCount);
        cu.parse(packet);
        setImei(cu.getUnit(0).getAsString());
        setJournalNo(cu.getUnit(1).getAsInteger());
        contacts = CompositeUnit.readUnits(cu, 2, unitsCount - 2, ContactEntry.class);
    }

    @Override
    public String toPacket() {
        int unitsCount = contacts.size() + 2;
        CompositeUnit cu = new StringCompositeUnit(",", unitsCount, true);
        cu.getUnit(0).setString(getImei());
        cu.getUnit(1).setInteger(getJournalNo(), 6);
        CompositeUnit.writeUnits(contacts, cu, 2);
        return cu.toPacket();
    }

    public ContactEntry getContact(int index) {
        return contacts.get(index);
    }

    public BP14 addContact(ContactEntry value) {
        if (contacts.size() >= 10) {
            throw new IllegalStateException("Можно добавить не более 10 контактов!");
        }
        contacts.add(value);
        return this;
    }
}