package h10.protocol.packets;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.packets.defaults.DefaultJournalable;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.subunits.WorkingMode;

public class AP33 extends DefaultJournalable {

    private WorkingMode mode;

    @Override
    public void parse(String packet) {
        CompositeUnit scu = new StringCompositeUnit(",", 2);
        scu.parse(packet);
        setJournalNo(scu.getUnit(0).getAsInteger());
        mode = WorkingMode.getByNumber(scu.getUnit(1).getAsInteger());
    }

    @Override
    public String toPacket() {
        CompositeUnit scu = new StringCompositeUnit(",", 2);
        scu.getUnit(0).setInteger(getJournalNo(), 6);
        scu.getUnit(1).setInteger(mode.getNumber(), 1);
        return scu.toPacket();
    }

    public WorkingMode getWorkingMode() {
        return mode;
    }

    public void setWorkingMode(WorkingMode value) {
        mode = value;
    }
}
