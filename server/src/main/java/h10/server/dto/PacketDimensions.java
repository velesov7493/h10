package h10.server.dto;

public class PacketDimensions {

    private String imei;
    private String command;
    private Integer journalNo;

    public static PacketDimensions of(String imei, String command, Integer journalNo) {
        PacketDimensions result = new PacketDimensions();
        result.command = command;
        result.imei = imei;
        result.journalNo = journalNo;
        return result;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Integer getJournalNo() {
        return journalNo;
    }

    public void setJournalNo(Integer journalNo) {
        this.journalNo = journalNo;
    }
}
