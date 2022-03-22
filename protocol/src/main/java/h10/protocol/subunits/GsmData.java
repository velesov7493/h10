package h10.protocol.subunits;

import h10.protocol.components.StrUtils;
import h10.protocol.components.StringCompositeUnit;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.Parseable;

import java.util.ArrayList;
import java.util.List;

public class GsmData implements Parseable {

    private int mobileCountryCode;
    private int mobileNetworkCode;
    private List<GsmEntry> bases;

    public GsmData() {
        bases = new ArrayList<>();
    }

    @Override
    public void parse(String packet) {
        CompositeUnit cu = new StringCompositeUnit(",", 3);
        String[] params = StrUtils.splitOnNthOccurrence(packet, ",", 3);
        cu.parse(params[0]);
        int basesCount = cu.getUnit(0).getAsInteger();
        mobileCountryCode = cu.getUnit(1).getAsInteger();
        mobileNetworkCode = cu.getUnit(2).getAsInteger();
        cu = new StringCompositeUnit(",", basesCount);
        cu.parse(params[1]);
        bases = CompositeUnit.readUnits(cu, GsmEntry.class);
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit(",", 3 + bases.size());
        cu.getUnit(0).setInteger(bases.size(), 0);
        cu.getUnit(1).setInteger(mobileCountryCode, 3);
        cu.getUnit(2).setInteger(mobileNetworkCode, 0);
        CompositeUnit.writeUnits(bases, cu, 3);
        return cu.toPacket();
    }

    public int getBasesCount() {
        return bases.size();
    }

    public int getMobileCountryCode() {
        return mobileCountryCode;
    }

    public void setMobileCountryCode(int mobileCountryCode) {
        this.mobileCountryCode = mobileCountryCode;
    }

    public int getMobileNetworkCode() {
        return mobileNetworkCode;
    }

    public boolean isMoving() {
        return mobileNetworkCode == 0;
    }

    public void setMobileNetworkCode(int mobileNetworkCode) {
        this.mobileNetworkCode = mobileNetworkCode;
    }

    public GsmEntry getBase(int index) {
        return bases.get(index);
    }

    public GsmData addBase(GsmEntry value) {
        bases.add(value);
        return this;
    }
}