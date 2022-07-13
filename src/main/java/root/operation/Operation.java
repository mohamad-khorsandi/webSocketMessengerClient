package root.operation;

import root.utils.connections.NormalConnectionPack;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Callable;

abstract public class Operation implements Callable<Object> {

    protected Command cmd;
    protected boolean shouldClosed;

    public static Operation newOperation(Command cmd) throws Exception {
        Operation operation;
        switch (cmd){
            case REGISTER:
                operation = new Register();
                break;

            case LOGIN:
                operation = new Login();
                break;

            case CREATE_WS:
                operation = new CreateWorkspace();
                break;

            case CONNECT_WP:
                operation = new ConnectWorkspace();
                break;

            case DISCONNECT:
                operation = new Disconnect();
                break;

            case SEND_MSG:
                operation = new SendMessage();
                break;

            case RECEIVE_MSG:
                operation = new ReceiveMessage();
                break;

            case GET_CHATS:
                operation = new GetChats();
                break;

            case GET_MESSAGES:
                operation = new GetMessages();
                break;
            default:
                throw new Exception("there is no such a operation");
        }
        operation.cmd = cmd;
        return operation;
    }

    abstract Object operate() throws Exception;

//    @Override
//    public Object call() throws Exception {
//        con.format(cmd.toString());
//        Object result = operate();
//        closeIfNeeded();
//        System.out.println(cmd + " was successful");
//        return result;
//    }
@Override
    abstract public Object call() throws Exception;
    abstract void closeIfNeeded() throws IOException;
}