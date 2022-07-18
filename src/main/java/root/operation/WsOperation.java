package root.operation;

import root.Client;
import root.utils.connections.MultiReceiveConnectionPack;

import java.io.IOException;

abstract public class WsOperation extends Operation{
    public WsOperation() {
        this.con = Client.curWorkspace.con;
        this.shouldClosed = false;
    }

    MultiReceiveConnectionPack con;

    @Override
    void closeIfNeeded(){
        if(!shouldClosed)
            return;
        con.close();
    }

    public void sendCmd(){
        con.format(cmd.toString());
    }
}
