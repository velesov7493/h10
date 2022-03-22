package h10.protocol.packets;

import h10.protocol.components.StringSimpleUnit;
import h10.protocol.rules.Parseable;
import h10.protocol.rules.SimpleUnit;

public class BP05 implements Parseable {

    private int recordsCount;

    @Override
    public void parse(String packet) {
        SimpleUnit su = new StringSimpleUnit();
        su.parse(packet);
        recordsCount = su.getAsInteger();
    }

    @Override
    public String toPacket() {
        SimpleUnit su = new StringSimpleUnit();
        su.setInteger(recordsCount, 0);
        return "," + su.toPacket();
    }

    public int getRecordsCount() {
        return recordsCount;
    }

    public void setRecordsCount(int value) {
        recordsCount = value;
    }
}