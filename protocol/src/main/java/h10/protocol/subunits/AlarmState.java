package h10.protocol.subunits;

public enum AlarmState {

    NO_ALARM,
    SOS,
    UNKNOWN_ALARM,
    LOW_BATTERY,
    WEARING_NOTICE,
    DEFAULT_NOTICE,
    FALL_DOWN_ALARM;

    public static AlarmState findByNumber(int number) {
        AlarmState result;
        switch (number) {
            case 1: result = AlarmState.SOS; break;
            case 2: result = AlarmState.LOW_BATTERY; break;
            case 3: result = AlarmState.UNKNOWN_ALARM; break;
            case 4: result = AlarmState.WEARING_NOTICE; break;
            case 5: result = AlarmState.DEFAULT_NOTICE; break;
            case 6: result = AlarmState.FALL_DOWN_ALARM; break;
            default: result = AlarmState.NO_ALARM; break;
        }
        return result;
    }

    public int getNumber() {
        return ordinal();
    }
}
