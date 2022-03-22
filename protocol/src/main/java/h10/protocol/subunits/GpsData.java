package h10.protocol.subunits;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.Parseable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class GpsData implements Parseable {

    private LocalDateTime dateTime;
    private boolean valid;
    private double latitude;
    private LatitudeHemisphere fromEquator;
    private double longitude;
    private LongitudeHemisphere fromGreenwich;
    private double speed;
    private double azimuth;
    private DeviceStatus status;

    public GpsData() {
        status = new DeviceStatus();
    }

    private void readDateTime(CompositeUnit src) {
        LocalDate date = src.getUnit(0).getAsDate("yyMMdd");
        LocalTime time = src.getUnit(9).getAsTime("HHmmss");
        dateTime = LocalDateTime.of(date, time);
    }

    private void writeDateTime(CompositeUnit dest) {
        dest.getUnit(0).setDatetime(dateTime, "yyMMdd");
        dest.getUnit(9).setDatetime(dateTime, "HHmmss");
    }

    private void readLatitude(CompositeUnit src) {
        int gradLatitude = src.getUnit(2).getAsInteger();
        double minutesLatitude = src.getUnit(3).getAsDouble();
        latitude = gradLatitude + minutesLatitude / 60;
        fromEquator = LatitudeHemisphere.getByChar(src.getUnit(4).getAsChar());
    }

    private void writeLatitude(CompositeUnit dest) {
        int gradLatitude = (int) Math.floor(latitude);
        double minutesLatitude = 60 * (latitude - gradLatitude);
        dest.getUnit(2).setInteger(gradLatitude, 2);
        dest.getUnit(3).setDouble(minutesLatitude, 6, 4);
        dest.getUnit(4).setChar(fromEquator.getChar());
    }

    private void readLongitude(CompositeUnit src) {
        int gradLongitude = src.getUnit(5).getAsInteger();
        double minutesLongitude = src.getUnit(6).getAsDouble();
        longitude = gradLongitude + minutesLongitude / 60;
        fromGreenwich = LongitudeHemisphere.getByChar(src.getUnit(7).getAsChar());
    }

    private void writeLongitude(CompositeUnit dest) {
        int gradLongitude = (int) Math.floor(longitude);
        double minutesLongitude = 60 * (longitude - gradLongitude);
        dest.getUnit(5).setInteger(gradLongitude, 3);
        dest.getUnit(6).setDouble(minutesLongitude, 6, 4);
        dest.getUnit(7).setChar(fromGreenwich.getChar());
    }

    public boolean isZeroCoordinates() {
        return !(latitude > 0 && longitude > 0);
    }

    @Override
    public void parse(String packet) {
        CompositeUnit cu = new StringCompositeUnit(
            new int[] {6, 1, 2, 7, 1, 3, 7, 1, 5, 6, 6, 14}, 12
        );
        cu.parse(packet);
        readDateTime(cu);
        valid = cu.getUnit(1).getAsChar() == 'A';
        readLatitude(cu);
        readLongitude(cu);
        speed = cu.getUnit(8).getAsDouble();
        azimuth = cu.getUnit(10).getAsDouble();
        status.parse(cu.getUnit(11).getAsString());
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit(
                new int[] {6, 1, 2, 7, 1, 3, 7, 1, 5, 6, 6, 14}, 12
        );
        writeDateTime(cu);
        cu.getUnit(1).setChar(valid ? 'A' : 'V');
        writeLatitude(cu);
        writeLongitude(cu);
        cu.getUnit(8).setDouble(speed, 4, 1);
        cu.getUnit(10).setDouble(azimuth, 5, 2);
        cu.getUnit(11).setString(status.toPacket());
        return cu.toPacket();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public LatitudeHemisphere getFromEquator() {
        return fromEquator;
    }

    public void setFromEquator(LatitudeHemisphere fromEquator) {
        this.fromEquator = fromEquator;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LongitudeHemisphere getFromGreenwich() {
        return fromGreenwich;
    }

    public void setFromGreenwich(LongitudeHemisphere fromGreenwich) {
        this.fromGreenwich = fromGreenwich;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(double azimuth) {
        this.azimuth = azimuth;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }
}