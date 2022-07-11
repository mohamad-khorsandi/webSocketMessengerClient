package root.operation;

import root.utils.AutoFormatter;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

abstract public class SerOperation extends Operation{

    protected SerOperation() throws IOException {
        this.socket = new Socket("localhost", 8000);
        this.receive = new Scanner(socket.getInputStream());
        this.send = new AutoFormatter(socket.getOutputStream());
    }

    protected SerOperation(Socket socket, AutoFormatter send, Scanner receive){
        this.socket = socket;
        this.send = send;
        this.receive = receive;
    }

    {
        shouldClosed = true;
    }
}
