package h10.server.rules;

public interface Node {

    int getServerPort();
    void setServerPort(int value);
    String getServerHost();
    void setServerHost(String value);
    long getWaitConnectTimeout();
    void setWaitConnectTimeout(long milliseconds);
    long getWaitResponseTimeout();
    void setWaitResponseTimeout(long milliseconds);
    long getKeepAliveTimeout();
    void setKeepAliveTimeout(long milliseconds);
    void setDeviceLoginHandler(Observe<SmartDevice> handler);
    void setDeviceLogoutHandler(Observe<SmartDevice> handler);
    void start();
    void stop();
}