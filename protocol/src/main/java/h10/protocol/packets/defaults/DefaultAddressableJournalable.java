package h10.protocol.packets.defaults;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.components.StringSimpleUnit;
import h10.protocol.rules.*;

import java.util.List;

public class DefaultAddressableJournalable
    extends DefaultAddressable
    implements Parseable, Addressable, Journalable {

    private int journalNo;

    @Override
    public String toPacket() {
        StringCompositeUnit scu = new StringCompositeUnit(",", 2);
        scu.getUnit(0).setString(getImei());
        scu.getUnit(1).setInteger(journalNo, 6);
        return scu.toPacket();
    }

    @Override
    public void parse(String packet) {
        StringCompositeUnit scu = new StringCompositeUnit(",", 2);
        scu.parse(packet);
        setImei(scu.getUnit(0).getAsString());
        journalNo = scu.getUnit(1).getAsInteger();
    }

    @Override
    public int getJournalNo() {
        return journalNo;
    }

    @Override
    public void setJournalNo(int journalNo) {
        this.journalNo = journalNo;
    }
}