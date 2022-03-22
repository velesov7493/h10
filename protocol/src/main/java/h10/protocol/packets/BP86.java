package h10.protocol.packets;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.components.StringSimpleUnit;
import h10.protocol.packets.defaults.DefaultAddressableJournalable;
import h10.protocol.rules.*;
import h10.protocol.subunits.TestingType;

import java.util.List;

/**
 * BP86 Set the interval of heart rate auto testing（respond：AP86）
 * BP86 Установка интервала автоизмерения сердцебиения или давления
 * Ответ: AP86
 */
public class BP86 extends DefaultAddressableJournalable {

    private TestingType testingType;
    private int minutes;

    @Override
    public void parse(String packet) {
        CompositeUnit cu = new StringCompositeUnit(",", 4);
        cu.parse(packet);
        setImei(cu.getUnit(0).getAsString());
        setJournalNo(cu.getUnit(1).getAsInteger());
        testingType = TestingType.getByNumber(cu.getUnit(2).getAsInteger());
        minutes = cu.getUnit(3).getAsInteger();
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit(",", 4);
        cu.getUnit(0).setString(getImei());
        cu.getUnit(1).setInteger(getJournalNo(), 6);
        cu.getUnit(2).setInteger(testingType.getNumber(), 1);
        cu.getUnit(3).setInteger(minutes, 0);
        return cu.toPacket();
    }

    public TestingType getTestingType() {
        return testingType;
    }

    public void setTestingType(TestingType testingType) {
        this.testingType = testingType;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
