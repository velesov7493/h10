package h10.protocol.subunits;

/**
 *  COUNT - Устройство запрашивает количество речевых записей на сервере
 *
 *  READY - Устройство готово к получению очередного пакета BP28
 *
 *  LIST - Устройство запрашивает список
 *  трех первых в очереди на отправку речевых записей на сервере
 **/
public enum RequestType {
    COUNT,
    READY,
    LIST;

    public static RequestType getByNumber(int number) {
        RequestType result;
        switch (number) {
            case 1: result = RequestType.READY; break;
            case 2: result = RequestType.LIST; break;
            default: result = RequestType.COUNT; break;
        }
        return result;
    }
}