package h10.server.components;

import h10.protocol.components.Patterns;
import h10.protocol.packets.*;
import h10.server.configs.DefaultNodeConfig;
import h10.server.models.Voice;
import h10.server.rules.Dispatcher;
import h10.server.rules.NodeConfig;
import h10.server.rules.SmartDevice;
import h10.server.stores.JdbcVoiceStore;
import h10.server.stores.rules.VoiceStore;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ProtocolDispatcher implements Dispatcher {

    private final NodeConfig config;
    private final SmartDevice device;
    private final VoiceStore voices;
    private final Pattern ptUnit;

    public ProtocolDispatcher(SmartDevice aDevice) {
        voices = JdbcVoiceStore.getInstance();
        config = DefaultNodeConfig.getInstance();
        device = aDevice;
        ptUnit = Patterns.byName("ProtocolDataUnit");
    }

    private BP05 getVoicesCount() {
        BP05 content = new BP05();
        content.setRecordsCount(voices.getCountByImei(device.getImei()));
        return content;
    }

    private BPVL getVoicesList() {
        BPVL content = new BPVL();
        List<Voice> list = voices.findAllByImei(device.getImei());
        list.forEach((v) -> content.addVoice(v.toEntry()));
        return content;
    }

    private ProtocolDataUnit getNextOutVoicePacket() {
        BP28 content = null;

        Iterator<BP28> outVoice =
                voices.findFirstByImei(device.getImei()).outVoiceIterator();
        if (outVoice != null && outVoice.hasNext()) {
            content = outVoice.next();
        }
        return  content != null ? new ProtocolDataUnit("BP28", content) : null;
    }

    private void cmdUnknown(String cmd) {
        System.err.println("Неизвестная команда: " + cmd);
    }

    private BP00 handle(AP00 in) {
        device.setImei(in.getImei());
        BP00 result = new BP00();
        result.setDateTime(LocalDateTime.now());
        result.setTimeZone(config.getServerTimeZone());
        return result;
    }

    private BP02 handle(AP02 in) {
        BP02 result = new BP02();
        if (in.isAddressNeeded()) {
            result.setAddress(device.getHumanReadableAddress());
        }
        return result;
    }

    /**
     * Обработчик входящей команды AP05
     * @param in AP05
     * @return BP05 или BPVL или BP28 в зависимости от типа запроса
     */
    private ProtocolDataUnit handle(AP05 in) {
        ProtocolDataUnit result = null;
        switch (in.getRequestType()) {
            case READY: result = getNextOutVoicePacket(); break;
            case LIST: result = new ProtocolDataUnit("BPVL", getVoicesList()); break;
            case COUNT: result = new ProtocolDataUnit("BP05", getVoicesCount()); break;
            default: break;
        }
        return result;
    }

    private BP07 handle(AP07 in) {
        BP07 result = new BP07();
        result.setSuccess(true);
        result.setNumber(in.getNumber());
        result.setTotalPackets(in.getTotalPackets());
        result.setDateTime(LocalDateTime.now());
        return result;
    }

    private BP10 handle(AP10 in) {
        BP10 result = new BP10();
        if (in.isAddressNeeded()) {
            result.setAddress(device.getHumanReadableAddress());
        }
        return result;
    }

    private BPTM handle(APTM in) {
        BPTM result = new BPTM();
        result.setDateTime(LocalDateTime.now());
        result.setLatitude(0D);
        result.setLongitude(0D);
        return result;
    }

    @Override
    public ProtocolDataUnit dispatchPacket(ProtocolDataUnit inPdu) {
        String cmd = inPdu.getCommandId().toUpperCase();
        ProtocolDataUnit result = null;
        switch (cmd) {
            case "AP00": result = new ProtocolDataUnit(
                    "BP00", handle((AP00) inPdu.getContent())
            ); break;
            case "AP01": result = new ProtocolDataUnit("BP01", null); break;
            case "AP02": result = new ProtocolDataUnit(
                    "BP02", handle((AP02) inPdu.getContent())
            ); break;
            case "AP03": result = new ProtocolDataUnit("BP03", null); break;
            case "AP05": result = handle((AP05) inPdu.getContent()); break;
            case "AP07": result = new ProtocolDataUnit(
                    "BP07", handle((AP07) inPdu.getContent())
            ); break;
            case "AP10": result = new ProtocolDataUnit(
                    "BP10", handle((AP10) inPdu.getContent())
            ); break;
            case "AP12": break;
            case "AP14": break;
            case "AP15": break;
            case "AP16": break;
            case "AP17": break;
            case "AP28": break;
            case "AP33": break;
            case "AP49": result = new ProtocolDataUnit("BP49", null); break;
            case "AP84": break;
            case "AP85": break;
            case "AP86": break;
            case "AP89": break;
            case "APHP": result = new ProtocolDataUnit("BPHP", null); break;
            case "APHT": result = new ProtocolDataUnit("BPHT", null); break;
            case "APJZ": break;
            case "APTM": result = new ProtocolDataUnit(
                    "BPTM", handle((APTM) inPdu.getContent())
            ); break;
            case "APXL": break;
            case "APXY": break;
            case "APXZ": break;
            default: cmdUnknown(cmd); break;
        }
        return result;
    }

    @Override
    public List<ProtocolDataUnit> readPackets(InputStream in) {
        List<ProtocolDataUnit> result = new ArrayList<>();
        synchronized (device) {
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
        device.notifyAll();
        return result;
    }

    @Override
    public void sendPacket(OutputStream out, ProtocolDataUnit pdu) {

        synchronized (device) {
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out))) {
                bw.write(pdu.toPacket());
                bw.flush();
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }
        }
        device.notifyAll();
    }
}