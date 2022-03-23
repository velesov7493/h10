package h10.protocol.rules;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public interface CompositeUnit extends Parseable {

    int getSize();

    SimpleUnit getUnit(int index);

    static void parseAll(List<? extends Parseable> units, String splitter, String packet) {
        String[] params = packet.split(splitter);
        if (params.length != units.size()) {
            throw new IllegalArgumentException(
                "Количество частей пакета данных (" + params.length
                + ") не совпадает с количеством компонентов (" + units.size() + ")."
            );
        }
        for (int i = 0; i < units.size(); i++) {
            units.get(i).parse(params[i]);
        }
    }

    static void parseAllBySplitter(CompositeUnit cu, String packet) {
        String[] params = packet.split(cu.getSplitter());
        if (params.length != cu.getSize()) {
            throw new IllegalArgumentException(
                    "Количество частей пакета данных (" + params.length
                    + ") не совпадает с количеством компонентов (" + cu.getSize() + ")."
            );
        }
        for (int i = 0; i < cu.getSize(); i++) {
            cu.getUnit(i).parse(params[i]);
        }
    }

    static void parseAllByLengths(CompositeUnit cu, String packet) {
        int[] lengths = cu.getLengths();
        if (lengths == null) {
            throw new IllegalStateException(
                "Для разбора этого модуля длины компонентов не используются! "
                + "Используйте parseAllBySplitter()"
            );
        }
        if (lengths.length != cu.getSize()) {
            throw new IllegalArgumentException(
                "Количество частей пакета данных (" + lengths.length
                + ") не совпадает с количеством компонентов (" + cu.getSize() + ")."
            );
        }
        String[] params = new String[lengths.length];
        int pos = 0;
        for (int i = 0; i < params.length; i++) {
            params[i] = packet.substring(pos, pos + lengths[i]);
            pos += lengths[i];
        }
        for (int i = 0; i < cu.getSize(); i++) {
            cu.getUnit(i).parse(params[i]);
        }
    }

    static String packAllUnits(List<? extends Parseable> units, String splitter) {
        String result;
        if (splitter != null && !splitter.isEmpty()) {
            if (splitter.contains("\\")) {
                splitter = splitter.substring(1);
            }
            StringJoiner r = new StringJoiner(splitter);
            for (Parseable u : units) {
                r.add(u.toPacket());
            }
            result = r.toString();
        } else {
            StringBuilder r = new StringBuilder();
            for (Parseable data : units) {
                r.append(data.toPacket());
            }
            result = r.toString();
        }
        return result;
    }

    static <T extends Parseable> List<T> readUnits(
            CompositeUnit cu,
            int offset, int count,
            Class<T> unitClazz
    ) {
        int l = offset + count;
        if (l > cu.getSize()) {
            throw new IndexOutOfBoundsException(
                    "Максимальный индекс элемента: " + (cu.getSize() - 1)
                    + ", а последний запрашиваемый: " + (l - 1)
            );
        }
        List<T> result = new ArrayList<>();
        Constructor<T> c;
        try {
            c = unitClazz.getDeclaredConstructor();
            for (int i = offset; i < l; i++) {
                T entry = c.newInstance();
                entry.parse(cu.getUnit(i).getAsString());
                result.add(entry);
            }
        } catch (Throwable ex) {
            throw new IllegalArgumentException(ex);
        }
        return result;
    }

    static <T extends Parseable> List<T> readUnits(CompositeUnit cu, Class<T> unitClazz) {
        return readUnits(cu, 0, cu.getSize(), unitClazz);
    }

    static <T extends Parseable> void writeUnits(List<T> src, CompositeUnit dest, int offset) {
        int l = offset + src.size();
        if (l > dest.getSize()) {
            throw new IndexOutOfBoundsException(
                    "Максимальный индекс элемента: " + (dest.getSize() - 1)
                    + ", а последний запрашиваемый: " + (l - 1)
            );
        }
        for (int i = 0; i < src.size(); i++) {
            dest.getUnit(offset + i).setString(src.get(i).toPacket());
        }
    }

    static String packAll(CompositeUnit cu) {
        String result;
        String splitter = cu.getSplitter();
        if (splitter != null && !splitter.isEmpty()) {
            if (splitter.contains("\\")) {
                splitter = splitter.substring(1);
            }
            StringJoiner r = new StringJoiner(splitter);
            for (int i = 0; i < cu.getSize(); i++) {
                r.add(cu.getUnit(i).toPacket());
            }
            result = r.toString();
        } else {
            StringBuilder r = new StringBuilder();
            for (int i = 0; i < cu.getSize(); i++) {
                r.append(cu.getUnit(i).toPacket());
            }
            result = r.toString();
        }
        return result;
    }

    default String getSplitter() {
        return null;
    }

    default int[] getLengths() {
        return null;
    }

    @Override
    default void parse(String packet) {
        if (getSplitter() == null && getLengths() == null) {
            throw new IllegalStateException(
                "Должен быть определен либо разделитель либо длины подпакетов!"
            );
        }
        if (getSplitter() == null) {
            parseAllByLengths(this, packet);
        } else {
            parseAllBySplitter(this, packet);
        }
    }

    @Override
    default String toPacket() {
        return packAll(this);
    }
}