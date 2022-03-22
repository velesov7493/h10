package h10.protocol.packets;

import h10.protocol.components.Patterns;
import h10.protocol.components.StrUtils;
import h10.protocol.components.StringCompositeUnit;
import h10.protocol.components.StringSimpleUnit;
import h10.protocol.packets.defaults.DefaultAddressableJournalable;
import h10.protocol.rules.*;
import h10.protocol.subunits.AlarmEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BP85 Set an alarm/reminder（respond：AP85）
 * BP85 Установка будильников/напоминаний
 * Ответ: AP85
 */
public class BP85 extends DefaultAddressableJournalable {

    private boolean allEnabled;

    private List<AlarmEntry> alarms;

    public BP85() {
        alarms = new ArrayList<>();
    }

    @Override
    public void parse(String packet) {
        String[] params = StrUtils.splitOnNthOccurrence(packet, ",", 4);
        CompositeUnit cu1 = new StringCompositeUnit(",", 4);
        cu1.parse(params[0]);
        setImei(cu1.getUnit(0).getAsString());
        setJournalNo(cu1.getUnit(1).getAsInteger());
        allEnabled = cu1.getUnit(2).getAsBoolean();
        int alarmsCount = cu1.getUnit(3).getAsInteger();
        CompositeUnit cu2 = new StringCompositeUnit("@", alarmsCount);
        cu2.parse(params[1]);
        alarms = CompositeUnit.readUnits(cu2, AlarmEntry.class);
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit(",", 4, true);
        cu.getUnit(0).setString(getImei());
        cu.getUnit(1).setInteger(getJournalNo(), 6);
        cu.getUnit(2).setBoolean(allEnabled);
        cu.getUnit(3).setInteger(alarms.size(), 0);
        return cu.toPacket() + "," + CompositeUnit.packAllUnits(alarms, "@");
    }

    public boolean isAllEnabled() {
        return allEnabled;
    }

    public void setAllEnabled(boolean allEnabled) {
        this.allEnabled = allEnabled;
    }

    public AlarmEntry getAlarm(int index) {
        return alarms.get(index);
    }

    public BP85 addAlarm(AlarmEntry value) {
        alarms.add(value);
        return this;
    }
}