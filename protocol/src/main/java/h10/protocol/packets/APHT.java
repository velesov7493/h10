package h10.protocol.packets;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.components.StringSimpleUnit;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.Parseable;
import h10.protocol.rules.SimpleUnit;

import java.util.List;

/**
 *  APHT  Upload heart rate and BP（responds：BPHT）
 *  APHT Пакет частоты сердцебиения и давления крови. Ответ - BPHT (без содержания)
 */
public class APHT implements Parseable {

    private int heartRate;
    private int systolicPressure;
    private int diastolicPressure;

    @Override
    public void parse(String packet) {
        StringCompositeUnit scu = new StringCompositeUnit(",", 3);
        scu.parse(packet);
        heartRate = scu.getUnit(0).getAsInteger();
        systolicPressure = scu.getUnit(1).getAsInteger();
        diastolicPressure = scu.getUnit(2).getAsInteger();
    }

    @Override
    public String toPacket() {
        StringCompositeUnit scu = new StringCompositeUnit(",", 3, true);
        scu.getUnit(0).setInteger(heartRate, 0);
        scu.getUnit(1).setInteger(systolicPressure, 0);
        scu.getUnit(2).setInteger(diastolicPressure, 0);
        return scu.toPacket();
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(int systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public int getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(int diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }
}
