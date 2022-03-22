package h10.protocol.packets;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.components.StringSimpleUnit;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.Parseable;
import h10.protocol.rules.Sequenced;
import h10.protocol.rules.SimpleUnit;

import java.time.LocalDateTime;
import java.util.List;

public class BP07 implements Parseable, Sequenced {

    private LocalDateTime dateTime;
    private int totalPackets;
    private int number;
    private boolean success;

    @Override
    public void parse(String packet) {
        CompositeUnit cu = new StringCompositeUnit(",", 4);
        cu.parse(packet);
        dateTime = cu.getUnit(0).getAsDatetime("yyyyMMddHHmmss");
        totalPackets = cu.getUnit(1).getAsInteger();
        number = cu.getUnit(2).getAsInteger();
        success = cu.getUnit(3).getAsBoolean();
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit(",", 4, true);
        cu.getUnit(0).setDatetime(dateTime, "yyyyMMddHHmmss");
        cu.getUnit(1).setInteger(totalPackets, 0);
        cu.getUnit(2).setInteger(number, 0);
        cu.getUnit(3).setBoolean(success);
        return cu.toPacket();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int getTotalPackets() {
        return totalPackets;
    }

    @Override
    public void setTotalPackets(int totalPackets) {
        this.totalPackets = totalPackets;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}