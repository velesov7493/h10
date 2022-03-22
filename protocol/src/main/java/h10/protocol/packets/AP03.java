package h10.protocol.packets;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.Parseable;
import h10.protocol.subunits.DeviceStatus;

import java.util.List;

/**
 * AP03 - Пакет статуса устройства. Отправляется устройством на сервер каждые 180 секунд
 * Ответный пакет - BP03 (без содержимого)
 */
public class AP03 implements Parseable {

    private DeviceStatus status;
    private int stepsCount;
    private int rollsFrequency;

    public AP03() {
        status = new DeviceStatus();
    }

    @Override
    public void parse(String packet) {
        CompositeUnit cu = new StringCompositeUnit(",", 3);
        cu.parse(packet);
        status.parse(cu.getUnit(0).getAsString());
        stepsCount = cu.getUnit(1).getAsInteger();
        rollsFrequency = cu.getUnit(2).getAsInteger();
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit(",", 3, true);
        cu.getUnit(0).setString(status.toPacket());
        cu.getUnit(1).setInteger(stepsCount, 0);
        cu.getUnit(2).setInteger(rollsFrequency, 0);
        return cu.toPacket();
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }

    public int getStepsCount() {
        return stepsCount;
    }

    public void setStepsCount(int stepsCount) {
        this.stepsCount = stepsCount;
    }

    public int getRollsFrequency() {
        return rollsFrequency;
    }

    public void setRollsFrequency(int rollsFrequency) {
        this.rollsFrequency = rollsFrequency;
    }
}