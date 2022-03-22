package h10.protocol.subunits;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.Parseable;

public class WirelessEntry implements Parseable {

    private String name;
    private String macAddress;
    private int signalPower;

    public static WirelessEntry of(String name, String macAddress, int signalPower) {
        WirelessEntry result = new WirelessEntry();
        result.name = name;
        result.macAddress = macAddress;
        result.signalPower = signalPower;
        return result;
    }

    @Override
    public void parse(String packet) {
        CompositeUnit cu = new StringCompositeUnit("\\|", 3);
        cu.parse(packet);
        name = cu.getUnit(0).getAsString();
        macAddress = cu.getUnit(1).getAsString();
        signalPower = cu.getUnit(2).getAsInteger();
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit("\\|", 3, false);
        cu.getUnit(0).setString(name);
        cu.getUnit(1).setString(macAddress);
        cu.getUnit(2).setInteger(signalPower, 0);
        return cu.toPacket();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public int getSignalPower() {
        return signalPower;
    }

    public void setSignalPower(int signalPower) {
        this.signalPower = signalPower;
    }
}