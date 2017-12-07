package client;

import java.util.Scanner;

public class UserInputsReader implements Runnable {

	private final Scanner console;
	private final Client client;

	public UserInputsReader(final Scanner console, final Client client) {
		super();
		this.console = console;
		this.client = client;
	}

	@Override
	public void run() {
		while (true) {
			try {
				final String input = console.nextLine();
				client.getUserInputsBuffer().add(input);
				client.processInput();
			} catch (final RuntimeException e) {
				// System.out.println("User input stream closed");
				// client has closed the stream (ready to terminate)
				break;
			}
		}
	}
}
