package h10.protocol.packets;

import h10.protocol.components.HexString;
import h10.protocol.components.StrUtils;
import h10.protocol.components.StringCompositeUnit;
import h10.protocol.components.StringSimpleUnit;
import h10.protocol.packets.defaults.DefaultJournalable;
import h10.protocol.rules.*;

import java.util.List;

/**
 * BP28 Send audio message to device（responds:AP28）
 * BP28 Отправить аудио-сообщение на устройство (в формате AMR)
 * Ответ: AP28
 *
 * Вместо journalNo здесь можно поставить любой другой параметр любого типа
 */
public class BP28 extends DefaultJournalable implements Sequenced, VoiceData {

    private String senderName;
    private int totalPackets;
    private int number;
    private byte[] audio;

    @Override
    public void parse(String packet) {
        String[] params = StrUtils.splitOnNthOccurrence(packet, ",", 5);
        CompositeUnit cu = new StringCompositeUnit(",", 5);
        cu.parse(params[0]);
        senderName = HexString.decode(cu.getUnit(0).getAsString(), true);
        setJournalNo(cu.getUnit(1).getAsInteger());
        totalPackets = cu.getUnit(2).getAsInteger();
        number = cu.getUnit(3).getAsInteger();
        int audioLength = cu.getUnit(4).getAsInteger();
        audio = params[1].getBytes();
        if (audioLength != audio.length) {
            throw new IllegalStateException("Размер аудио не соответствует данным пакета!");
        }
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit(",", 6, true);
        cu.getUnit(0).setString(HexString.encode(senderName, true));
        cu.getUnit(1).setInteger(getJournalNo(), 6);
        cu.getUnit(2).setInteger(totalPackets, 0);
        cu.getUnit(3).setInteger(number, 0);
        cu.getUnit(4).setInteger(audio.length, 0);
        cu.getUnit(5).setBytes(audio);
        return cu.toPacket();
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
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

    @Override
    public byte[] getAudio() {
        return audio;
    }

    @Override
    public void setAudio(byte[] data) {
        if (data.length > 1024) {
            throw new IllegalArgumentException(
                "Размер аудиоданных в пакете (" + data.length + "Б) не должен превышать 1кБ!"
            );
        }
        audio = data;
    }
}