package h10.server.configs;

import h10.server.rules.NodeConfig;

public class DefaultNodeConfig implements NodeConfig {

    private static final DefaultNodeConfig INSTANCE = new DefaultNodeConfig();

    private int serverPort;
    private String serverHost;
    private int serverTimeZone;
    private long waitConnectTimeout;
    private long waitResponseTimeout;
    private long keepAliveTimeout;

    private DefaultNodeConfig() {
        serverPort = 5033;
        serverHost = "127.0.0.1";
        serverTimeZone = 3;
        waitResponseTimeout = 40000L;
        waitConnectTimeout = 16000L;
        keepAliveTimeout = 180000L;
    }

    public static NodeConfig getInstance() {
        return INSTANCE;
    }

    @Override
    public int getServerPort() {
        return serverPort;
    }

    @Override
    public void setServerPort(int value) {
        serverPort = value;
    }

    @Override
    public String getServerHost() {
        return serverHost;
    }

    @Override
    public void setServerHost(String value) {
        serverHost = value;
    }

    @Override
    public int getServerTimeZone() {
        return serverTimeZone;
    }

    @Override
    public void setServerTimeZone(int value) {
        serverTimeZone = value;
    }

    @Override
    public long getWaitConnectTimeout() {
        return waitConnectTimeout;
    }

    @Override
    public void setWaitConnectTimeout(long milliseconds) {
        waitConnectTimeout = milliseconds;
    }

    @Override
    public long getWaitResponseTimeout() {
        return waitResponseTimeout;
    }

    @Override
    public void setWaitResponseTimeout(long milliseconds) {
        waitResponseTimeout = milliseconds;
    }

    @Override
    public long getKeepAliveTimeout() {
        return keepAliveTimeout;
    }

    @Override
    public void setKeepAliveTimeout(long milliseconds) {
        keepAliveTimeout = milliseconds;
    }
}
