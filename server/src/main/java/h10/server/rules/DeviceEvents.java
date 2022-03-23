package h10.server.rules;

import h10.protocol.packets.*;
import h10.server.models.Voice;

public interface DeviceEvents {

    void onGpsLocatingPkg(SmartDevice sender, AP01 pkg);

    void onBasesLocatingPkg(SmartDevice sender, AP02 pkg);

    void onHeartbeatPkg(SmartDevice sender, AP03 pkg);

    void onAlarmLocatingPkg(SmartDevice sender, AP10 pkg);

    void onHeartRatePkg(SmartDevice sender, AP49 pkg);

    void onHeartRateAndBpPkg(SmartDevice sender, APHT pkg);

    void onHrBpSaO2SugarPkg(SmartDevice sender, APHP pkg);

    void onSosNumbersResp(SmartDevice sender, AP12 resp);

    void onWhiteListSetResp(SmartDevice sender, AP14 resp);

    void onWhereResp(SmartDevice sender, AP16 resp);

    void onResetResp(SmartDevice sender, AP17 resp);

    void onSendAudioResp(SmartDevice sender, AP28 resp);

    void onModeResp(SmartDevice sender, AP33 resp);

    void onWhiteListSwitchResp(SmartDevice sender, AP84 resp);

    void onAlarmsResp(SmartDevice sender, AP85 resp);

    void onAutoTestingTimerResp(SmartDevice sender, AP86 resp);

    void onCalibrateBloodPressureResp(SmartDevice sender, APJZ resp);

    void onHeartRateResp(SmartDevice sender, APXL resp);

    void onBloodPressureResp(SmartDevice sender, APXY resp);

    void onUnknownPkg(SmartDevice sender, ProtocolDataUnit pkg);

    void onVoice(SmartDevice sender, Voice data);
}