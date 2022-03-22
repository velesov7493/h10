package h10.protocol.subunits;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.Parseable;

import java.util.Objects;

public class GsmEntry implements Parseable {

    private int localAreaCode;
    private int cellId;
    private int signalPower;

    public static GsmEntry of(int lac, int cellId, int signalPower) {
        GsmEntry result = new GsmEntry();
        result.localAreaCode = lac;
        result.cellId = cellId;
        result.signalPower = signalPower;
        return result;
    }

    @Override
    public void parse(String packet) {
        CompositeUnit cu = new StringCompositeUnit("\\|", 3);
        cu.parse(packet);
        localAreaCode = cu.getUnit(0).getAsInteger();
        cellId = cu.getUnit(1).getAsInteger();
        signalPower = cu.getUnit(2).getAsInteger();
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit("\\|", 3);
        cu.getUnit(0).setInteger(localAreaCode, 0);
        cu.getUnit(1).setInteger(cellId, 0);
        cu.getUnit(2).setInteger(signalPower, 0);
        return cu.toPacket();
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

    public int getSignalPower() {
        return signalPower;
    }

    public void setSignalPower(int signalPower) {
        this.signalPower = signalPower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GsmEntry gsmEntry = (GsmEntry) o;
        return
                localAreaCode == gsmEntry.localAreaCode
                && cellId == gsmEntry.cellId
                && signalPower == gsmEntry.signalPower;
    }

    @Override
    public int hashCode() {
        return Objects.hash(localAreaCode, cellId, signalPower);
    }
}