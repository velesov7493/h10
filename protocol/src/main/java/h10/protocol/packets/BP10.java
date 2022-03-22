package h10.protocol.packets;

import h10.protocol.components.HexString;

/**
 * BP10 -ответ на AP10
 */

public class BP10 extends BP02 {

    @Override
    public String toPacket() {
        return getAddress() != null ? HexString.encode(getAddress(), false) : "";
    }
}