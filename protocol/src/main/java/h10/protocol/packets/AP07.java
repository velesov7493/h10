package h10.protocol.packets;

import h10.protocol.components.StrUtils;
import h10.protocol.components.StringCompositeUnit;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.Parseable;
import h10.protocol.rules.Sequenced;
import h10.protocol.rules.VoiceData;

import java.time.LocalDateTime;

/**
 * AP07 Upload audio message（responds:BP07)
 * AP07 Загрузка аудио сообщения (в формате AMR)
 * Ответ: BP07
 */
public class AP07 implements Parseable, Sequenced, VoiceData {

    private LocalDateTime dateTime;
    private int totalPackets;
    private int number;
    private byte[] audio;

    @Override
    public void parse(String packet) {
        CompositeUnit cu = new StringCompositeUnit(",", 4);
        String[] params = StrUtils.splitOnNthOccurrence(packet, ",", 4);
        cu.parse(params[0]);
        dateTime = cu.getUnit(0).getAsDatetime("yyyyMMddHHmmss");
        totalPackets = cu.getUnit(1).getAsInteger();
        number = cu.getUnit(2).getAsInteger();
        audio = params[1].getBytes();
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit(",", 5, true);
        cu.getUnit(0).setDatetime(dateTime, "yyyyMMddHHmmss");
        cu.getUnit(1).setInteger(totalPackets, 0);
        cu.getUnit(2).setInteger(number, 0);
        cu.getUnit(3).setInteger(audio.length, 0);
        cu.getUnit(4).setBytes(audio);
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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