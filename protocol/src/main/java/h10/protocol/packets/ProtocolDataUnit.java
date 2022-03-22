package h10.protocol.packets;

import h10.protocol.rules.Parseable;

/**
 * Объект базового пакета протокола H10
 * Служит оберткой всех остальных пакетов
 */

public class ProtocolDataUnit implements Parseable {

    private String commandId;
    private Parseable content;
    private boolean haveContent;

    public ProtocolDataUnit() {
        haveContent = true;
        commandId = "";
    }

    public ProtocolDataUnit(String aCommandId) {
        commandId = aCommandId;
        haveContent = true;
        createContentByCommandId();
    }

    public ProtocolDataUnit(String aCommandId, Parseable aContent) {
        commandId = aCommandId;
        content = aContent;
        haveContent = content != null;
    }

    public ProtocolDataUnit(Parseable aContent) {
        commandId = "";
        content = aContent;
        haveContent = true;
    }

    private void createContentByCommandId() {
        try {
            content =
                    (Parseable) Class.forName("h10.protocol.packets." + commandId)
                    .getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    public String getCommandId() {
        return commandId;
    }

    public <T extends Parseable> T getContent() {
        return (T) content;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    @Override
    public void parse(String packet) throws IllegalArgumentException {
        if (!(packet.startsWith("IW") && packet.endsWith("#"))) {
            throw new IllegalArgumentException("Неправильная структура пакета:" + packet);
        }
        commandId = packet.substring(2, 6);
        int shift = ',' == packet.charAt(6) ? 1 : 0;
        if (haveContent) {
            if (content == null) { createContentByCommandId(); }
            content.parse(packet.substring(6 + shift, packet.length() - 1));
        }
    }

    @Override
    public String toPacket() {
        String msg = content != null ? content.toPacket() : "";
        return "IW" + commandId + msg + "#";
    }
}