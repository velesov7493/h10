package h10.protocol.packets;

import h10.protocol.components.Patterns;
import h10.protocol.components.StrUtils;
import h10.protocol.components.StringCompositeUnit;
import h10.protocol.components.StringSimpleUnit;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.Parseable;
import h10.protocol.rules.SimpleUnit;
import h10.protocol.subunits.GsmData;
import h10.protocol.subunits.GsmEntry;
import h10.protocol.subunits.WirelessEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * AP02 Multiple bases locating package (responds:BP02)
 * Сообщение ориентирования по опорным точкам беспроводных сетей
 * Данные в пакете служат основой для определения человеко-понятного адреса
 */
public class AP02 implements Parseable {

    private String language;
    private List<WirelessEntry> bluetooth;
    private boolean addressNeeded;
    private GsmData gsm;
    private List<WirelessEntry> wifi;

    public AP02() {
        gsm = new GsmData();
        wifi = new ArrayList<>();
    }

    @Override
    public void parse(String packet) {
        String[] params = packet.split(",", 2);
        if (params[0].contains("|")) {
            CompositeUnit cu1 = new StringCompositeUnit("\\|", 2);
            cu1.parse(params[0]);
            params = params[1].split(",", 2);
            language = cu1.getUnit(0).getAsString();
            int btCount = cu1.getUnit(1).getAsInteger();
            CompositeUnit cu2 = new StringCompositeUnit("&", btCount);
            cu2.parse(params[0]);
            bluetooth = CompositeUnit.readUnits(cu2, WirelessEntry.class);
        } else {
            language = params[0];
        }
        params = params[1].split(",", 2);
        SimpleUnit su = new StringSimpleUnit();
        su.parse(params[0]);
        addressNeeded = su.getAsBoolean();
        Patterns p = Patterns.getInstance();
        Pattern ptGsm = p.getPattern("GsmBases");
        Matcher m = ptGsm.matcher(params[1]);
        if (!m.find()) {
            throw new IllegalArgumentException("Неправильная структура пакета AP02: " + packet);
        }
        params = StrUtils.splitOnPosition(params[1], m.end() - 1);
        gsm.parse(params[0]);
        CompositeUnit cu1 = new StringCompositeUnit(",", 2);
        cu1.parse(params[1]);
        int wifiCount = cu1.getUnit(0).getAsInteger();
        CompositeUnit cu2 = new StringCompositeUnit("&", wifiCount);
        cu2.parse(cu1.getUnit(1).getAsString());
        wifi = CompositeUnit.readUnits(cu2, WirelessEntry.class);
    }

    @Override
    public String toPacket() {
        int unitsCount = bluetooth == null ? 5 : 6;
        int btShift = 0;
        CompositeUnit cu = new StringCompositeUnit(",", unitsCount, true);
        if (bluetooth != null) {
            cu.getUnit(0).setString(language + "|" + bluetooth.size());
            cu.getUnit(1).setString(CompositeUnit.packAllUnits(bluetooth, "@"));
            btShift++;
        } else {
            cu.getUnit(0).setString(language);
        }
        cu.getUnit(1 + btShift).setBoolean(addressNeeded);
        cu.getUnit(2 + btShift).setString(gsm.toPacket());
        cu.getUnit(3 + btShift).setInteger(wifi.size(), 0);
        cu.getUnit(4 + btShift).setString(CompositeUnit.packAllUnits(wifi, "@"));
        return cu.toPacket();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public WirelessEntry getBluetoothBase(int index) {
        if (bluetooth == null || index < 0 || index >= bluetooth.size()) {
            throw new NoSuchElementException("Нет bluetooth точки с индексом" + index + "!");
        }
        return bluetooth.get(index);
    }

    public AP02 addBluetoothBase(WirelessEntry value) {
        if (bluetooth == null) {
            bluetooth = new ArrayList<>();
        }
        bluetooth.add(value);
        return this;
    }

    public boolean isAddressNeeded() {
        return addressNeeded;
    }

    public void setAddressNeeded(boolean addressNeeded) {
        this.addressNeeded = addressNeeded;
    }

    public WirelessEntry getWifiBase(int index) {
        return wifi.get(index);
    }

    public AP02 addWifiBase(WirelessEntry value) {
        wifi.add(value);
        return this;
    }

    public int getGsmBasesCount() {
        return gsm.getBasesCount();
    }

    public int getMobileCountryCode() {
        return gsm.getMobileCountryCode();
    }

    public void setMobileCountryCode(int mobileCountryCode) {
        gsm.setMobileCountryCode(mobileCountryCode);
    }

    public int getMobileNetworkCode() {
        return gsm.getMobileNetworkCode();
    }

    public boolean isMoving() {
        return gsm.isMoving();
    }

    public void setMobileNetworkCode(int mobileNetworkCode) {
        gsm.setMobileNetworkCode(mobileNetworkCode);
    }

    public GsmEntry getGsmBase(int index) {
        return gsm.getBase(index);
    }

    public AP02 addGsmBase(GsmEntry value) {
        gsm.addBase(value);
        return this;
    }
}
