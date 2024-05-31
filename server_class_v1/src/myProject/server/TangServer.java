package myProject.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

public class TangServer {

	// ServerSocket, Socket, IO 선언
	private Vector<CreateServer> createServer = new Vector<>();

	private Vector<MyRoom> madeRooms = new Vector<>();

	private ServerSocket serverSocket;
	private Socket socket;
	private String protocol;
	private String from;
	private String message;

	private String id;

	private TangServerFrame frame;

	public TangServer() {
		frame = new TangServerFrame(this);

//		startServer();
	}

	public void startServer() {
		try {
			System.out.println("실행");
			int portNum = Integer.parseInt(frame.getPortText().getText().trim());
			serverSocket = new ServerSocket(portNum);
			frame.serverList.append(portNum + " 포트로 연결되었습니다.\n");
			connect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void connect() {
		new Thread(() -> {

			while (true) {
				try {
					System.out.println("연결됨");
					socket = serverSocket.accept();
					CreateServer createServer = new CreateServer(socket);
					createServer.start();
					System.out.println("클라이언트 접속");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}).start();
	}

	private void broadCast(String bMsg) {
		for (int i = 0; i < createServer.size(); i++) {
			// 0
			CreateServer user = createServer.elementAt(i);
			// user[0]
			user.writer(bMsg);
		}

	}
	
	private void serverAppend(String str) {
		frame.serverList.append(str);
	}

	private class CreateServer extends Thread {
		private Socket socket;
		private BufferedReader in;
		private BufferedWriter out;
		
		private String id;
		private String myRoomName;

		public CreateServer(Socket socket) {
			this.socket = socket;
			connectIO();

		}

		public void connectIO() {
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//				out = new PrintWriter(socket.getOutputStream());
				out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			} catch (IOException e) {
				e.printStackTrace();
			}

			createServer.add(this);
		}

		private void sendUserInfomation() {

			try {
				id = in.readLine();

				newUser();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// 클라이언트한테 받은 정보를 읽는
		@Override
		public void run() {

			try {
				while (true) {
					String message = in.readLine();
					checkProtocol(message); // 프로토콜 만들기

				}
			} catch (Exception e) {
				
			}
		}

//
		private void checkProtocol(String str) {
			StringTokenizer tokenizer = new StringTokenizer(str, "/");

			System.out.println("여기는 protocol 과 from 을 구분하기 위한 라인입니다ㅣ");

			// protocol 값에 따라 무엇을 할지 결정 = 우리가 설정해놓은 값에 따라 각각의 메소드 실행
			protocol = tokenizer.nextToken();
			System.out.println(protocol);

			// client 로부터 문자를 받았을때, 문자의 내용
			from = tokenizer.nextToken();
			System.out.println(from);

			System.out.println("여기는 protocol 과 from 을 구분하기 위한 라인입니다ㅣ");

			// 안녕하세요 저는 박태현입니다
			// (protocol)
			// (from) 전체

			if (protocol.equals("Chatting")) {
				message = tokenizer.nextToken();
				System.out.println(message);
//				chatting();
//				broadCast("Chatting/" + from + "/" + message);

				for (int i = 0; i < createServer.size(); i++) {
					CreateServer user = createServer.elementAt(i);
					user.writer("Chatting/" + from + "/" + message);
				}

			}

		}
		// Server 에서 Client 로 보내는 규칙/양식

		public void writer(String str) {
			try {
				out.write(str + "\n");
				System.out.println("서버가 클라이언트에게");
				out.flush();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void chatting() {
			
			serverAppend("[" + from + "]" + message);
		
			for (int i = 0; i < madeRooms.size(); i++) {
				MyRoom room = madeRooms.elementAt(i);

				broadCast(from);

				if (room.roomName.equals(from)) {
					room.roomBroadCast("Chatting/" + id + "/");
				}
			}
		}

		public void SecretMessage() {
			if (protocol.equals("SecretMessage")) {
			}

		}
//		public void MakeRoom() {
//			for (int i = 0; i < madeRooms.size(); i++) {
//				MyRoom room = madeRooms.elementAt(i);
//				
//				if (room.roomName.equals(from)) {
//					
//				}
//			}
//		}

		public void newUser() {
			createServer.add(this);
			broadCast("NewUser/" + id);
		}

		public void connectedUser() {
			for (int i = 0; i < createServer.size(); i++) {
				CreateServer user = createServer.elementAt(i);
				writer("ConnectedUser/" + user.id);
				
			}
		}

	}

	private class MyRoom {

		private String roomName;
		private Vector<CreateServer> myRoom = new Vector<>();

		public MyRoom(String roomName, CreateServer createServer) {
			this.roomName = roomName;
			this.myRoom.add(createServer);
		}

		private void roomBroadCast(String msg) {
			for (int i = 0; i < myRoom.size(); i++) {
				CreateServer server = myRoom.elementAt(i);

				server.writer(msg);
			}
		}

	}

	// Client 에 뿌려주기

	public static void main(String[] args) {
		new TangServer();
	}
}
