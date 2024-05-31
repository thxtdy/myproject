package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class TangClient {

	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
	private TangLoginClient login;

	private TangClientFrame frame;

	private String portNum;

	private String clientName;
	private String protocol;
	private String from;
	private String message;
	private String myId;

	// 내 이름을 담을 변수
	String id;

	public TangClient() {
		login = new TangLoginClient(this);

	}

	public void startClient() {
		frame = new TangClientFrame(this);
		connectNetwork();
		connectIO();

	}

	public void connectNetwork() {
		try {
			int portNum = Integer.parseInt(login.getPortNum().getText().trim());
			socket = new Socket("localhost", portNum);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void connectIO() {
		try {
			System.out.println("연결띠~");
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			readMessage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readMessage() {
		new Thread(() -> {
			System.out.println("부러쓰부러쓰 읽는 곳에 들어와부러쓰");

			while (true) {

				try {
					System.out.println("리드읽기 전");
					String str = reader.readLine();
					System.out.println("리드읽기 후");
					System.out.println("클라이언트가 메시지를 읽었습니다.");
					checkProtocol(str);
					System.out.println("클라이언트가 메시지를 프로토콜로 보냈습니다.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}).start();

	}

	public void writeMessage(String str) {

		try {
			System.out.println("쓴다?");
			// 1. 입력 메시지를 서버로 전송
			writer.write(str + "\n"); // 클라이언트가 서버에게 쏘아주는
			writer.flush();

			System.out.println(str);
			System.out.println("썼다");

//				writer.close();

		} catch (Exception e) {

		}

	}

	private void checkProtocol(String msg) {
		StringTokenizer tokenizer = new StringTokenizer(msg, "/");

		System.out.println("client 여기는 protocol과 from 을 구분하기 위한 라인입니다");
		protocol = tokenizer.nextToken();
		System.out.println(protocol);
		from = tokenizer.nextToken();
		System.out.println(from);
		System.out.println("client 여기는 protocol과 from 을 구분하기 위한 라인입니다");

		if (protocol.equals("Chatting")) {
			message = tokenizer.nextToken();
			System.out.println("Chatting 인식 완료");
			chatting();
		}
		// protocol == 앞에 "chatting/" 과 같은 형식. from 그 다음 메세지 == id
	}

	public void chatting() {
		if (id.equals(from)) {
			System.out.println("여기 들어옴");
			frame.chatList.append("[Me]" + message + "\n");
//			writeMessage(message);
		} else {
			frame.chatList.append("[" + from +"] " + message + "\n");
		}
	}

	// Client 에서 Server 로 보내는 규칙/양식 ==== writeMessage 를 재정의
	public void chatwriter(String chatMessage) {
		writeMessage("Chatting/" + id + "/" + chatMessage);
	}

	public static void main(String[] args) {
		new TangClient();
	}
}
