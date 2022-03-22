package h10.protocol.packets;

import h10.protocol.components.StrUtils;
import h10.protocol.components.StringCompositeUnit;
import h10.protocol.packets.defaults.DefaultJournalable;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.subunits.AlarmEntry;

import java.util.ArrayList;
import java.util.List;

public class AP85 extends DefaultJournalable {

    private boolean isAllEnabled;
    private List<AlarmEntry> alarms;

    public AP85() {
        alarms = new ArrayList<>();
    }

    @Override
    public void parse(String packet) {
        CompositeUnit cu = new StringCompositeUnit(",", 3);
        String[] msg = StrUtils.splitOnNthOccurrence(packet, ",", 3);
        cu.parse(msg[0]);
        setJournalNo(cu.getUnit(0).getAsInteger());
        isAllEnabled = cu.getUnit(1).getAsBoolean();
        int alarmsCount = cu.getUnit(2).getAsInteger();
        CompositeUnit cu1 = new StringCompositeUnit("@", alarmsCount);
        cu1.parse(msg[1]);
        alarms = CompositeUnit.readUnits(cu1, AlarmEntry.class);
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit(",", 4, true);
        cu.getUnit(0).setInteger(getJournalNo(), 6);
        cu.getUnit(1).setBoolean(isAllEnabled);
        cu.getUnit(2).setInteger(alarms.size(), 0);
        cu.getUnit(3).setString(CompositeUnit.packAllUnits(alarms, "@"));
        return cu.toPacket();
    }

    public boolean isAllEnadled() {
        return isAllEnabled;
    }

    public void setAllEnabled(boolean value) {
        isAllEnabled = value;
    }

    private int getAlarmsCount() {
        return alarms.size();
    }

    public AlarmEntry getAlarm(int index) {
        return alarms.get(index);
    }

    public AP85 addAlarm(AlarmEntry value) {
        alarms.add(value);
        return this;
    }
}