package h10.protocol;

import h10.protocol.components.Patterns;
import h10.protocol.packets.*;
import h10.protocol.rules.Dispatcher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ProtocolDispatcher implements Dispatcher {

    private final Pattern ptUnit;
    private final Object lock;

    public ProtocolDispatcher() {
        ptUnit = Patterns.byName("ProtocolDataUnit");
        lock = new Object();
    }

    private BP00 handle(AP00 in) {

    }

    private BP02 handle(AP02 in) {

    }

    private BP05 handle(AP05 in) {

    }

    private BP07 handle(AP07 in) {

    }

    private BP10 handle(AP10 in) {

    }

    private BP12 handle(AP12 in) {

    }

    private BP14 handle(AP14 in) {

    }

    private BP15 handle(AP15 in) {

    }

    @Override
    public ProtocolDataUnit dispatchPacket(ProtocolDataUnit inPdu) {
        String cmd = inPdu.getCommandId().toUpperCase();
        ProtocolDataUnit result;
        switch (cmd) {
            case "AP00": result = new ProtocolDataUnit("BP00", handle((AP00) inPdu.getContent())); break;
            case "AP01": result = new ProtocolDataUnit("BP01", null); break;
            case "AP02": result = new ProtocolDataUnit("BP02", handle((AP02) inPdu.getContent())); break;
            case "AP03": result = new ProtocolDataUnit("BP03", null); break;
            case "AP05": result = new ProtocolDataUnit("BP05", handle((AP05) inPdu.getContent())); break;
            case "AP07": result = new ProtocolDataUnit("BP07", handle((AP07) inPdu.getContent())); break;
            case "AP10": result = new ProtocolDataUnit("BP10", handle((AP10) inPdu.getContent())); break;
            case "AP12": result = new ProtocolDataUnit("BP12", handle((AP12) inPdu.getContent())); break;
            case "AP14": result = new ProtocolDataUnit("BP14", handle((AP14) inPdu.getContent())); break;
            case "AP15": result = new ProtocolDataUnit("BP15", handle((AP15) inPdu.getContent())); break;
            //TODO обработка всех входящих комманд
            default: result = null; break;
        }
        return result;
    }

    @Override
    public List<ProtocolDataUnit> readPackets(InputStream in) {
        List<ProtocolDataUnit> result = new ArrayList<>();
        synchronized (lock) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                br.lines().forEach((line) -> {
                    Scanner sc = new Scanner(line);
                    while (sc.hasNext(ptUnit)) {
                        ProtocolDataUnit pdu = new ProtocolDataUnit();
                        pdu.parse(sc.next(ptUnit));
                        result.add(pdu);
                    }
                });
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }
        }
        lock.notifyAll();
        return result;
    }

    @Override
    public void sendPacket(OutputStream out, ProtocolDataUnit pdu) {

        synchronized (lock) {
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out))) {
                bw.write(pdu.toPacket());
                bw.flush();
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }
        }
        lock.notifyAll();
    }
}
