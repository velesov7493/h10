package h10.server.models;

import h10.protocol.packets.ProtocolDataUnit;
import h10.protocol.rules.Addressable;
import h10.protocol.rules.Journalable;
import h10.protocol.rules.Sequenced;
import h10.protocol.rules.VoiceData;

import java.util.Objects;

public class Packet {

    private Long id;
    private String imei;
    private String command;
    private Integer journalNo;
    private Integer sequenceNo;
    private Integer totalPackets;
    private String data;
    private byte[] voice;

    public Packet(ProtocolDataUnit source) {
        command = source.getCommandId();
        data = source.toPacket();
        Class clazz = source.getContent().getClass();
        if (clazz.isAssignableFrom(Addressable.class)) {
            Addressable content = source.getContent();
            imei = content.getImei();
        }
        if (clazz.isAssignableFrom(Journalable.class)) {
            Journalable content = source.getContent();
            journalNo = content.getJournalNo();
        }
        if (clazz.isAssignableFrom(Sequenced.class)) {
            Sequenced content = source.getContent();
            sequenceNo = content.getNumber();
            totalPackets = content.getTotalPackets();
        }
        if (clazz.isAssignableFrom(VoiceData.class)) {
            VoiceData content = source.getContent();
            voice = content.getAudio();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public Integer getTotalPackets() {
        return totalPackets;
    }

    public void setTotalPackets(Integer totalPackets) {
        this.totalPackets = totalPackets;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public byte[] getVoice() {
        return voice;
    }

    public void setVoice(byte[] voice) {
        this.voice = voice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Packet that = (Packet) o;
        return
                Objects.equals(imei, that.imei)
                && command.equals(that.command)
                && Objects.equals(journalNo, that.journalNo)
                && Objects.equals(sequenceNo, that.sequenceNo)
                && Objects.equals(totalPackets, that.totalPackets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imei, command, journalNo, sequenceNo, totalPackets);
    }
}