package root.operation;

import root.Client;

import java.io.IOException;

import static root.operation.Command.LOGIN;

public class CreateWorkspace extends SerOperation{
    public CreateWorkspace() throws IOException {
    }

    @Override
    Object operate() throws Exception {
        //2 -------------------------
        String workspaceName = Client.sc.next();
        new Login(con).operate();
        con.format(workspaceName);
        //5 -------------------------
        con.throwIfResIsNotOK();
        String ip = con.next();
        int port = con.nextInt();
        return null;
    }
}
