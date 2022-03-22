package h10.protocol.components;

import java.nio.charset.StandardCharsets;

public class HexString {

    private static String encode(String strData) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < strData.length(); i++) {
            char c = strData.charAt(i);
            result.append(String.format("%4X", (int) c));
        }
        return
                result
                .toString()
                .replaceAll(" ", "0");
    }

    private static String encodeReverse(String strData) {
        byte[] data = strData.getBytes(StandardCharsets.UTF_16LE);
        StringBuilder result = new StringBuilder();
        for (byte b : data) {
            result.append(String.format("%2X", b));
        }
        return
                result
                .toString()
                .replaceAll(" ", "0");
    }

    public static String encode(String strData, boolean invert) {
        return invert ? encodeReverse(strData) : encode(strData);
    }

    public static String decode(String hexStr, boolean invert) throws NumberFormatException {
        int i, m;
        int[] j = new int[4];
        char c;
        String msg = hexStr.toUpperCase();
        StringBuilder result = new StringBuilder();
        String p = "0123456789ABCDEF";
        m = hexStr.length() % 4;
        while (m != 0) {
            msg = "0" + msg;
            m--;
        }
        for (i = 0; i < msg.length(); i += 4) {
            for (int k = 0; k < 4; k++) {
                c = msg.charAt(i + (3 - k));
                j[k] = p.indexOf(c);
            }
            if (j[0] < 0 || j[1] < 0 || j[2] < 0 || j[3] < 0) {
                throw new NumberFormatException(
                    "Строка не является представлением шестнадцатеричного числа."
                );
            }
            result.append(
                    invert
                    ? (char) ((j[1] << 12) | (j[0] << 8) | (j[3] << 4) | j[2])
                    : (char) ((j[3] << 12) | (j[2] << 8) | (j[1] << 4) | j[0])
            );
        }
        return result.toString();
    }
}
