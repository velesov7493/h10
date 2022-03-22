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
 * 	AP10 Alarm and Return address Package(responds:BP10)
 * 	AP10 - пакет срочной геолокации по тревоге с возвратом адреса
 * 	Ответный пакет - BP10
 */

public class AP10 implements Parseable {

    private GpsData gps;
    private LbsData lbs;
    private AlarmState alarmState;
    private String language;
    private boolean addressNeeded;
    private List<WirelessEntry> wifi;

    public AP10() {
        gps = new GpsData();
        lbs = new LbsData();
        wifi = new ArrayList<>();
    }

    @Override
    public void parse(String packet) {
        String[] params = packet.split(",", 2);
        gps.parse(params[0]);
        params = StrUtils.splitOnNthOccurrence(params[1], ",", 4);
        lbs.parse(params[0]);
        CompositeUnit cu1 = new StringCompositeUnit(",", 4);
        cu1.parse(params[1]);
        alarmState = AlarmState.findByNumber(cu1.getUnit(0).getAsInteger());
        language = cu1.getUnit(1).getAsString();
        addressNeeded = cu1.getUnit(2).getAsBoolean();
        String wifiMsg = cu1.getUnit(3).getAsString();
        int wifiCount = StrUtils.countOfElements(wifiMsg, "&") + 1;
        CompositeUnit cu2 = new StringCompositeUnit("&", wifiCount);
        cu2.parse(wifiMsg);
        wifi = CompositeUnit.readUnits(cu2, WirelessEntry.class);
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit(",", 6, true);
        cu.getUnit(0).setString(gps.toPacket());
        cu.getUnit(1).setString(lbs.toPacket());
        cu.getUnit(2).setInteger(alarmState.getNumber(), 1);
        cu.getUnit(3).setString(language);
        cu.getUnit(4).setBoolean(addressNeeded);
        cu.getUnit(5).setString(CompositeUnit.packAllUnits(wifi, "&"));
        return cu.toPacket();
    }

    public boolean isZeroCoordinates() {
        return gps.isZeroCoordinates();
    }

    public LocalDateTime getDateTime() {
        return gps.getDateTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        gps.setDateTime(dateTime);
    }

    public boolean isValid() {
        return gps.isValid();
    }

    public void setValid(boolean valid) {
        gps.setValid(valid);
    }

    public double getLatitude() {
        return gps.getLatitude();
    }

    public void setLatitude(double latitude) {
        gps.setLatitude(latitude);
    }

    public LatitudeHemisphere getFromEquator() {
        return gps.getFromEquator();
    }

    public void setFromEquator(LatitudeHemisphere fromEquator) {
        gps.setFromEquator(fromEquator);
    }

    public double getLongitude() {
        return gps.getLongitude();
    }

    public void setLongitude(double longitude) {
        gps.setLongitude(longitude);
    }

    public LongitudeHemisphere getFromGreenwich() {
        return gps.getFromGreenwich();
    }

    public void setFromGreenwich(LongitudeHemisphere fromGreenwich) {
        gps.setFromGreenwich(fromGreenwich);
    }

    public double getSpeed() {
        return gps.getSpeed();
    }

    public void setSpeed(double speed) {
        gps.setSpeed(speed);
    }

    public double getAzimuth() {
        return gps.getAzimuth();
    }

    public void setAzimuth(double azimuth) {
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

    public AlarmState getAlarmState() {
        return alarmState;
    }

    public void setAlarmState(AlarmState alarmState) {
        this.alarmState = alarmState;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isAddressNeeded() {
        return addressNeeded;
    }

    public void setAddressNeeded(boolean addressNeeded) {
        this.addressNeeded = addressNeeded;
    }

    public int getWifiCount() {
        return wifi.size();
    }

    public WirelessEntry getWifiPoint(int index) {
        return wifi.get(index);
    }

    public AP10 addWifiPoint(WirelessEntry value) {
        wifi.add(value);
        return this;
    }
}