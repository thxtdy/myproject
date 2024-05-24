package ch06;

import java.io.IOException;
import java.net.Socket;

public class ChatClient extends AbstractClient{
	
	public ChatClient(String name) {
		super(name);
	}


	@Override
	protected void connectToServer() throws IOException {
		// AbstractClient => 부모 클래스 => Server 측과 연결된 Socket 주입해주어야 한다.
		
		super.setSocket(new Socket("192.168.0.48", 5000));
		
		
	}
	
	public static void main(String[] args) {
		ChatClient chat = new ChatClient("BTS,봉준호,손흥민,김남철 ");
		chat.run();
	}
}
