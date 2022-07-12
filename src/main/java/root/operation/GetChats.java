package root.operation;

import root.utils.Utils;

public class GetChats extends WsOperation{
    @Override
    Object operate() throws Exception{
        //1 --------------------------------
        send.format(cmd.toString());
        //2 --------------------------------
        Utils.throwIfResIsNotOK(receive);
        String json = receive.nextLine();
        System.out.println(json);
        return null;
    }
}
