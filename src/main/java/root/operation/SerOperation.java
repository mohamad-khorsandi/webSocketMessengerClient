package root.operation;

import root.utils.connections.ConnectionPack;
import root.utils.connections.NormalConnectionPack;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

abstract public class SerOperation extends Operation{

    protected SerOperation() throws IOException {
        shouldClosed = true;
        con = ConnectionPack.newNormConnectionPack("127.0.0.1", 8000);
    }

    protected SerOperation(NormalConnectionPack con){
        shouldClosed = true;
        this.con = con;
    }

    protected NormalConnectionPack con;

    @Override
    void closeIfNeeded() throws IOException {
        if(!shouldClosed)
            return;
        con.close();
    }

    @Override
    public Object call() throws Exception {
        con.format(cmd.toString());
        Object result = operate();
        closeIfNeeded();
        System.out.println(cmd + " was successful");
        return result;
    }
}
