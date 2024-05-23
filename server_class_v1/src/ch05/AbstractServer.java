package ch05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// 상속 활용
public abstract class AbstractServer {
	
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader readerStream;
	private PrintWriter writerStream;
	private BufferedReader keyboardReader;
	
	// set Method
	// 메소드 의존 주입(멤버 변수에 참조 변수 할당)
	
	protected void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	
	protected void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	// get Method
	protected ServerSocket getServerSocket() {
		return this.serverSocket;
	}
	
	// 실행의 흐름이 필요하다(순서가 중요)
	public final void run() {
		
		// 1 : 서버 세팅 => 포트 번호 할당
		try {
			setupServer();
			connection();
			setupStream();
			startService();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("1111111111111");
			cleanUp();
		}
	}
	// 1 : 포트 번호 할당 처리 (구현 클래스에서 직접 설계)
	protected abstract void setupServer() throws IOException;
	// 2 : Client 연결 대기 실행 (구현 클래스)
	protected abstract void connection() throws IOException;
	// 3 : 스트림 초기화 (연결된 소켓에서 스트림을 뽑아야 함) - 여기서 함(private)
	private void setupStream() throws IOException{
		readerStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writerStream = new PrintWriter(socket.getOutputStream(), true);
		keyboardReader = new BufferedReader(new InputStreamReader(System.in));
	
	}
	private void startService() {
		Thread readThread = createReadThread();
		readThread.start();
		Thread wriThread = createWriteThread();
		wriThread.start();
		
		try {
			readThread.join();
			wriThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("333333333333333");
	}
	
	// 캡슐화 개념
	private Thread createReadThread() {
		return new Thread(() -> {
			try {
				String msg;
				while((msg = readerStream.readLine()) != null) {
					System.out.println("Client Message : " + msg);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	private Thread createWriteThread() {
		return new Thread(() -> {
			try {
				String serverMsg;
				// 서버 측 키보드에서 데이터를 한줄 라인으로 읽음
				while((serverMsg = keyboardReader.readLine()) != null) {
					// 클라이언트와 연결된 소켓에다가 데이터를 보냄
					writerStream.println(serverMsg);
					writerStream.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	// 캡슐화 - 소켓 자원 종료
	private void cleanUp() {
		try {
			if(socket != null) {
				socket.close();
			}
			if(serverSocket != null) {
				serverSocket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
