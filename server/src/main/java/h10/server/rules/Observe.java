package h10.server.rules;

@FunctionalInterface
public interface Observe<T> {

    void receive(T model);
}
