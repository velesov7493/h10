package h10.protocol.packets.defaults;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.Journalable;
import h10.protocol.rules.Parseable;

import java.util.List;

public class DefaultJournalable implements Parseable, Journalable {

    private int journalNo;

    @Override
    public void parse(String packet) {
        StringCompositeUnit scu = new StringCompositeUnit(",", 1);
        scu.parse(packet);
        journalNo = scu.getUnit(0).getAsInteger();
    }

    @Override
    public String toPacket() {
        StringCompositeUnit scu = new StringCompositeUnit(",", 1);
        scu.getUnit(0).setInteger(journalNo, 6);
        return scu.toPacket();
    }

    @Override
    public int getJournalNo() {
        return journalNo;
    }

    @Override
    public void setJournalNo(int value) {
        journalNo = value;
    }
}