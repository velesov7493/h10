package h10.protocol.subunits;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.Parseable;

import java.util.Objects;

public class DeviceStatus implements Parseable {

    private int gsmSignalLevel;
    private int gsmSattellitesCount;
    private int batteryLevel;
    private int reserved;
    private int fortificationState;
    private WorkingMode workingMode;

    @Override
    public void parse(String packet) {
        CompositeUnit scu = new StringCompositeUnit(new int[] {3, 3, 3, 1, 2, 2}, 6);
        scu.parse(packet);
        gsmSignalLevel = scu.getUnit(0).getAsInteger();
        gsmSattellitesCount = scu.getUnit(1).getAsInteger();
        batteryLevel = scu.getUnit(2).getAsInteger();
        reserved = scu.getUnit(3).getAsInteger();
        fortificationState = scu.getUnit(4).getAsInteger();
        workingMode = WorkingMode.getByNumber(scu.getUnit(5).getAsInteger());
    }

    @Override
    public String toPacket() {
        CompositeUnit scu = new StringCompositeUnit(new int[] {3, 3, 3, 1, 2, 2}, 6);
        scu.getUnit(0).setInteger(gsmSignalLevel, 3);
        scu.getUnit(1).setInteger(gsmSattellitesCount, 3);
        scu.getUnit(2).setInteger(batteryLevel, 3);
        scu.getUnit(3).setInteger(reserved, 1);
        scu.getUnit(4).setInteger(fortificationState, 2);
        scu.getUnit(5).setInteger(workingMode.getNumber(), 2);
        return scu.toPacket();
    }

    public int getGsmSignalLevel() {
        return gsmSignalLevel;
    }

    public void setGsmSignalLevel(int gsmSignalLevel) {
        this.gsmSignalLevel = gsmSignalLevel;
    }

    public int getGsmSattellitesCount() {
        return gsmSattellitesCount;
    }

    public void setGsmSattellitesCount(int gsmSattellitesCount) {
        this.gsmSattellitesCount = gsmSattellitesCount;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public int getFortificationState() {
        return fortificationState;
    }

    public void setFortificationState(int fortificationState) {
        this.fortificationState = fortificationState;
    }

    public WorkingMode getWorkingMode() {
        return workingMode;
    }

    public void setWorkingMode(WorkingMode workingMode) {
        this.workingMode = workingMode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeviceStatus status = (DeviceStatus) o;
        return
                gsmSignalLevel == status.gsmSignalLevel
                && gsmSattellitesCount == status.gsmSattellitesCount
                && batteryLevel == status.batteryLevel
                && fortificationState == status.fortificationState
                && workingMode == status.workingMode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gsmSignalLevel, gsmSattellitesCount, batteryLevel, fortificationState, workingMode);
    }
}