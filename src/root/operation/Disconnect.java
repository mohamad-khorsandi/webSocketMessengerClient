package root.operation;

public class Disconnect extends WsOperation{
    @Override
    Object operate() {
        this.shouldClosed = true;
        return null;
    }
}
