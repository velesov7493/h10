package h10.protocol.packets;

import h10.protocol.rules.Parseable;
import h10.protocol.subunits.LbsData;

/**
 * APTM - запрос широты и долготы вышки сотовой связи.
 * Ответ - BPTM
 */
public class APTM implements Parseable {

    private LbsData lbs;

    public APTM() {
        lbs = new LbsData();
    }

    @Override
    public void parse(String packet) {
        lbs.parse(packet);
    }

    @Override
    public String toPacket() {
        return lbs.toPacket();
    }

    public int getMobileCountryCode() {
        return lbs.getMobileCountryCode();
    }

    public void setMobileCountryCode(int mobileCountryCode) {
        lbs.setMobileCountryCode(mobileCountryCode);
    }

    public int getMobileNetworkCode() {
        return lbs.getMobileNetworkCode();
    }

    public void setMobileNetworkCode(int mobileNetworkCode) {
        lbs.setMobileNetworkCode(mobileNetworkCode);
    }

    public int getLocalAreaCode() {
        return lbs.getLocalAreaCode();
    }

    public void setLocalAreaCode(int localAreaCode) {
        lbs.setLocalAreaCode(localAreaCode);
    }

    public int getCellId() {
        return lbs.getCellId();
    }

    public void setCellId(int cellId) {
        lbs.setCellId(cellId);
    }

    public boolean isMoving() {
        return lbs.isMoving();
    }
}
