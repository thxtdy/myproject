package ch05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// 1 : 함수로 분리해서 리팩토링
public class MultiThreadClient {

	public static void main(String[] args) {
		System.out.println("### Client Started ###");
		
		try (Socket socket = new Socket("localhost", 5000)){
			System.out.println("Connected to the Server");
			
			// 서버와 통신을 위한 스트림 초기화 세팅
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
			BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
			
			startReadThread(bufferedReader);
			startWriteThread(printWriter, keyboardReader);
			// Main Thread 멈추는건 어딨노 => 가독성이 떨어짐
			// startWriteThread() <= 내부에 있음
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} // end of main
	// 1. Client 에서 데이터를 읽는 Thread 시작 메소드 생성
	private static void startReadThread(BufferedReader reader) {
		
		Thread readThread = new Thread(() -> {
			
			try {
				String msg;
				while((msg = reader.readLine()) != null) {
					System.out.println("Client에서 온 Message : " + msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
		readThread.start();
		
	}
	
	// 2 : 키보드에서 입력을 받아 클라이언트 측으로 데이터를 전송하는 스레드
	private static void startWriteThread(PrintWriter writer, BufferedReader keyboardReader) {
		Thread writeThread = new Thread(() -> {
			
			try {
				String clientMessage;
				while((clientMessage = keyboardReader.readLine()) != null) {
					// 전송
					writer.print(clientMessage);
					writer.flush();
				}
			} catch (Exception e) {
				
			}
		});
		writeThread.start();
		
		try {
			writeThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
} // end of class
