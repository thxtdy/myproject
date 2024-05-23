package ch01;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) {
		// client 측 => 소켓 통신을 위한 준비물
		// 1 : 서버 측 컴퓨터의 주소 + 포트 번호
		// 2 : 서버 측과 연결될 기본 소켓이 필요하다.
		
		// 생성자 매개 변수에 서버 측 IP 주소, 포트 번호 
		try (Socket socket = new Socket("192.168.0.111", 5000)){
			// new Socket("localhost", 7000) => 객체 생성 시 서버 측과 연결되어 스트림을 활용할 수 있다.
			// 대상은 소켓이다.
			OutputStream output = socket.getOutputStream(); // 소켓에서 기반 스트림
			PrintWriter writer = new PrintWriter(output, true); // 기능의 확장 - 보조 스트림
			writer.println("오목 스코어 ( 3 : 1 ) ");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
