package root;

import root.utils.connections.MultiReceiveConnectionPack;

import java.net.Socket;

public class Workspace {
    public Workspace() {
    }

    public String name;
    public String ip;
    public int port;
    public MultiReceiveConnectionPack con;
}
