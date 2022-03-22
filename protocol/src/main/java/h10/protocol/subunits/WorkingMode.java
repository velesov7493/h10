package h10.protocol.subunits;

/*
 * NORMAL -  Нормальный режим - каждые 15 минут отчет о местонахождении с помощью WIFI и LBS
 * POWER_SAVING - Режим энергосбережения - то же, что и NORMAL_MODE, но период отчета - 60 мин.
 * EMERGENCY - Режим ЧС - каждую минуту отчет с помощью GPS, WIFI и LBS
 */

public enum WorkingMode {

    UNDEFINED,
    NORMAL,
    POWER_SAVING,
    EMERGENCY;

    public int getNumber() {
        return ordinal();
    }

    public static WorkingMode getByNumber(int number) {
        WorkingMode result;
        switch (number) {
            case 1: result = WorkingMode.NORMAL; break;
            case 2: result = WorkingMode.POWER_SAVING; break;
            case 3: result = WorkingMode.EMERGENCY; break;
            default: result = WorkingMode.UNDEFINED;
        }
        return result;
    }
}