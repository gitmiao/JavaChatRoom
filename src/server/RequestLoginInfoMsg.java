package server;

public class RequestLoginInfoMsg implements Message{
	private final static String head = RequestLoginInfoMsg.class.getSimpleName();
	@Override
	public String toMessage() {
		return head;
	}
	public static Message parse(final String message) {
		if (!message.equals(head)) {
			return null;
		}
		return new RequestLoginInfoMsg();
	}

}
