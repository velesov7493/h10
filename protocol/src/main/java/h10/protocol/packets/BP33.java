package h10.protocol.packets;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.components.StringSimpleUnit;
import h10.protocol.packets.defaults.DefaultAddressableJournalable;
import h10.protocol.rules.*;
import h10.protocol.subunits.WorkingMode;

import java.util.List;

/**
 * BP33 Working Mode（respond：AP33）
 * Установка режима работы устройства
 * Ответ: AP33
 */
public class BP33 extends DefaultAddressableJournalable {

    private WorkingMode mode;

    @Override
    public void parse(String packet) {
        CompositeUnit cu = new StringCompositeUnit(",", 3);
        cu.parse(packet);
        setImei(cu.getUnit(0).getAsString());
        setJournalNo(cu.getUnit(1).getAsInteger());
        mode = WorkingMode.getByNumber(cu.getUnit(2).getAsInteger());
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit(",", 3, true);
        cu.getUnit(0).setString(getImei());
        cu.getUnit(1).setInteger(getJournalNo(), 6);
        cu.getUnit(2).setInteger(mode.getNumber(), 1);
        return cu.toPacket();
    }

    public WorkingMode getMode() {
        return mode;
    }

    public void setMode(WorkingMode mode) {
        this.mode = mode;
    }
}