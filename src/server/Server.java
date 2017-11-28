package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		ServerModel server = new ServerModel();
		try{
			int port = Integer.parseInt(args[0]);
			ServerSocket s = new ServerSocket(port); // server never close
			while(true){
				Socket incoming = s.accept();
				Runnable r = new ServerThread(incoming, server);
				Thread t = new Thread(r);
				t.start();		
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
