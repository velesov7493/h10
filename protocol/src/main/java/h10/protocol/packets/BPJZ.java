package h10.protocol.packets;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.components.StringSimpleUnit;
import h10.protocol.packets.defaults.DefaultAddressableJournalable;
import h10.protocol.rules.*;

import java.util.List;

/**
 * BPJZ  Blood pressure calibration（respond：APJZ）
 * BPJZ калибровка измерений давления крови
 * Ответ: APJZ
 */
public class BPJZ extends DefaultAddressableJournalable {

    private int systolicBp;
    private int diastolicBp;

    @Override
    public void parse(String packet) {
        CompositeUnit cu = new StringCompositeUnit(",", 4);
        setImei(cu.getUnit(0).getAsString());
        setJournalNo(cu.getUnit(1).getAsInteger());
        systolicBp = cu.getUnit(2).getAsInteger();
        diastolicBp = cu.getUnit(3).getAsInteger();
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit(",", 4, true);
        cu.getUnit(0).setString(getImei());
        cu.getUnit(1).setInteger(getJournalNo(), 6);
        cu.getUnit(2).setInteger(systolicBp, 0);
        cu.getUnit(3).setInteger(diastolicBp, 0);
        return cu.toPacket();
    }

    public int getSystolicBp() {
        return systolicBp;
    }

    public void setSystolicBp(int systolicBp) {
        this.systolicBp = systolicBp;
    }

    public int getDiastolicBp() {
        return diastolicBp;
    }

    public void setDiastolicBp(int diastolicBp) {
        this.diastolicBp = diastolicBp;
    }
}
