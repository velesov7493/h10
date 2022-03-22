package h10.server.stores;

import h10.server.models.Device;
import h10.server.rules.Store;

import java.util.List;

public class JdbcDeviceStore implements Store<String, Device> {

    @Override
    public Device save(Device value) {
        return null;
    }

    @Override
    public List<Device> findAll() {
        return null;
    }

    @Override
    public Device findById(String id) {
        return null;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}
