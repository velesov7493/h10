package h10.protocol.packets;

import h10.protocol.components.StringCompositeUnit;
import h10.protocol.packets.defaults.DefaultJournalable;
import h10.protocol.rules.CompositeUnit;

import java.util.ArrayList;
import java.util.List;

public class AP12 extends DefaultJournalable {

    private List<String> phones;

    public AP12() {
        phones = new ArrayList<>();
    }

    @Override
    public void parse(String packet) {
        CompositeUnit scu = new StringCompositeUnit(",", 4);
        scu.parse(packet);
        setJournalNo(scu.getUnit(0).getAsInteger());
        for (int i = 1; i < 4; i++) {
            phones.add(scu.getUnit(i).getAsString());
        }

    }

    @Override
    public String toPacket() {
        CompositeUnit scu = new StringCompositeUnit(",", 1 + phones.size());
        scu.getUnit(0).setInteger(getJournalNo(), 6);
        for (int i = 0; i < phones.size(); i++) {
            scu.getUnit(i + 1).setString(phones.get(i));
        }
        return scu.toPacket();
    }

    public List<String> getSosPhones() {
        return phones;
    }

    public void setSosPhones(List<String> value) {
        if (value.size() > 3) {
            throw new IllegalArgumentException(
                "Можно установить не больше трех экстренных номеров!"
            );
        }
        phones = value;
    }
}