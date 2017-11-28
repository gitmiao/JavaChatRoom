package server;

public class MessageFactory {
	private MessageFactory() {
		
	}
	
	public static Message parse(final String message){
		Message msg;
		msg = RequestLoginInfoMsg.parse(message);
		if(msg != null){
			return msg;
		}
		throw new RuntimeException("Unknown message " + message);
	}

}


