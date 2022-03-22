package h10.protocol.subunits;

import h10.protocol.components.HexString;
import h10.protocol.components.StringCompositeUnit;
import h10.protocol.rules.CompositeUnit;
import h10.protocol.rules.Parseable;

import java.util.Objects;

public class ContactEntry implements Parseable {

    private String name;
    private String phone;

    public static ContactEntry of(String name, String phone) {
        ContactEntry result = new ContactEntry();
        result.setName(name);
        result.setPhone(phone);
        return result;
    }

    @Override
    public void parse(String packet) {
        CompositeUnit scu = new StringCompositeUnit("\\|", 2);
        scu.parse(packet);
        name = HexString.decode(scu.getUnit(0).getAsString(), true);
        phone = scu.getUnit(1).getAsString();
    }

    @Override
    public String toPacket() {
        CompositeUnit scu = new StringCompositeUnit("\\|", 2, false);
        scu.getUnit(0).setString(HexString.encode(name, true));
        scu.getUnit(1).setString(phone);
        return scu.toPacket();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContactEntry that = (ContactEntry) o;
        return
                Objects.equals(name, that.name)
                && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }
}