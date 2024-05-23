package ch01;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {

		// 서버 측 소켓통신을 만들기 위해서의 준비물
		// 1. ServerSocket (client 측 소켓과 연결만 시켜줌.)
		// 2. client 와 연결되는 소켓을 들고 있어야 한다.

		// 로컬 컴퓨터에는 정해진 or 사용할 수 있는 포트 번호 갯수가 할당됨.
		// 1 ~ 1024 (잘 알려진 포트 번호)
		// 시스템이 선점하고 있는 번호들이다.

		try (ServerSocket serverSocket = new ServerSocket(5000)) {
			System.out.println("서버 포트 : 5000 으로 생성 "  ); //serverSocket);
			
			// 내부 메소드 안 while 문을 돌면서
			// client 측의 연결을 기다린다.. 스윗하네
			Socket socket = serverSocket.accept();
			
			// 여기 아래는 client 측과 양 끝단의 소켓이
			// 서로 연결되어야 실행흐름이 내려온다.
			System.out.println("Client connected...");
			
			// 대상 => 소켓 => 입력 스트림을 가져온다
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			while(true) {
				String message = reader.readLine(); // 한줄 단위로 데이터를 읽을 수 있기 때문.
				System.out.println("Received : " + message);
			}
			
			
			// 기본 소켓은 클라이언트가 연결되어야 생성된다.
//			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
