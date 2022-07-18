package root.operation;

public class GetChats extends WsOperation{
    @Override
    Object operate() throws Exception{
        //2 --------------------------------
        con.throwIfResIsNotOK();
        String json = con.nextLine();
        System.out.println(json);
        return null;
    }
}
