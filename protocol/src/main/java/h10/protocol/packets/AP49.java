package h10.protocol.packets;

import h10.protocol.components.StringSimpleUnit;
import h10.protocol.rules.Parseable;
import h10.protocol.rules.SimpleUnit;

/**
 * AP49 Upload heart rate（responds：BP49）
 * Пакет частоты седцебиения. Ответ - BP49 (без содержания)
 */
public class AP49 implements Parseable {

    private int heartRate;

    @Override
    public void parse(String packet) {
        SimpleUnit su = new StringSimpleUnit();
        su.parse(packet);
        heartRate = su.getAsInteger();
    }

    @Override
    public String toPacket() {
        SimpleUnit su = new StringSimpleUnit();
        su.setInteger(heartRate, 0);
        return "," + su.toPacket();
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int value) {
        heartRate = value;
    }
}
