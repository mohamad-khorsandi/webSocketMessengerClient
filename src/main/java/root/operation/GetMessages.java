package root.operation;

import root.Client;
import root.utils.Utils;

public class GetMessages extends WsOperation{
    @Override
    Object operate() throws Exception {
        //1 --------------------------------
        con.format(Client.sc.next());
        //1 --------------------------------
        con.throwIfResIsNotOK();
        System.out.println(con.nextLine());
        return true;
    }
}
