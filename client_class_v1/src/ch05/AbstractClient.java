package ch05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// 2 : 상속 활용
public abstract class AbstractClient {
	
	private Socket socket;
	// 클라이언트 측에서 출력하는 문자
	private PrintWriter writerStream;
	// 클라이언트 측에서 받아들이는 문자
	private BufferedReader readerStream;
	// 클라이언트 측에서 키보드 인식 후 문자로 출력
	private BufferedReader keyboardReader;
	
	// 소켓 설정
	protected void setSocket(Socket socket) {
		this.socket = socket;
	}
	// 소켓 정보
	protected Socket getSocket() {
		return this.socket;
	}
	// 서버에 연결
	protected abstract void setUpConnect() throws IOException;
	
	public final void run() {
		
		try {
			setUpStream();
			startService();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("4444444444");
			cleanUp();
		}
		
	}
	// Read, Write Thread 불러오기
	private void startService() {
		Thread writeThread = createWriterThread();
		writeThread.start();
		Thread readThread = createReadThread();
		readThread.start();
		
		try {
			writeThread.join();
			readThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	// Stream 의 구성
	protected void setUpStream() throws IOException{
		socket = new Socket("localhost", 5000);
		writerStream = new PrintWriter(socket.getOutputStream(), true);
		readerStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		keyboardReader = new BufferedReader(new InputStreamReader(System.in));
	}
	// 서버에게 메시지 전송 Thread
	protected Thread createWriterThread(){
		return new Thread(() -> {
			try {
				String clientMessage;
				while((clientMessage = keyboardReader.readLine()) != null){
					System.out.println(clientMessage);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
	}
	// 서버에게 메시지 받기 Thread
	protected Thread createReadThread() {
		return new Thread(() -> {
			try {
				String serverMessage;
				while((serverMessage = readerStream.readLine()) != null) {
					System.out.println("Server Message : " + serverMessage);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
	}
	
	private void cleanUp() {
		try {
			if(socket != null) {
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
