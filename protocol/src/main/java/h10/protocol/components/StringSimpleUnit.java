package h10.protocol.components;

import h10.protocol.rules.SimpleUnit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringSimpleUnit implements SimpleUnit {

    private String value;

    public static List<SimpleUnit> generateNewUnits(int count) {
        List<SimpleUnit> units = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            units.add(new StringSimpleUnit());
        }
        return units;
    }

    @Override
    public String getAsString() {
        return value;
    }

    @Override
    public void setString(String val) {
        value = val;
    }

    @Override
    public double getAsDouble() {
        return Double.parseDouble(value);
    }

    @Override
    public void setDouble(double val, int width, int scale) {
        int w = width == 0 ? 4 : width + 1;
        int s = scale == 0 ? 2 : scale;
        String result = String.format("%0" + w + "." + s + "f", val);
        value = result.replaceAll(",", ".");
    }

    @Override
    public boolean getAsBoolean() {
        return Integer.parseInt(value) == 1;
    }

    @Override
    public void setBoolean(boolean val) {
        value = val ? "1" : "0";
    }

    @Override
    public int getAsInteger() {
        return Integer.parseInt(value);
    }

    @Override
    public void setInteger(int val, int width) {
        String w = width == 0 ? "" : String.valueOf(width);
        String result = String.format("%"+ w +"d", val);
        value = result.replaceAll(" ", "0");
    }

    @Override
    public long getAsLong() {
        return Long.parseLong(value);
    }

    @Override
    public void setLong(long val) {
        value = Long.toString(val);
    }

    @Override
    public LocalDateTime getAsDatetime(String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.from(df.parse(value));
    }

    @Override
    public void setDatetime(LocalDateTime val, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        value = df.format(val);
    }

    @Override
    public LocalDate getAsDate(String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.from(df.parse(value));
    }

    @Override
    public void setDate(LocalDate val, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        value = df.format(val);
    }

    @Override
    public LocalTime getAsTime(String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalTime.from(df.parse(value));
    }

    @Override
    public void setTime(LocalTime val, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        value = df.format(val);
    }

    @Override
    public int[] getAsIntArray() {
        int[] result = new int[value.length()];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(value.substring(i, i + 1));
        }
        return result;
    }

    @Override
    public void setIntArray(int[] val) {
        StringBuilder result = new StringBuilder();
        for (int i : val) {
            result.append(i);
        }
        value = result.toString();
    }

    @Override
    public char getAsChar() {
        return value.charAt(0);
    }

    @Override
    public void setChar(char val) {
        value = String.valueOf(val);
    }

    @Override
    public byte[] getAsBytes() {
        return value.getBytes();
    }

    @Override
    public void setBytes(byte[] bytes) {
        value = new String(bytes);
    }

    @Override
    public void parse(String packet) throws IllegalArgumentException {
        value = packet;
    }

    @Override
    public String toPacket() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringSimpleUnit that = (StringSimpleUnit) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
