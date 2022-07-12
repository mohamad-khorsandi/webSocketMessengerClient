package root.operation;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import root.Client;
import root.utils.Utils;

import java.io.IOException;

public class SendMessage extends WsOperation{
    @Override
    Object operate() throws Exception {
        //1 -------------------------------
        String receiver = Client.sc.next();
        Message msg = new Message(Client.sc.next(), Client.sc.nextLine());
        //2 -------------------------------
        send.format(receiver + " " + msg.toJSON());

        Utils.throwIfResIsNotOK(receive);
        int seq = receive.nextInt();
        return null;
    }

    @Getter
    @Setter
    public static class Message {
        public Message(String type, String body) {
            this.type = type;
            this.body = body;
        }

        String type;
        String body;

        public String toJSON() throws IOException {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        }
    }
}

