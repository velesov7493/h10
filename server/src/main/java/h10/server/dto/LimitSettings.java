package h10.server.dto;

public class LimitSettings {

    private int heartRateLow;
    private int heartRateHigh;
    private int diastolicBpLow;
    private int diastolicBpHigh;
    private int systolicBpLow;
    private int systolicBpHigh;

    public int getHeartRateLow() {
        return heartRateLow;
    }

    public void setHeartRateLow(int heartRateLow) {
        this.heartRateLow = heartRateLow;
    }

    public int getHeartRateHigh() {
        return heartRateHigh;
    }

    public void setHeartRateHigh(int heartRateHigh) {
        this.heartRateHigh = heartRateHigh;
    }

    public int getDiastolicBpLow() {
        return diastolicBpLow;
    }

    public void setDiastolicBpLow(int diastolicBpLow) {
        this.diastolicBpLow = diastolicBpLow;
    }

    public int getDiastolicBpHigh() {
        return diastolicBpHigh;
    }

    public void setDiastolicBpHigh(int diastolicBpHigh) {
        this.diastolicBpHigh = diastolicBpHigh;
    }

    public int getSystolicBpLow() {
        return systolicBpLow;
    }

    public void setSystolicBpLow(int systolicBpLow) {
        this.systolicBpLow = systolicBpLow;
    }

    public int getSystolicBpHigh() {
        return systolicBpHigh;
    }

    public void setSystolicBpHigh(int systolicBpHigh) {
        this.systolicBpHigh = systolicBpHigh;
    }
}
