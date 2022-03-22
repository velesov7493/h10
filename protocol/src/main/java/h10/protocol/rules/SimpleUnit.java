package h10.protocol.rules;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface SimpleUnit extends Parseable {

    String getAsString();
    void setString(String val);
    double getAsDouble();
    void setDouble(double val, int width, int scale);
    boolean getAsBoolean();
    void setBoolean(boolean val);
    int getAsInteger();
    void setInteger(int val, int width);
    long getAsLong();
    void setLong(long val);
    LocalDateTime getAsDatetime(String pattern);
    void setDatetime(LocalDateTime val, String pattern);
    LocalDate getAsDate(String pattern);
    void setDate(LocalDate val, String pattern);
    LocalTime getAsTime(String pattern);
    void setTime(LocalTime val, String pattern);
    int[] getAsIntArray();
    void setIntArray(int[] val);
    char getAsChar();
    void setChar(char val);
    byte[] getAsBytes();
    void setBytes(byte[] bytes);
}
