package h10.protocol.packets.defaults;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.components.StringSimpleUnit;
import h10.protocol.rules.Addressable;
import h10.protocol.rules.Parseable;
import h10.protocol.rules.SimpleUnit;

public class DefaultAddressable implements Parseable, Addressable {

    private String imei;

    @Override
    public void parse(String packet) {
        StringCompositeUnit scu = new StringCompositeUnit(",", 1);
        scu.parse(packet);
        imei = scu.getUnit(0).getAsString();
    }

    @Override
    public String toPacket() {
        StringCompositeUnit scu = new StringCompositeUnit(",", 1);
        scu.getUnit(0).setString(imei);
        return scu.toPacket();
    }

    @Override
    public String getImei() {
        return imei;
    }

    @Override
    public void setImei(String value) {
        imei = value;
    }
}