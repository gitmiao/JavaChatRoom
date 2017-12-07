package client;

import java.io.BufferedReader;
import java.io.IOException;

import server.Message;
import server.MessageFactory;
import server.RequestLoginInfoMsg;

public class ServerInputHandler implements Runnable {
	private final BufferedReader in;
	private final Client client;

	public ServerInputHandler(final BufferedReader in, final Client client) {
		super();
		this.in = in;
		this.client = client;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				String msgFromServer = in.readLine();
				final Message messageFromServer = MessageFactory
						.parse(msgFromServer);
				// if message request user input, then queue it, otherwise
				// handle it immediately
				if (messageFromServer instanceof RequestLoginInfoMsg) {
					Logger.log("Please input the user name and password (in two lines)");
					addMessageToQueue(messageFromServer);
				}
				
			} catch (IOException e) {
				// Logger.log("Server input stream closed");
				// client has closed the stream (ready to terminate)
				break;
			}
		}

	}
	private void addMessageToQueue(final Message message) {
		client.getMessageFromServerQueue().add(message);
		client.processInput();
	}

}
