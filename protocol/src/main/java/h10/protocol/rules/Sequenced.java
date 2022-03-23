package h10.protocol.rules;

public interface Sequenced {

    int getNumber();

    void setNumber(int value);

    int getTotalPackets();

    void setTotalPackets(int value);
}
