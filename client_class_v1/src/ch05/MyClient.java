package ch05;

import java.io.IOException;
import java.net.Socket;

// 2-1 : 상속을 호라용한 구현 클래스 설계하기
public class MyClient extends AbstractClient{
	
	@Override
	protected void setUpConnect() throws IOException {
//		super.setSocket(new Socket("localhost",5000));
		
		
	}
	public static void main(String[] args) {
		MyClient client = new MyClient();
		client.run();
	}

	
}
