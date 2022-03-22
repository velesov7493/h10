package h10.protocol.packets;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.Parseable;
import h10.protocol.subunits.VoiceEntry;

import java.util.ArrayList;
import java.util.List;

public class BPVL implements Parseable {

    private List<VoiceEntry> voices;

    public BPVL() {
        voices = new ArrayList<>();
    }

    @Override
    public void parse(String packet) {
        CompositeUnit cu = new StringCompositeUnit(",", 2);
        cu.parse(packet);
        int voicesCount = cu.getUnit(0).getAsInteger();
        CompositeUnit cu1 = new StringCompositeUnit("\\|", voicesCount);
        cu1.parse(cu.getUnit(1).getAsString());
        voices = CompositeUnit.readUnits(cu1, VoiceEntry.class);
    }

    @Override
    public String toPacket() {
        CompositeUnit cu = new StringCompositeUnit(",", 2, true);
        cu.getUnit(0).setInteger(voices.size(), 0);
        cu.getUnit(1).setString(CompositeUnit.packAllUnits(voices, "\\|"));
        return cu.toPacket();
    }

    public VoiceEntry getVoice(int index) {
        return voices.get(index);
    }

    public BPVL addVoice(VoiceEntry value) {
        voices.add(value);
        return this;
    }
}
