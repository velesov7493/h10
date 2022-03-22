package h10.protocol.packets;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.components.StringSimpleUnit;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.Parseable;
import h10.protocol.rules.SimpleUnit;

import java.time.LocalDateTime;
import java.util.List;

/**
 * BP00
 *  Ответ сервера на пакет входа от устройства
 */

public class BP00 implements Parseable {

    private LocalDateTime dateTime;
    private int timeZone;

    @Override
    public void parse(String packet) {
        CompositeUnit cu = new StringCompositeUnit(",", 2);
        cu.parse(packet);
        dateTime = cu.getUnit(0).getAsDatetime("yyyyMMddHHmmss");
        timeZone = cu.getUnit(1).getAsInteger();
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit(",", 2, false);
        cu.getUnit(0).setDatetime(dateTime, "yyyyMMddHHmmss");
        cu.getUnit(1).setInteger(timeZone, 0);
        return cu.toPacket();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(int timeZone) {
        this.timeZone = timeZone;
    }
}
