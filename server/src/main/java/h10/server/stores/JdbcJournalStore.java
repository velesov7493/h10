package h10.server.stores;

import h10.server.models.Journal;
import h10.server.rules.Store;

import java.util.List;

public class JdbcJournalStore implements Store<Integer, Journal> {

    @Override
    public Journal save(Journal value) {
        return null;
    }

    @Override
    public List<Journal> findAll() {
        return null;
    }

    @Override
    public Journal findById(Integer id) {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
