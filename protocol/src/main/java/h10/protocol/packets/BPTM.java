package h10.protocol.packets;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.Parseable;

import java.time.LocalDateTime;

public class BPTM implements Parseable {

    private LocalDateTime dateTime;
    private double latitude;
    private double longitude;

    @Override
    public void parse(String packet) {
        CompositeUnit cu = new StringCompositeUnit(",", 3);
        cu.parse(packet);
        dateTime = cu.getUnit(0).getAsDatetime("yyyyMMddHHmmss");
        latitude = cu.getUnit(1).getAsDouble();
        longitude = cu.getUnit(2).getAsDouble();
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit(",", 3, true);
        cu.getUnit(0).setDatetime(dateTime, "yyyyMMddHHmmss");
        cu.getUnit(1).setDouble(latitude, 8, 6);
        cu.getUnit(2).setDouble(longitude, 9, 6);
        return cu.toPacket();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}