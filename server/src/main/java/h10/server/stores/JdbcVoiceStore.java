package h10.server.stores;

import h10.server.models.Voice;
import h10.server.rules.Store;

import java.util.List;

public class JdbcVoiceStore implements Store<Integer, Voice> {

    @Override
    public Voice save(Voice value) {
        return null;
    }

    @Override
    public List<Voice> findAll() {
        return null;
    }

    @Override
    public Voice findById(Integer id) {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    public List<Voice> findAllByImei(String imei) {
        return null;
    }
}
