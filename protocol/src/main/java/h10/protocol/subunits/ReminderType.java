package h10.protocol.subunits;

public enum ReminderType {

    TAKE_THE_MEDICINE,
    DRINK_WATER,
    SEDENTARY;

    public static ReminderType getByNumber(int number) {
        ReminderType result;
        switch (number) {
            case 1: result = ReminderType.TAKE_THE_MEDICINE; break;
            case 3: result = ReminderType.SEDENTARY; break;
            default: result = ReminderType.DRINK_WATER; break;
        }
        return result;
    }

    public int getNumber() {
        return ordinal() + 1;
    }
}
