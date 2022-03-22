package h10.protocol.subunits;

public enum LatitudeHemisphere {
    NORTH,
    SOUTH;

    public static LatitudeHemisphere getByChar(char value) {
        return value == 'S' ?  LatitudeHemisphere.SOUTH : LatitudeHemisphere.NORTH;
    }

    public char getChar() {
        return ordinal() == 0 ? 'N' : 'S';
    }
}
