package h10.server.rules;

import h10.protocol.packets.ProtocolDataUnit;
import h10.protocol.subunits.AlarmEntry;
import h10.protocol.subunits.ContactEntry;
import h10.protocol.subunits.TestingType;
import h10.protocol.subunits.WorkingMode;
import h10.server.dto.LimitSettings;
import h10.server.dto.VoiceStatus;
import h10.server.models.Voice;

import java.io.InputStream;
import java.util.List;

public interface SmartDevice {

    String getImei();

    void setImei(String value);

    String getHumanReadableAddress();

    void setHumanReadableAddress(String value);

    Observe<ProtocolDataUnit> getPacketHandler();

    Observe<VoiceStatus> getVoiceResultHandler();

    Observe<Voice> getVoiceHandler();

    void where(int journalNo);

    void setMode(WorkingMode mode, int journalNo);

    void testHeartRate(int journalNo);

    void testBloodPressure(int journalNo);

    void testSaturationAndSugar(int journalNo);

    void setSosPhones(int journalNo, List<String> phones);

    void setWhiteList(int journalNo, List<ContactEntry> contacts);

    void setWhiteListEnabled(boolean value);

    void setAlarms(int journalNo, List<AlarmEntry> alarms, boolean enableAll);

    void setAutoTestingTimer(int journalNo, TestingType type, int minutes);

    void setLocationReportTimer(int journalNo, int minutes);

    void setLimits(LimitSettings limits);

    void reset(int journalNo);

    void calibrateBloodPressure(int journalNo, int systolicPressure, int diastolicPressure);

    void sendVoice(int journalNo, InputStream audio, String senderName);
}