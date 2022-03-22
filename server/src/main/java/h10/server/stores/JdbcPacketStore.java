package h10.server.stores;

import h10.server.models.Packet;
import h10.server.dto.PacketDimensions;
import h10.server.rules.Store;

import java.util.List;

public class JdbcPacketStore implements Store<Long, Packet> {

    @Override
    public Packet save(Packet value) {
        return null;
    }

    @Override
    public List<Packet> findAll() {
        return null;
    }

    @Override
    public Packet findById(Long id) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    public List<Packet> findAllByDimensions(PacketDimensions value) {
        return null;
    }
}
