package h10.protocol.subunits;

public enum LongitudeHemisphere {
    EAST,
    WEST;

    public static LongitudeHemisphere getByChar(char value) {
        return value == 'W' ?  LongitudeHemisphere.WEST : LongitudeHemisphere.EAST;
    }

    public char getChar() {
        return ordinal() == 0 ? 'E' : 'W';
    }
}
