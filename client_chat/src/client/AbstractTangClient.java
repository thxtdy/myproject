package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import lombok.Data;

@Data

public abstract class AbstractTangClient {

	private String name;
	private Socket socket;
	private BufferedReader reader;
	private BufferedReader keyboardReader;
	private PrintWriter writer;
	
	

	public AbstractTangClient(String name) {
		this.name = name;
	}
	
	protected abstract void connectToServer() throws IOException;
	
	public void run()throws IOException {
		setUpStream();
		startService();
		
	}
	
	private void setUpStream() {
		try {
			socket = new Socket("localhost",5000);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);
			keyboardReader = new BufferedReader(new InputStreamReader(System.in));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void startService() throws IOException {
		Thread read = readThread();
		Thread write = writeThread();
		
		read.start();
		write.start();
		
		try {
			read.join();
			write.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			endThread();
		}
	}
	
	private Thread readThread() {
		return new Thread(() -> {
			try {
				String serverMessage;
				while((serverMessage = reader.readLine())!= null) {
					System.out.println("ServerMessage :" + serverMessage);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		}
	private Thread writeThread() {
		return new Thread(() -> {
			try {
				String clientMessage;
				while((clientMessage = keyboardReader.readLine()) != null) {
					writer.print(clientMessage);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	private void endThread() {
		try {
			System.out.println("Client end");
			if(socket != null) {
				socket.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
