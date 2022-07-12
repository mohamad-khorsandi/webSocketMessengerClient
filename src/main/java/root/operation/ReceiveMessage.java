package root.operation;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class ReceiveMessage extends WsOperation{
    @Override
    Object operate() throws ExecutionException, InterruptedException {
        String message = receive.nextLine();
        System.out.println(cmd + " " + message);
        return null;
    }

    @Override
    public Object call() throws ExecutionException, InterruptedException, IOException {
        Object result = operate();
        closeIfNeeded();
        System.out.println(cmd + " was successful");
        return result;
    }

}
