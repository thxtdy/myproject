package ch05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {
	
	// Main Method
	public static void main(String[] args) {
		
		System.out.println("======== 서버 실행 ========");
		
		try(ServerSocket serverSocket = new ServerSocket(5000)) {
			
			Socket socket =  serverSocket.accept(); // Client 대기 => 연결 시 소켓 객체를 생성(Client 와 연결된 상태)
			System.out.println("======Client Connected!======");
			
			// 클라이언트와 통신을 위한 스트림을 설정 (대상 소켓을 얻었기 때문)
			
			// 클라이언트로부터 문자를 받아들임.
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			// 클라이언트에게 보낼 문자를 인식, Keyboard. 표준 입출력 System.in
			BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
			
			// 클라이언트에게 문자를 출력
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
			
			
			startReadThread(bufferedReader);
			startWriteThread(printWriter, keyboardReader);
			
			
			// Test Code
			System.out.println("Main Thread Worked");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} // end of main
	////////////////////////////////////////////////////////////////////////////////////
	
	// 1 : 클라이언트로부터 데이터를 읽는 스레드 분리
	// 데이터를 읽는 객체 = BufferedReader socketReader = new BufferedReader(new InputStream());
	
	private static void startReadThread(BufferedReader bufferedReader) {
		
		Thread readThread = new Thread(() -> {
			
			try {
				
				String clientMessage;
				while((clientMessage = bufferedReader.readLine()) != null) {
					// 서버 측 콘솔에 클라이언트 메시지(문자 데이터) 출력
					System.out.println("Client Message : " + clientMessage);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
		readThread.start(); // run() method
		// Main Thread join(); => 2번 반복

		
	}
	private static void startWriteThread(PrintWriter printWriter, BufferedReader keyboardReader) {
		
		Thread writeThread = new Thread(() -> {
			
			try {
				String serverMessage;
				while((serverMessage = keyboardReader.readLine()) != null) {
					printWriter.print(serverMessage);
					printWriter.flush();
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		writeThread.start();
	
	}
	
	// When Worker Thread ended
	private static void waitForThreadToEnd(Thread thread) {
		try {
			thread.join();
			
		} catch (Exception e) {

		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

} // end of class
