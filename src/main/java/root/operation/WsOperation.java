package root.operation;

import root.Client;
import root.utils.QueueScanner;

import java.io.IOException;

abstract public class WsOperation extends Operation{
    public WsOperation() {
        this.socket = Client.curWorkspace.socket;
        this.receive = Client.curWorkspace.receive;
        this.send = Client.curWorkspace.send;
        this.shouldClosed = false;
    }

    QueueScanner receive;

    @Override
    void closeIfNeeded() throws IOException {
        if(!shouldClosed)
            return;
        receive.close();
        send.close();
        socket.close();
    }

}
