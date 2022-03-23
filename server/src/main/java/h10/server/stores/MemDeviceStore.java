package h10.server.stores;

import h10.server.rules.SmartDevice;
import h10.server.stores.rules.DeviceStore;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemDeviceStore implements DeviceStore {

    private static final class Holder {
        private static final MemDeviceStore INSTANCE = new MemDeviceStore();
    }

    private final Map<String, SmartDevice> devices;

    private MemDeviceStore() {
        devices = new ConcurrentHashMap<>();
    }

    public DeviceStore getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void add(SmartDevice value) {
        devices.put(value.getImei(), value);
    }

    @Override
    public int getDevicesCount() {
        return devices.size();
    }

    @Override
    public SmartDevice findByImei(String imei) {
        return devices.get(imei);
    }

    @Override
    public void deleteByImei(String imei) {
        devices.remove(imei);
    }
}