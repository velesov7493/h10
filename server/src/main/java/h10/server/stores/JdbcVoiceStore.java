package h10.server.stores;

import h10.server.models.Voice;
import h10.server.stores.rules.Store;
import h10.server.stores.rules.VoiceStore;

import java.util.List;

public class JdbcVoiceStore implements VoiceStore {

    private static final class Holder {
        private static final JdbcVoiceStore INSTANCE = new JdbcVoiceStore();
    }

    private JdbcVoiceStore() { }

    public static VoiceStore getInstance() {
        return Holder.INSTANCE;
    }

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

    @Override
    public Voice findFirstByImei(String imei) {
        return null;
    }

    @Override
    public List<Voice> findAllByImei(String imei) {
        return null;
    }

    @Override
    public int getCountByImei(String imei) {
        return 0;
    }
}
