package A_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class AbstractServer {

	// Stream 선언
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader reader;
	private BufferedReader keyboardReader;
	private PrintWriter writer;

	// 서버 소켓 설정
	protected ServerSocket getServerSocket() {
		return this.serverSocket;
	}

	protected void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	// 소켓(연결) 설정
	protected Socket getSocket() {
		return this.socket;
	}

	protected void setSocket(Socket socket) {
		this.socket = socket;
	}

	public AbstractServer() {
		// TODO Auto-generated constructor stub
	}

	// 포트 번호 설정
	protected abstract void setUpServer() throws IOException;

	// client 연결 대기
	protected abstract void connection() throws IOException;

	// Stream 세팅 초기화
	private void setStream() throws IOException {
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		keyboardReader = new BufferedReader(new InputStreamReader(System.in));
		writer = new PrintWriter(socket.getOutputStream(),true);
	}
	// run() Method
	public final void run() {
		try {
			setUpServer();
			connection();
			
			
		} catch (Exception e) {

		}
	}
	private Thread startReadThread() {
		return new Thread(() -> {
			try {
				String ReadMessage;
				while((ReadMessage = reader.readLine()) != null) {
					System.out.println(ReadMessage);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
	
	}
}
