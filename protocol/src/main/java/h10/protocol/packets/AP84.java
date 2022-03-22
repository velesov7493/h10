package h10.protocol.packets;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.packets.defaults.DefaultJournalable;
import h10.protocol.rules.CompositeUnit;

public class AP84 extends DefaultJournalable {

    private boolean isDone;

    @Override
    public void parse(String packet) {
        CompositeUnit scu = new StringCompositeUnit(",", 2);
        scu.parse(packet);
        setJournalNo(scu.getUnit(0).getAsInteger());
        isDone = scu.getUnit(1).getAsBoolean();
    }

    @Override
    public String toPacket() {
        CompositeUnit scu = new StringCompositeUnit(",", 2);
        scu.getUnit(0).setInteger(getJournalNo(), 6);
        scu.getUnit(1).setBoolean(isDone);
        return scu.toPacket();
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}