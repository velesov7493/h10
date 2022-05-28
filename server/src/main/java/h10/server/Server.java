package h10.server;

import h10.server.configs.DefaultNodeConfig;
import h10.server.rules.Node;
import h10.server.rules.NodeConfig;
import h10.server.rules.Observe;
import h10.server.rules.SmartDevice;

/**
 * Управляющий (главный) поток
 * Управляет жизненным циклом всех подключений (сокетов)
 * Ждет подключения и передает сокет потоку-обработчику
 * Ставит исходящие PDU в список на отправку
 */
public class Server extends Thread implements Node {

    private NodeConfig config;

    public Server() {
        config = DefaultNodeConfig.getInstance();
    }

    @Override
    public NodeConfig getConfig() {
        return config;
    }

    @Override
    public void setConfig(NodeConfig value) {
        config = value;
    }

    @Override
    public void setDeviceLoginHandler(Observe<SmartDevice> handler) {

    }

    @Override
    public void setDeviceLogoutHandler(Observe<SmartDevice> handler) {

    }
}
