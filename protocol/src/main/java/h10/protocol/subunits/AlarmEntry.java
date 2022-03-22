package h10.protocol.subunits;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.components.StringSimpleUnit;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.Parseable;
import h10.protocol.rules.SimpleUnit;

import java.time.LocalTime;
import java.util.List;

public class AlarmEntry implements Parseable {

    private LocalTime time;
    private int[] daysOfWeek;
    private boolean enabled;
    private ReminderType type;

    @Override
    public void parse(String packet) {
        StringCompositeUnit scu = new StringCompositeUnit(",",4, false);
        scu.parse(packet);
        time = scu.getUnit(0).getAsTime("HHmm");
        daysOfWeek = scu.getUnit(1).getAsIntArray();
        enabled = scu.getUnit(2).getAsBoolean();
        type = ReminderType.getByNumber(scu.getUnit(3).getAsInteger());
    }

    @Override
    public String toPacket() {
        StringCompositeUnit scu = new StringCompositeUnit(",",4, false);
        scu.getUnit(0).setTime(time, "HHmm");
        scu.getUnit(1).setIntArray(daysOfWeek);
        scu.getUnit(2).setBoolean(enabled);
        scu.getUnit(3).setInteger(type.getNumber(), 1);
        return scu.toPacket();
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int[] getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(int[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public ReminderType getType() {
        return type;
    }

    public void setType(ReminderType type) {
        this.type = type;
    }
}