package root;

import root.operation.Command;
import root.operation.Operation;
import java.util.Scanner;

public class Client {
    static public Scanner sc = new Scanner(System.in);
    static public String pass;
    static public String phone;
    static public Workspace curWorkspace;
    public static void main(String[] args) throws Exception {
        pass = args[0];
        phone = args[1];

        while (true){
            Command cmd = Command.type(sc.next());
            Operation.newOperation(cmd).call();
        }
    }
}