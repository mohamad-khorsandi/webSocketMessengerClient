package root.operation;

import root.Client;
import root.utils.connections.NormalConnectionPack;

import java.io.IOException;

public class Login extends SerOperation{
    protected Login() throws IOException {
        super();
    }
    public Login(NormalConnectionPack con) {
        super(con);
    }

    @Override
    Object operate() throws Exception {
        //1 -------------------------
        con.format("%s %s", Client.phone, Client.pass);
        //2 -------------------------
        String result = con.nextLine();
        if (!result.equals("OK"))
            throw new Exception(result);
        return null;
    }
}
