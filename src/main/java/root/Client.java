package root;

import root.operation.Command;
import root.operation.Operation;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    static public Scanner sc = new Scanner(System.in);
    static public String pass;
    static public String phone;
    static public Workspace curWorkspace;
    static public ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        pass = args[0];
        phone = args[1];
        sendCommand();
    }

    static void sendCommand() throws Exception {
        while (true){
            Command cmd = Command.type(sc.next());
            Operation.newOperation(cmd).call();
        }
    }

    public static void receiveFromWorkspace() throws Exception {
        while (true){
            Command cmd = Command.type(curWorkspace.receive.waitForNext("receive-message"));
            Operation.newOperation(cmd).call();
            //todo what if curWorkspace.receive changes?
        }
    }
}