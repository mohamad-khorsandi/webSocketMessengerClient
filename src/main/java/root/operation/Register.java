package root.operation;
import root.Client;
import java.io.IOException;

public class Register extends SerOperation{
    protected Register() throws IOException {
    }

    @Override
    Object operate() throws Exception {
        //2 -------------------------
        send.format("%s %s", Client.phone, Client.pass);
        //3 -------------------------
        receive.next();//ok
        return null;
    }
}
