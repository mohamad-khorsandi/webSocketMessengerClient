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
    public Object call() throws Exception {
        con.format(cmd.toString());
        Object result = operate();
        closeIfNeeded();
        System.out.println(cmd + " was successful");
        return result;
    }
    @Override
    void closeIfNeeded(){
        if(!shouldClosed)
            return;
        con.close();
    }

}
