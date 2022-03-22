package h10.protocol.packets;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.components.StringSimpleUnit;
import h10.protocol.packets.defaults.DefaultAddressableJournalable;
import h10.protocol.rules.*;

import java.util.List;

/**
 * BP15	Установка периода позиционирования（Ответ：AP15）
 */
public class BP15 extends DefaultAddressableJournalable {

    private int minutes;

    @Override
    public void parse(String packet) {
        CompositeUnit cu = new StringCompositeUnit(",", 3);
        setImei(cu.getUnit(0).getAsString());
        setJournalNo(cu.getUnit(1).getAsInteger());
        minutes = cu.getUnit(2).getAsInteger();
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit(",", 3, true);
        cu.getUnit(0).setString(getImei());
        cu.getUnit(1).setInteger(getJournalNo(), 6);
        cu.getUnit(2).setInteger(minutes, 0);
        return cu.toPacket();
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int value) {
        minutes = value;
    }
}
