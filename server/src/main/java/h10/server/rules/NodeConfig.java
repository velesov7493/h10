package h10.server.rules;

public interface NodeConfig {

    int getServerPort();

    void setServerPort(int value);

    String getServerHost();

    void setServerHost(String value);

    int getServerTimeZone();

    void setServerTimeZone(int value);

    long getWaitConnectTimeout();

    void setWaitConnectTimeout(long milliseconds);

    long getWaitResponseTimeout();

    void setWaitResponseTimeout(long milliseconds);

    long getKeepAliveTimeout();

    void setKeepAliveTimeout(long milliseconds);
}
