package root.operation;

import java.util.Arrays;
import java.util.Optional;

public enum Command {
    REGISTER("register"), LOGIN("login"),
    CREATE_WS("create-workspace"), CONNECT_WP("connect-workspace"),
    DISCONNECT("disconnect");

    Command(String str) {
        this.str = str;
    }

    private final String str;

    @Override
    public String toString() {
        return str;
    }

    static public Command type(String str){
        Optional<Command> command = Arrays.stream(Command.values()).filter(cmd -> cmd.str.equals(str)).findFirst();
        return command.orElseThrow();
    }
}
