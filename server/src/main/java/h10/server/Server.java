package h10.server;

import h10.server.rules.Node;
import h10.server.rules.Observe;
import h10.server.rules.SmartDevice;

/**
 * Управляющий (главный) поток
 * Управляет жизненным циклом всех подключений (сокетов)
 * Ждет подключения и передает сокет потоку-обработчику
 * Ставит исходящие PDU в список на отправку
 */
public class Server extends Thread implements Node {

    @Override
    public int getServerPort() {
        return 0;
    }

    @Override
    public void setServerPort(int value) {

    }

    @Override
    public String getServerHost() {
        return null;
    }

    @Override
    public void setServerHost(String value) {

    }

    @Override
    public long getWaitConnectTimeout() {
        return 0;
    }

    @Override
    public void setWaitConnectTimeout(long milliseconds) {

    }

    @Override
    public long getWaitResponseTimeout() {
        return 0;
    }

    @Override
    public void setWaitResponseTimeout(long milliseconds) {

    }

    @Override
    public long getKeepAliveTimeout() {
        return 0;
    }

    @Override
    public void setKeepAliveTimeout(long milliseconds) {

    }

    @Override
    public void setDeviceLoginHandler(Observe<SmartDevice> handler) {

    }

    @Override
    public void setDeviceLogoutHandler(Observe<SmartDevice> handler) {

    }
}
