package h10.protocol.components;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Patterns {

    private final static Patterns INSTANCE = new Patterns();

    private HashMap<String, Pattern> expressions;

    private Patterns() {
        expressions = new HashMap<>();
        fillExpressions();
    }

    private void fillExpressions() {
        String[][] patterns = {
                {"ProtocolDataUnit", "IW[AB]P(\\d{2}|TM|JZ|HP|HT|XL|XY).*#^(!AMR)"},
                {"Alarms", ",((\\d{4},\\d{1,7},\\d{1},\\d{1})@?)+"},
                {"AudioPdu", "IWAP07,\\d{14},\\d{1,3},\\d{1,3},\\d{1,4},^(#IWAP)+#"},
                {"GpsSpeed", "[EW]\\d{3}\\.\\d{1}"},
                {"GpsCoords", "[AV]\\d{4}\\.\\d{4}[NS]\\d{5}\\.\\d{4}[EW]"},
                {"GpsDate", "\\d{6}[AV]"},
                {"GsmBases", "((\\d{1,4}\\|){2}\\d{1,3},?)+"},
                {"WirelessBases", "((\\w+\\|[0-9a-fA-F\\-]{17}\\|\\d{1,3})@?)+"}
        };
        for (String[] row : patterns) {
            expressions.put(row[0], Pattern.compile(row[1]));
        }
    }

    public static Patterns getInstance() {
        return INSTANCE;
    }

    public  Pattern getPattern(String name) {
        Pattern regex = expressions.get(name);
        if (regex == null) {
            throw new IllegalArgumentException("В наборе паттернов нет ключа с именем " + name);
        }
        return regex;
    }

    public static Pattern byName(String name) {
        return INSTANCE.getPattern(name);
    }
}
