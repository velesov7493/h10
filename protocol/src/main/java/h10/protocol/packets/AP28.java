package h10.protocol.packets;

import h10.protocol.components.HexString;
import h10.protocol.components.StringCompositeUnit;
import h10.protocol.packets.defaults.DefaultJournalable;
import h10.protocol.rules.*;

import java.util.List;

/**
 * AP28 - ответ на аудио-пакет BP28
 * Вместо journalNo здесь можно поставить любой другой параметр любого типа
 */

public class AP28 extends DefaultJournalable implements Sequenced {

    private String senderName;
    private int totalPackets;
    private int number;
    private boolean success;

    @Override
    public void parse(String packet) {
        CompositeUnit cu = new StringCompositeUnit(",", 5);
        cu.parse(packet);
        senderName = HexString.decode(cu.getUnit(0).getAsString(), true);
        setJournalNo(cu.getUnit(1).getAsInteger());
        totalPackets = cu.getUnit(2).getAsInteger();
        number = cu.getUnit(3).getAsInteger();
        success = cu.getUnit(4).getAsBoolean();
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit(",", 5, true);
        cu.getUnit(0).setString(HexString.encode(senderName, true));
        cu.getUnit(1).setInteger(getJournalNo(), 6);
        cu.getUnit(2).setInteger(totalPackets, 0);
        cu.getUnit(3).setInteger(number, 0);
        cu.getUnit(4).setBoolean(success);
        return cu.toPacket();
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void setNumber(int value) {
        number = value;
    }

    @Override
    public int getTotalPackets() {
        return totalPackets;
    }

    @Override
    public void setTotalPackets(int value) {
        totalPackets = value;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}