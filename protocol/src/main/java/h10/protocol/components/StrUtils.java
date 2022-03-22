package h10.protocol.components;

public class StrUtils {

    public static int indexOfNthOccurrence(String src, String element, int n) {
        int pos = 0;
        int count = 0;
        while (count < n) {
            pos = src.indexOf(element, pos + 1);
            count++;
        }
        return pos;
    }

    public static String[] splitOnPosition(String src, int position) {
        String[] result = new String[2];
        result[0] = src.substring(0, position);
        result[1] = src.substring(position + 1);
        return result;
    }

    public static String[] splitOnNthOccurrence(String src, String splitter, int n) {
        int pos = indexOfNthOccurrence(src, splitter, n);
        return splitOnPosition(src, pos);
    }

    public static int countOfElements(String src, String element) {
        int pos = 0;
        int count = 0;
        while (pos < src.length()) {
            pos = src.indexOf(element, pos + 1);
            if (pos >= 0) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}
