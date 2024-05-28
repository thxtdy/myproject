package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import lombok.Data;
@Data

public class TangClient {
	
	private Socket socket;
	private BufferedReader reader;
	private BufferedReader keyboardReader;
	private PrintWriter writer;
	private TangClientFrame frame;
	private TangLoginClient login;
	
	private String clientName;
	private String protocol;
	private String from;
	private String message;
	private String myId;
	
	public TangClient() {
		login = new TangLoginClient(this);
		
	}
	
	public void startClient(String clientName) {
		this.clientName = clientName;
		
		setUpStream();
		allThread();
		writer.println(clientName + "\n");
		frame = new TangClientFrame(this);
		
	}

	
	private void setUpStream(){
		try {
			socket = new Socket("localhost", 8000);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);
			keyboardReader = new BufferedReader(new InputStreamReader(System.in));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void allThread() {
		Thread read = readThread();
		try {
			read.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Thread readThread() {
		return new Thread(() -> {
			
			while(true) {
				try {
					String getmessage = reader.readLine();
					
					checkProtocol(getmessage);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
	}
	private void writeMessage(String str) {
	
		try {
			writer.write(str + "\n");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
	}

	
	private void endThread() {
		if(socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void checkProtocol(String str) {
		StringTokenizer tokenizer = new StringTokenizer(str);

		protocol = tokenizer.nextToken();
		from = tokenizer.nextToken();

		if (protocol.equals("Message")) {
			message = tokenizer.nextToken();
			chatting();
			
		} else if (protocol.equals("SecretMessage")) {
			
			
		} else if (protocol.equals("MakeRoom")) {

		} else if (protocol.equals("EnterRoom")) {

		} else if (protocol.equals("ExitRoom")) {

		}
	}
	public void chatting() {
		if(protocol.equals(clientName)) {
			frame.getChatList().append("[나] \n " + message + "\n" );
		} else if (protocol.equals(from)) {
			frame.getChatList().append(from + message + "\n");
		}
	}

	
	
	public static void main(String[] args) {
		new TangClient();
	}
	
}
