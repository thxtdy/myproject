package ch06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class AbstractClient {
	
	private String name;
	private Socket socket;
	private PrintWriter socketWriter;
	private BufferedReader socketReader;
	private BufferedReader keyboardReader;
	
	public AbstractClient(String name) {
		this.name = name;
	}
	// 외부에서 나의 멤버 변수에 참조변수를 주입받을 수 있도록 setter 메소드 설계
	protected void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public final void run() {
		try {
			connectToServer();
			setupStreams();
			startService();
		} catch (IOException e) {
			System.out.println("접속 종료~++");
		} finally {
			closeThread();
		}
	}
	
	protected abstract void connectToServer() throws IOException;
	
	private void setupStreams() throws IOException{
		socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		socketWriter = new PrintWriter(socket.getOutputStream(), true);
		keyboardReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	private void startService() throws IOException{
		// Thread Start
		Thread readThread = createReader();
		Thread writeThread = createWriter();
		// Main Thread wait
		readThread.start();
		writeThread.start();
		
		try {
			readThread.join();
			writeThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private Thread createWriter() {
		return new Thread(() -> {
			try {
				String message;
				while((message = keyboardReader.readLine()) != null) {
					socketWriter.println("[" + name + "]" + ": " + message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	private Thread createReader() {
		return new Thread(() -> {
			try {
				String sMessage;
				while((sMessage = socketReader.readLine()) != null) {
					System.out.println("Server Message" + sMessage);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	private void closeThread() {
		if(socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
}
