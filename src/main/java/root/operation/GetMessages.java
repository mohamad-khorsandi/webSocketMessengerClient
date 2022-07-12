package root.operation;

import root.Client;
import root.utils.Utils;

public class GetMessages extends WsOperation{
    @Override
    Object operate() throws Exception {
        //1 --------------------------------
        send.format(Client.sc.next());
        //1 --------------------------------
        Utils.throwIfResIsNotOK(receive);
        System.out.println(receive.nextLine());
        return true;
    }
}
