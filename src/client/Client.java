package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import server.Message;

public class Client {
	private final List<String> userInputs;
	private final List<Message> messagesFromServer;
	// fields for incomplete message (e.g. user log in message)
	private final List<String> incompleteMessage;
	private String userName;
	private PrintWriter out;
	private Socket s;
	private Thread userInputThread;
	private Scanner userInputStream;
	private Thread serverInputThread;
	private BufferedReader serverInputStream;

	
	private Client() {
		userInputs = new LinkedList<String>();
		messagesFromServer = new LinkedList<Message>();
		incompleteMessage = new ArrayList<String>();
		userName = null;
	}
	
	synchronized List<String> getUserInputsBuffer() {
		return userInputs;
	}

	synchronized List<Message> getMessageFromServerQueue() {
		return messagesFromServer;
	}
	
	private void start(final String[] args) {
		final String ipAddress = args[0];
		final int port = Integer.parseInt(args[1]);
		try {
			s = new Socket(ipAddress, port);
			final OutputStream outStream = s.getOutputStream();
			out = new PrintWriter(outStream, true);
			final InputStream inStream = s.getInputStream();
			serverInputStream = new BufferedReader(new InputStreamReader(
					inStream));
			//Runnable r2 = new ServerInputHandler(serverInputStream, this);

			userInputStream = new Scanner(System.in);
			//Runnable r = new UserInputsReader(userInputStream, this);

		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		final Client client = new Client();
		client.start(args);
	}

}
