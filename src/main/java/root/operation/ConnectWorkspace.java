package root.operation;

import lombok.NoArgsConstructor;
import root.utils.AutoFormatter;
import root.Client;
import root.Workspace;
import root.utils.QueueScanner;

import static root.utils.Utils.throwIfResIsNotOK;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class ConnectWorkspace extends SerOperation {
    public ConnectWorkspace() throws IOException {
    }

    @Override
    Object operate() throws Exception {
        //1 ---------------------------
        Workspace ws = new Workspace();
        ws.name = Client.sc.next();
        //2 ---------------------------
        new Login(socket, send, receive).operate();
        send.format(ws.name);
        //3 ---------------------------
        if (!receive.next().equals("OK"))
            throw new Exception();
        ws.ip = receive.next();
        ws.port = receive.nextInt();
        String token = receive.next();
        //4 ---------------------------
        send.close();
        receive.close();
        socket.close();

        ws.socket = new Socket(ws.ip, ws.port);
        ws.send = new AutoFormatter(ws.socket.getOutputStream());
        ws.receive = new QueueScanner(ws.socket.getInputStream());
        ws.send.format("connect %s", token);
        //7,8,9 ---------------------------
        String response = ws.receive.next();
        if (response.equals("username?")){
            System.out.println("please input your username:");
            ws.send.format(Client.sc.next());
            throwIfResIsNotOK(ws.receive);
        } else
            throwIfResIsNotOK(ws.receive);

        Client.curWorkspace = ws;
        Client.executor.submit(() ->{
            Client.receiveFromWorkspace();
            return null;
        });
        return null;
    }
}
