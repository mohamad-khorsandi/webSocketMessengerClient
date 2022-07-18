package root.operation;

import root.utils.connections.ConnectionPack;
import root.utils.connections.NormalConnectionPack;

import java.io.IOException;

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
    void closeIfNeeded() {
        if(!shouldClosed)
            return;
        con.close();
    }

    public void sendCmd(){
        con.format(cmd.toString());
    }
}
