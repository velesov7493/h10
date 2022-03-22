package h10.protocol.subunits;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.components.StringSimpleUnit;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.Parseable;
import h10.protocol.rules.SimpleUnit;

import java.util.List;
import java.util.Objects;

public class LbsData implements Parseable {

    private int mobileCountryCode;
    private int mobileNetworkCode;
    private int localAreaCode;
    private int cellId;

    @Override
    public void parse(String packet) {
        CompositeUnit cu = new StringCompositeUnit(",", 4);
        cu.parse(packet);
        mobileCountryCode = cu.getUnit(0).getAsInteger();
        mobileNetworkCode = cu.getUnit(1).getAsInteger();
        localAreaCode = cu.getUnit(2).getAsInteger();
        cellId = cu.getUnit(3).getAsInteger();
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit(",", 4, false);
        cu.getUnit(0).setInteger(mobileCountryCode, 0);
        cu.getUnit(1).setInteger(mobileNetworkCode, 0);
        cu.getUnit(2).setInteger(localAreaCode, 0);
        cu.getUnit(3).setInteger(cellId, 0);
        return cu.toPacket();
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

    public void setMobileNetworkCode(int mobileNetworkCode) {
        this.mobileNetworkCode = mobileNetworkCode;
    }

    public int getLocalAreaCode() {
        return localAreaCode;
    }

    public void setLocalAreaCode(int localAreaCode) {
        this.localAreaCode = localAreaCode;
    }

    public int getCellId() {
        return cellId;
    }

    public void setCellId(int cellId) {
        this.cellId = cellId;
    }

    public boolean isMoving() {
        return mobileNetworkCode == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LbsData lbsData = (LbsData) o;
        return
                mobileCountryCode == lbsData.mobileCountryCode
                && mobileNetworkCode == lbsData.mobileNetworkCode
                && localAreaCode == lbsData.localAreaCode
                && cellId == lbsData.cellId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mobileCountryCode, mobileNetworkCode, localAreaCode, cellId);
    }
}
