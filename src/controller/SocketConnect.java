package controller;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class SocketConnect {
	public static Socket cli(int host) {
        Socket socket;
        try {
        	return new Socket("localhost",host);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
