package root.operation;

import root.Client;

abstract public class WsOperation extends Operation{
    public WsOperation() {
        this.socket = Client.curWorkspace.socket;
        this.receive = Client.curWorkspace.receive;
        this.send = Client.curWorkspace.send;
        this.shouldClosed = false;
    }
}
