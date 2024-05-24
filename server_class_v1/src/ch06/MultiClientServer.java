package ch06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MultiClientServer {
	
	private static final int PORT = 5000;
	// 하나의 변수에 자원을 통으로 관리하기 기법 => 자료구조
	// 자료구조 => 코드 단일, 멀티 => 멀티 스레드 => 자료구조는 뭐?
	// 객체 배열 ArrayList <= 멀티 스레딩 환경에선 안정적이지 않음
	// 객체 배열 Vector<> : 멀티 스레드에 안정적이다. 
	private static Vector<PrintWriter> clientWriters = new Vector<>();
	
	
	public static void main(String[] args) {
		System.out.println("Server Started");
		
		try (ServerSocket serverSocket = new ServerSocket(PORT)){
			while(true) {
				// 1 : serverSocket.accept() 호출 시, 블록킹 상태가 됨 = 멈춰있음
				// 2 : Client 가 연결 요청하면 새로운 소켓 객체 생성
				// 3 : 새로운 스레드를 만들어서 처리. Client 가 데이터를 주고 받기 위한 스레드(메인 스레드에 부담 X)
				// 4 : 새로운 Client 가 접속하기까지 다시 대기 유지 (무한 반복)
				
				Socket socket = serverSocket.accept();
				
				// 새로운 Client가 연결되면 새로운 스레드가 생성된다.
				new ClientHandler(socket).start(); 
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} // end of main
	
	// 정적 내부 클래스 설계
	private static class ClientHandler extends Thread {
		
		private Socket socket;
		private PrintWriter out;
		private BufferedReader in;
		
		public ClientHandler(Socket socket) {
			this.socket = socket;
		}
		
		// Thread Start() 호출 시 동작되는 run()
		@Override
		public void run() {
			
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				
				// *** 중요합니다 *** - 서버가 관리하는 자료구조에 자원 저장~! (Client 와 연결된 소켓 => OutputStream)
				clientWriters.add(out);
				
				String message;
				while((message = in.readLine()) != null) {
					System.out.println("Received : " + message);
					broadcastMessage(message);
				}
				// 받은 데이터를 서버측과 연결된 Client 에게 전달
				for (PrintWriter writer : clientWriters) {
					writer.println(message); // 모든 Client 에게 메시지 전송
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try {
					socket.close();
					System.out.println("Client 연결 해제");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	} // end of ClientHandler
	
	// 모든 Client 에게 메시지 보내기 - 브로드캐스트
	private static void broadcastMessage(String message) {
		for(PrintWriter writer : clientWriters) {
			writer.println(message);
		}
		
		
	}
	
	
	
	
}
