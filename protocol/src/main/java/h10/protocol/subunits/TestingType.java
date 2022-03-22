package h10.protocol.subunits;

public enum TestingType {

    HEART_RATE,
    BLOOD_PRESSURE;

    public static TestingType getByNumber(int number) {
        return
                number > 1
                ? TestingType.BLOOD_PRESSURE
                : TestingType.HEART_RATE;
    }

    public int getNumber() {
        return ordinal() + 1;
    }
}
