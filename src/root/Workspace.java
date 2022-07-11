package root;

import root.utils.AutoFormatter;

import java.net.Socket;
import java.util.Scanner;

public class Workspace {
    public Workspace(String hostIp, int port) {
        this.ip = hostIp;
        this.port = port;
    }

    public Workspace() {
    }
    public String name;
    public String ip;
    public int port;
    public Socket socket;
    public Scanner receive;
    public AutoFormatter send;
}
