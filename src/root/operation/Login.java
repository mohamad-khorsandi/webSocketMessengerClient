package root.operation;

import root.Client;
import root.utils.AutoFormatter;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Login extends SerOperation{
    protected Login() throws IOException {
        super();
    }
    public Login(Socket socket, AutoFormatter send, Scanner receive) {
        super(socket, send, receive);
    }

    @Override
    Object operate() throws Exception {
        //1 -------------------------
        send.format("%s %s", Client.phone, Client.pass);
        //2 -------------------------
        String result = receive.nextLine();
        if (!result.equals("OK"))
            throw new Exception(result);
        return null;
    }
}
