package server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {
	private final Socket incoming;
	private final ServerModel server;
	
	public ServerThread(final Socket s, final ServerModel server) {
		incoming = s;
		this.server = server;
	}

	@Override
	public void run() {
		try {
			// should always ask for user name and password
			final OutputStream outStream = incoming.getOutputStream();
			PrintWriter out = new PrintWriter(outStream, true);
			out.println(new RequestLoginInfoMsg().toMessage());
			final InputStream inStream = incoming.getInputStream();
			final BufferedReader in = new BufferedReader(new InputStreamReader(
					inStream));
			String msgFromClient;
			while (true) {
				msgFromClient = in.readLine();
				System.out.println("Received message " + msgFromClient);
				// process client message according to client state and the
				// message
				final Message messageFromClient = MessageFactory
						.parse(msgFromClient);
			}
		} catch(Exception e) {

		} finally {
		}

	}

}
