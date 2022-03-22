package h10.protocol.packets;

import h10.protocol.components.StrUtils;
import h10.protocol.components.StringCompositeUnit;
import h10.protocol.components.StringSimpleUnit;
import h10.protocol.packets.defaults.DefaultAddressableJournalable;
import h10.protocol.rules.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * BP12 Set sos numbers(three)（respond：AP12）
 * BP12 - установка экстренных номеров
 * Ответ - AP12
 */
public class BP12 extends DefaultAddressableJournalable {

    private List<String> phones;

    public BP12() {
        phones = new ArrayList<>();
    }

    @Override
    public void parse(String packet) {
        String[] params = StrUtils.splitOnNthOccurrence(packet, ",", 2);
        CompositeUnit cu = new StringCompositeUnit(",", 2);
        cu.parse(params[0]);
        setImei(cu.getUnit(0).getAsString());
        setJournalNo(cu.getUnit(1).getAsInteger());
        phones = Arrays.asList(params[1].split(","));
    }

    @Override
    public String toPacket() {
        int unitsCount = phones.size() + 2;
        CompositeUnit cu = new StringCompositeUnit(",", unitsCount);
        cu.getUnit(0).setString(getImei());
        cu.getUnit(1).setInteger(getJournalNo(), 6);
        for (int i = 0; i < phones.size(); i++) {
           cu.getUnit(i + 2).setString(phones.get(i));
        }
        return cu.toPacket();
    }

    public String getSosPhone(int index) {
        return phones.get(index);
    }

    public BP12 addSosPhone(String value) {
        phones.add(value);
        return this;
    }
}