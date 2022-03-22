package h10.protocol.subunits;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.components.StringSimpleUnit;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.Parseable;
import h10.protocol.rules.SimpleUnit;

import java.time.LocalDateTime;
import java.util.List;

public class VoiceEntry implements Parseable {

    private int id;
    private int duration;
    private LocalDateTime dateTime;

    public static VoiceEntry of(int id, int duration, LocalDateTime dateTime) {
        VoiceEntry result = new VoiceEntry();
        result.id = id;
        result.duration = duration;
        result.dateTime = dateTime;
        return result;
    }

    @Override
    public void parse(String packet) {
        CompositeUnit cu = new StringCompositeUnit("@", 3);
        cu.parse(packet);
        id = cu.getUnit(0).getAsInteger();
        duration = cu.getUnit(1).getAsInteger();
        dateTime = cu.getUnit(2).getAsDatetime("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit("@", 3, false);
        cu.getUnit(0).setInteger(id, 0);
        cu.getUnit(1).setInteger(duration, 0);
        cu.getUnit(2).setDatetime(dateTime, "yyyy-MM-dd HH:mm:ss");
        return cu.toPacket();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}