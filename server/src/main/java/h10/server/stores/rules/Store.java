package h10.server.stores.rules;

import java.util.List;

public interface Store<K, V> {

    V save(V value);

    List<V> findAll();

    V findById(K id);

    boolean deleteById(K id);
}
