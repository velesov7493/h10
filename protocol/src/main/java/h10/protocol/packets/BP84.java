package h10.protocol.packets;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.components.StringSimpleUnit;
import h10.protocol.packets.defaults.DefaultAddressableJournalable;
import h10.protocol.rules.*;

import java.util.List;

/**
 * BP84 Switch for White list（respond：AP84）
 * Вкл/Выкл "белый" список контактов на устройстве
 * Ответ: AP84
 */
public class BP84 extends DefaultAddressableJournalable {

    private boolean whiteListEnabled;

    @Override
    public void parse(String packet) {
        CompositeUnit cu = new StringCompositeUnit(",", 3);
        cu.parse(packet);
        setImei(cu.getUnit(0).getAsString());
        setJournalNo(cu.getUnit(1).getAsInteger());
        whiteListEnabled = cu.getUnit(3).getAsBoolean();
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit(",", 3);
        cu.getUnit(0).setString(getImei());
        cu.getUnit(1).setInteger(getJournalNo(), 6);
        cu.getUnit(2).setBoolean(whiteListEnabled);
        return cu.toPacket();
    }

    public boolean isWhiteListEnabled() {
        return whiteListEnabled;
    }

    public void setWhiteListEnabled(boolean whiteListEnabled) {
        this.whiteListEnabled = whiteListEnabled;
    }
}