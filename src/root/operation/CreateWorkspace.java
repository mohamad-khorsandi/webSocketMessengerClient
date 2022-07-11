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
        new Login(socket, send, receive).operate();
        send.format(workspaceName);
        //5 -------------------------
        String result = receive.next();
        if (!result.equals("OK"))
            throw new Exception(result);
        String ip = receive.next();
        int port = receive.nextInt();
        return null;
    }
}
