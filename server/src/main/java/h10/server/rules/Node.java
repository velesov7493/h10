package h10.server.rules;

public interface Node {

    NodeConfig getConfig();

    void setConfig(NodeConfig value);

    void setDeviceLoginHandler(Observe<SmartDevice> handler);

    void setDeviceLogoutHandler(Observe<SmartDevice> handler);

    void start();

    void stop();
}