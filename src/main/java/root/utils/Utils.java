package root.utils;

import java.util.Scanner;

public class Utils {
    public static void throwIfResIsNotOK(Scanner receive) throws Exception{
        String response = receive.next();
        if (!response.equals("OK"))
            throw new Exception(response + " " + receive.nextLine());
    }

    public static void throwIfResIsNotOK(QueueScanner receive) throws Exception{
        String response = receive.next();
        if (!response.equals("OK"))
            throw new Exception();
    }
}
