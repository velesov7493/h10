package h10.protocol.packets;

import h10.protocol.components.StringSimpleUnit;
import h10.protocol.rules.Parseable;
import h10.protocol.rules.SimpleUnit;
import h10.protocol.subunits.RequestType;

/**
 *  AP05 - реакция устройства на речевой пакет (BP28)
 *  Это запрос состояния речевых пакетов для устройства на сервере
 *  Ответ - BP05 или BPVL, если тип запроса = 2 (LIST)
 */
public class AP05 implements Parseable {

    private RequestType requestType;

    @Override
    public void parse(String packet) {
        SimpleUnit su = new StringSimpleUnit();
        su.parse(packet);
        requestType = RequestType.getByNumber(su.getAsInteger());
    }

    @Override
    public String toPacket() {
        SimpleUnit su = new StringSimpleUnit();
        su.setInteger(requestType.ordinal(), 1);
        return su.toPacket();
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }
}
