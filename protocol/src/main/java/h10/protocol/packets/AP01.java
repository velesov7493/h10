package h10.protocol.packets;

import h10.protocol.components.StrUtils;
import h10.protocol.components.StringCompositeUnit;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.Parseable;
import h10.protocol.subunits.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * AP01 - Пакет геолокации через GPS, LBS и опорные точки доступа WIFI
 * Ответный пакет - BP01 (без содержимого)
 */
public class AP01 implements Parseable {

    private GpsData gps;
    private LbsData lbs;
    private List<WirelessEntry> wifi;

    public AP01() {
        gps = new GpsData();
        lbs = new LbsData();
        wifi = new ArrayList<>();
    }

    @Override
    public void parse(String packet) {
        String[] params = packet.split(",", 2);
        gps.parse(params[0]);
        params = StrUtils.splitOnPosition(params[1], params[1].lastIndexOf(","));
        lbs.parse(params[0]);
        int wifiCount = StrUtils.countOfElements(params[1], "&") + 1;
        CompositeUnit cu = new StringCompositeUnit("&", wifiCount);
        cu.parse(params[1]);
        wifi = CompositeUnit.readUnits(cu, WirelessEntry.class);
    }

    @Override
    public String toPacket() {
        return
                gps.toPacket() + "," + lbs.toPacket() +
                "," + CompositeUnit.packAllUnits(wifi, "&");
    }

    public boolean gpsIsZeroCoordinates() {
        return gps.isZeroCoordinates();
    }

    public LocalDateTime getGpsDateTime() {
        return gps.getDateTime();
    }

    public void setGpsDateTime(LocalDateTime dateTime) {
        gps.setDateTime(dateTime);
    }

    public boolean gpsIsValid() {
        return gps.isValid();
    }

    public void setGpsValid(boolean valid) {
        gps.setValid(valid);
    }

    public double getGpsLatitude() {
        return gps.getLatitude();
    }

    public void setGpsLatitude(double latitude) {
        gps.setLatitude(latitude);
    }

    public LatitudeHemisphere getGpsFromEquator() {
        return gps.getFromEquator();
    }

    public void setGpsFromEquator(LatitudeHemisphere fromEquator) {
        gps.setFromEquator(fromEquator);
    }

    public double getGpsLongitude() {
        return gps.getLongitude();
    }

    public void setGpsLongitude(double longitude) {
        gps.setLongitude(longitude);
    }

    public LongitudeHemisphere getGpsFromGreenwich() {
        return gps.getFromGreenwich();
    }

    public void setGpsFromGreenwich(LongitudeHemisphere fromGreenwich) {
        gps.setFromGreenwich(fromGreenwich);
    }

    public double getGpsSpeed() {
        return gps.getSpeed();
    }

    public void setGpsSpeed(double speed) {
        gps.setSpeed(speed);
    }

    public double getGpsAzimuth() {
        return gps.getAzimuth();
    }

    public void setGpsAzimuth(double azimuth) {
        gps.setAzimuth(azimuth);
    }

    public DeviceStatus getStatus() {
        return gps.getStatus();
    }

    public void setStatus(DeviceStatus status) {
        gps.setStatus(status);
    }

    public int getMobileCountryCode() {
        return lbs.getMobileCountryCode();
    }

    public void setMobileCountryCode(int mobileCountryCode) {
        lbs.setMobileCountryCode(mobileCountryCode);
    }

    public int getMobileNetworkCode() {
        return lbs.getMobileNetworkCode();
    }

    public void setMobileNetworkCode(int mobileNetworkCode) {
        lbs.setMobileNetworkCode(mobileNetworkCode);
    }

    public int getLocalAreaCode() {
        return lbs.getLocalAreaCode();
    }

    public void setLocalAreaCode(int localAreaCode) {
        lbs.setLocalAreaCode(localAreaCode);
    }

    public int getCellId() {
        return lbs.getCellId();
    }

    public void setCellId(int cellId) {
        lbs.setCellId(cellId);
    }

    public boolean isMoving() {
        return lbs.isMoving();
    }

    public int getWifiBasesCount() {
        return wifi.size();
    }

    public WirelessEntry getWifiBase(int index) {
        return wifi.get(index);
    }

    public void addWifiPoint(WirelessEntry value) {
        wifi.add(value);
    }
}