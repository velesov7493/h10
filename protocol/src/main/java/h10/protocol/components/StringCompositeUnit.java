package h10.protocol.components;

import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.SimpleUnit;

import java.util.List;

public class StringCompositeUnit implements CompositeUnit {

    private List<SimpleUnit> units;
    private boolean havePrefix;
    private String splitter;
    private int[] lengths;

    public StringCompositeUnit(String aSplitter, int unitsCount) {
        splitter = aSplitter;
        havePrefix = true;
        units = StringSimpleUnit.generateNewUnits(unitsCount);
    }

    public StringCompositeUnit(int[] unitLengths, int unitsCount) {
        lengths = unitLengths;
        havePrefix = false;
        units = StringSimpleUnit.generateNewUnits(unitsCount);
    }

    public StringCompositeUnit(String aSplitter, int unitsCount, boolean aHavePrefix) {
        splitter = aSplitter;
        havePrefix = aHavePrefix;
        units = StringSimpleUnit.generateNewUnits(unitsCount);
    }

    @Override
    public String getSplitter() {
        return splitter;
    }

    @Override
    public int[] getLengths() {
        return lengths;
    }

    @Override
    public String toPacket() {
        return
                havePrefix
                ? splitter + CompositeUnit.packAll(this)
                : CompositeUnit.packAll(this);
    }

    @Override
    public int getSize() {
        return units.size();
    }

    @Override
    public SimpleUnit getUnit(int index) {
        return units.get(index);
    }
}