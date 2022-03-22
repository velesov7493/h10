package h10.protocol.rules;

public interface Parseable {

    void parse(String packet);
    String toPacket();
}
