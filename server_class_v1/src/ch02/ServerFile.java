package ch02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFile {
	
	public static void main(String[] args) {
		
		// 준비물
		// 1 : 서버 소켓이 필요하다.
		// 2 : 포트 번호가 필요하다. ( 0 ~ 65535 까지 존재 )
		// 2-1 : 잘 알려진 포트 번호 : 주로 시스템 레벨에서 먼저 선점함 = 0 ~ 1023
		// 2-2 : 등록 가능한 포트 : 1024 ~ 49151 까지
		// 2-3 : 동적/사설 포트 번호 - 그 외 임시 사용을 위해 할당된다.
		
		// 지역 변수
		ServerSocket serverSocket = null;
		
		try {
			
			serverSocket = new ServerSocket(5001);
			System.out.println("Start Server - Port_num : 5001");
			
			Socket socket = serverSocket.accept(); // while => return
			System.out.println(">>> Connected Client");
			
			// "InputStream"
			InputStream input =  socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			// 실제 데이터를 읽는 행위
			String message = reader.readLine();
			System.out.println("Client : recieved " + message);
			
			socket.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			if(serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
}
