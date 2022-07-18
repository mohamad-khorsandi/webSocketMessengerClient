package root.operation;

import root.Client;
import root.Workspace;
import root.utils.connections.ConnectionPack;
import root.utils.connections.MultiReceiveConnectionPack;
import java.io.IOException;

public class ConnectWorkspace extends SerOperation {
    public ConnectWorkspace() throws IOException {
    }

    @Override
    Object operate() throws Exception {
        //1 ---------------------------
        Workspace ws = new Workspace();
        ws.name = Client.sc.next();
        //2 ---------------------------
        new Login(con).operate();
        con.format(ws.name);
        //3 ---------------------------
        con.throwIfResIsNotOK();
        ws.ip = con.next();
        ws.port = con.nextInt();
        String token = con.next();
        //4 ---------------------------
        con.close();

        ws.con = ConnectionPack.newMulRecConnectionPack(ws.ip, ws.port);
        ws.con.format("connect %s", token);
        //7,8,9 ---------------------------
        String response = ws.con.next();
        if (response.equals("username?")){
            System.out.println("please input your username:");
            ws.con.format(Client.sc.next());
            ws.con.throwIfResIsNotOK();
        } else
            ws.con.throwIfResIsNotOK();

        Client.curWorkspace = ws;
        Client.executor.submit(Client::receiveMsgFromWorkspace);
        return null;
    }
}
