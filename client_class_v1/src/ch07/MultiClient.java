package ch07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MultiClient {
	
	public static void main(String[] args) {
		
		try {
			Socket socket = new Socket("192.168.0.48", 5000);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("서버에 접속 완료");
			
			// 실행의 흐름 - 약속 : 먼저 사용자 닉네임을 보내기
			System.out.println("Enter your name : ");
			String name = keyboard.readLine();
			out.println("NAME: " + name); // 서버로 사용자 이름 전송
			
			Thread readThread = new Thread(() -> {
				String serverMsg;
				try {
					while((serverMsg = in.readLine()) != null){
						System.out.println("server : " + serverMsg);
					
						
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			/// Client to Server
			Thread writeThread = new Thread(() -> {
				try {
					String userMessage;
					while((userMessage = keyboard.readLine()) != null) {
						if(userMessage.equalsIgnoreCase("bye")) {
							out.print("BYE:");
						} else {
							out.println("MSG: " + userMessage);
						}
//						 else if(userMessage.equalsIgnoreCase("MSG")) {
//							out.println("MSG:" + userMessage);
//						}
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			});
			
			// Thread Start
			readThread.start();
			writeThread.start();
			// Main Thread Wait
			try {
				readThread.join();
				writeThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			socket.close();
			System.out.println("서버로부터 연결을 종료하였습니다.");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	} // end of main
	
} // end of class
