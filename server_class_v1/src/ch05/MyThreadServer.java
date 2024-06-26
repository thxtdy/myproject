package ch05;

import java.io.IOException;
import java.net.ServerSocket;

public class MyThreadServer extends AbstractServer{
	
	MyThreadServer server;

	@Override
	protected void setupServer() throws IOException {
		// 추상 클래스 => 부모 -- 자식 (부모 기능 확장 또는 사용)
		super.setServerSocket(new ServerSocket(5000));
		System.out.println(" >> Server Started on Port - 5000 <<");
	}

	@Override
	protected void connection() throws IOException {
		// 서버 소켓.accept() 호출
		super.setSocket(super.getServerSocket().accept());
		System.out.println("Client Connected");
		
	}
	
	public static void main(String[] args) {
		MyThreadServer myThreadServer = new MyThreadServer();
		System.out.println("서버가 시작되었습니다.");
		
		myThreadServer.run();
	}
	
}
