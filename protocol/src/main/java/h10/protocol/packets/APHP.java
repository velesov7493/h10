package h10.protocol.packets;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.components.StringSimpleUnit;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.Parseable;
import h10.protocol.rules.SimpleUnit;

import java.util.List;

/**
 * APHP Upload heart rate, BP, SaO2, blood sugar（responds：BPHP）
 * APHP частота сердцебиения, давление крови, сатурация O2 крови, сахар крови
 * Ответ: BPHP
 */
public class APHP implements Parseable {

    private int heartRate;
    private int systolicPressure;
    private int diastolicPressure;
    private int saturationO2;
    private int bloodSugar;

    @Override
    public void parse(String packet) {
        StringCompositeUnit scu = new StringCompositeUnit(",", 5);
        scu.parse(packet);
        heartRate = scu.getUnit(0).getAsInteger();
        systolicPressure = scu.getUnit(1).getAsInteger();
        diastolicPressure = scu.getUnit(2).getAsInteger();
        saturationO2 = scu.getUnit(3).getAsInteger();
        bloodSugar = scu.getUnit(4).getAsInteger();
    }

    @Override
    public String toPacket() {
        StringCompositeUnit scu = new StringCompositeUnit(",", 5, true);
        scu.getUnit(0).setInteger(heartRate, 0);
        scu.getUnit(1).setInteger(systolicPressure, 0);
        scu.getUnit(2).setInteger(diastolicPressure, 0);
        scu.getUnit(3).setInteger(saturationO2, 0);
        scu.getUnit(4).setInteger(bloodSugar, 0);
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

    public int getSaturationO2() {
        return saturationO2;
    }

    public void setSaturationO2(int saturationO2) {
        this.saturationO2 = saturationO2;
    }

    public int getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(int bloodSugar) {
        this.bloodSugar = bloodSugar;
    }
}
