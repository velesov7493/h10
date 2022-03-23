package h10.server.components;

import h10.protocol.packets.ProtocolDataUnit;
import h10.protocol.subunits.AlarmEntry;
import h10.protocol.subunits.ContactEntry;
import h10.protocol.subunits.TestingType;
import h10.protocol.subunits.WorkingMode;
import h10.server.dto.LimitSettings;
import h10.server.dto.VoiceStatus;
import h10.server.models.Voice;
import h10.server.rules.DeviceEvents;
import h10.server.rules.Observe;
import h10.server.rules.SmartDevice;

import java.io.InputStream;
import java.net.Socket;
import java.util.List;

public class Device implements SmartDevice {

    /**
     * Получатель входящих пакетов
     */
    private static final class InPackets implements Observe<ProtocolDataUnit> {

        @Override
        public void receive(ProtocolDataUnit model) {

        }
    }

    private static final class InVoices implements Observe<Voice> {

        @Override
        public void receive(Voice model) {

        }
    }

    private static final class InVoiceResponses implements Observe<VoiceStatus> {

        @Override
        public void receive(VoiceStatus model) {

        }
    }

    private final String remoteIp;
    private final Socket s;
    private Observe<ProtocolDataUnit> out;
    private DeviceEvents eventHandlers;
    private String imei;
    private String address;

    public Device(Socket socket) {
        s = socket;
        imei = "000000000000000";
        remoteIp = s.getRemoteSocketAddress().toString();
    }

    @Override
    public String getHumanReadableAddress() {
        return address;
    }

    @Override
    public void setHumanReadableAddress(String value) {
        address = value;
    }

    @Override
    public Observe<ProtocolDataUnit> getPacketHandler() {
        return new InPackets();
    }

    @Override
    public Observe<VoiceStatus> getVoiceResultHandler() {
        return new InVoiceResponses();
    }

    @Override
    public Observe<Voice> getVoiceHandler() {
        return new InVoices();
    }

    @Override
    public void where(int journalNo) {

    }

    @Override
    public void setMode(WorkingMode mode, int journalNo) {

    }

    @Override
    public void testHeartRate(int journalNo) {

    }

    @Override
    public void testBloodPressure(int journalNo) {

    }

    @Override
    public void testSaturationAndSugar(int journalNo) {

    }

    @Override
    public void setSosPhones(int journalNo, List<String> phones) {

    }

    @Override
    public void setWhiteList(int journalNo, List<ContactEntry> contacts) {

    }

    @Override
    public void setWhiteListEnabled(boolean value) {

    }

    @Override
    public void setAlarms(int journalNo, List<AlarmEntry> alarms, boolean enableAll) {

    }

    @Override
    public void setAutoTestingTimer(int journalNo, TestingType type, int minutes) {

    }

    @Override
    public void setLocationReportTimer(int journalNo, int minutes) {

    }

    @Override
    public void setLimits(LimitSettings limits) {

    }

    @Override
    public void reset(int journalNo) {

    }

    @Override
    public void calibrateBloodPressure(int journalNo, int systolicPressure, int diastolicPressure) {

    }

    @Override
    public void sendVoice(int journalNo, InputStream audio, String senderName) {

    }

    @Override
    public String getImei() {
        return imei;
    }

    @Override
    public void setImei(String value) {
        imei = value;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public DeviceEvents getEventHandlers() {
        return eventHandlers;
    }

    public void setEventHandlers(DeviceEvents eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    public Socket getSocket() {
        return s;
    }
}
