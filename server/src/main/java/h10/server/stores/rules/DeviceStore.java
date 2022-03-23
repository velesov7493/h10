package h10.server.stores.rules;

import h10.server.rules.SmartDevice;

public interface DeviceStore {

    int getDevicesCount();

    void add(SmartDevice value);

    SmartDevice findByImei(String imei);

    void deleteByImei(String imei);
}
