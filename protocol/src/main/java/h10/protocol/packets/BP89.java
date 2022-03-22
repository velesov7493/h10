package h10.protocol.packets;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.components.StringSimpleUnit;
import h10.protocol.packets.defaults.DefaultAddressableJournalable;
import h10.protocol.rules.*;

import java.util.List;

/**
 * BP89 - установка пределов частоты пульса и давления.
 * Ответ: AP89
 */
public class BP89 extends DefaultAddressableJournalable {

    private int heartRateLow;
    private int heartRateHigh;
    private int diastolicBpLow;
    private int diastolicBpHigh;
    private int systolicBpLow;
    private int systolicBpHigh;

    @Override
    public void parse(String packet) {
        CompositeUnit cu = new StringCompositeUnit(",", 8);
        cu.parse(packet);
        setImei(cu.getUnit(0).getAsString());
        setJournalNo(cu.getUnit(1).getAsInteger());
        heartRateLow = cu.getUnit(2).getAsInteger();
        heartRateHigh = cu.getUnit(3).getAsInteger();
        diastolicBpLow = cu.getUnit(4).getAsInteger();
        diastolicBpHigh = cu.getUnit(5).getAsInteger();
        systolicBpLow = cu.getUnit(6).getAsInteger();
        systolicBpHigh = cu.getUnit(7).getAsInteger();
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit(",", 8);
        cu.getUnit(0).setString(getImei());
        cu.getUnit(1).setInteger(getJournalNo(), 6);
        cu.getUnit(2).setInteger(heartRateLow, 0);
        cu.getUnit(3).setInteger(heartRateHigh, 0);
        cu.getUnit(4).setInteger(diastolicBpLow, 0);
        cu.getUnit(5).setInteger(diastolicBpHigh, 0);
        cu.getUnit(6).setInteger(systolicBpLow, 0);
        cu.getUnit(7).setInteger(systolicBpHigh, 0);
        return cu.toPacket();
    }

    public int getHeartRateLow() {
        return heartRateLow;
    }

    public void setHeartRateLow(int heartRateLow) {
        this.heartRateLow = heartRateLow;
    }

    public int getHeartRateHigh() {
        return heartRateHigh;
    }

    public void setHeartRateHigh(int heartRateHigh) {
        this.heartRateHigh = heartRateHigh;
    }

    public int getDiastolicBpLow() {
        return diastolicBpLow;
    }

    public void setDiastolicBpLow(int diastolicBpLow) {
        this.diastolicBpLow = diastolicBpLow;
    }

    public int getDiastolicBpHigh() {
        return diastolicBpHigh;
    }

    public void setDiastolicBpHigh(int diastolicBpHigh) {
        this.diastolicBpHigh = diastolicBpHigh;
    }

    public int getSystolicBpLow() {
        return systolicBpLow;
    }

    public void setSystolicBpLow(int systolicBpLow) {
        this.systolicBpLow = systolicBpLow;
    }

    public int getSystolicBpHigh() {
        return systolicBpHigh;
    }

    public void setSystolicBpHigh(int systolicBpHigh) {
        this.systolicBpHigh = systolicBpHigh;
    }
}