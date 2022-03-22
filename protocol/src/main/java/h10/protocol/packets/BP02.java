package h10.protocol.packets;

import h10.protocol.components.HexString;
import h10.protocol.rules.Parseable;

/**
 * BP02 - ответ сервера на AP02
 * содержит человеко-понятный адрес на запрашиваемом языке
 */

public class BP02 implements Parseable {

    private String address;

    @Override
    public void parse(String packet) {
        address = packet.length() > 0 ? HexString.decode(packet, true) : null;
    }

    @Override
    public String toPacket() {
        return address != null ? HexString.encode(address, true) : "";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
