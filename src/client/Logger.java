package client;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	private Logger() {

	}

	public static void log(final String message) {
		final String time = sdf.format(new Date());
		System.out.println(time + " " + message);
	}
}
