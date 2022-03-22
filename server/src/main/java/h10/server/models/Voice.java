package h10.server.models;

import h10.protocol.packets.AP07;
import h10.protocol.packets.BP28;
import h10.protocol.subunits.VoiceEntry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Voice {

    private int id;
    private String imei;
    private int duration;
    private LocalDateTime dateTime;
    private byte[] data;

    private int packetsCount(int dataLength) {
        return (int) Math.ceil((double) dataLength / 1024);
    }

    public static Voice fromIncomeSequence(List<AP07> sequence) {
        sequence.sort(Comparator.comparingInt(AP07::getNumber));
        int k = sequence.size() - 1;
        int dLength = 1024 * (k) + sequence.get(k).getAudio().length;
        int offset = 0;
        Voice result = new Voice();
        result.data = new byte[dLength];
        for (AP07 packet : sequence) {
            byte[] audio = packet.getAudio();
            System.arraycopy(audio, 0, result.data, offset, audio.length);
            offset += audio.length;
        }
        result.dateTime = LocalDateTime.now();
        return result;
    }

    public List<BP28> toOutcomeSequence(String senderName, int journalNo) {
        List<BP28> result = new ArrayList<>();
        int count = packetsCount(data.length);
        int position = 0;
        int size = count > 1 ? 1024 : data.length;
        for (int i = 0; i < count; i++) {
            byte[] audio = new byte[size];
            System.arraycopy(data, position, audio, 0, size);
            BP28 entry = new BP28();
            entry.setAudio(audio);
            entry.setNumber(i + 1);
            entry.setTotalPackets(count);
            entry.setSenderName(senderName);
            entry.setJournalNo(journalNo);
            result.add(entry);
            position += size;
            size = Math.min(1024, data.length - position);
        }
        return result;
    }

    public VoiceEntry toEntry() {
        VoiceEntry e = new VoiceEntry();
        e.setId(id);
        e.setDuration(duration);
        e.setDateTime(dateTime);
        return e;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}