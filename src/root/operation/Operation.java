package root.operation;

import root.utils.AutoFormatter;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Callable;

abstract public class Operation implements Callable<Object> {

    protected Command cmd;
    protected Scanner receive;
    protected AutoFormatter send;
    protected Socket socket;
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

            default:
                throw new Exception("there is no such a operation");
        }
        operation.cmd = cmd;
        return operation;
    }

    abstract Object operate() throws Exception;

    @Override
    public Object call() throws Exception {
        send.format(cmd.toString());
        Object result = operate();
        closeIfNeeded();
        System.out.println(cmd + " was successful");
        return result;
    }

    private void closeIfNeeded() throws IOException {
        if(!shouldClosed)
            return;
        receive.close();
        send.close();
        socket.close();
    }
}