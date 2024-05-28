package myProject.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

//import lombok.Data;
//
//@Data

public class TangServer {

	// ServerSocket, Socket, IO 선언
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader reader;
	private BufferedReader keyboardReader;
	private PrintWriter writer;
	private static Vector<PrintWriter> clientWriter = new Vector<>();
	private String protocol;
	private String from;
	private String message;

	private TangServerFrame frame;

	public TangServer() {
		frame = new TangServerFrame(this);

//		startServer();
	}

	public void startServer() {
		new Thread(() -> {

			try {
				while (true) {
					serverSocket = new ServerSocket(8000);
					System.out.println("대기 중");
					socket = serverSocket.accept();

					CreateServer createServer = new CreateServer(socket);
					System.out.println("ㅎㅇ");
					frame.getServerList().append("연결됨");
					System.out.println("ㅎㅇ1");
					createServer.start();

				}
			} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
			}

		}).start();

	}

	public void setUpStream() {
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);
			keyboardReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	private class CreateServer extends Thread {
		private Socket socket;

		public CreateServer(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			
			while(true) {
				try {
					String message = reader.readLine();
					checkProtocol(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}

		private void checkProtocol(String str) {
			StringTokenizer tokenizer = new StringTokenizer(str);

			protocol = tokenizer.nextToken();
			from = tokenizer.nextToken();

			if (protocol.equals("Message")) {
				message = tokenizer.nextToken();
				Message();
			} else if (protocol.equals("SecretMessage")) {
				message = tokenizer.nextToken();
				SecretMessage();
			} else if (protocol.equals("MakeRoom")) {

			} else if (protocol.equals("EnterRoom")) {

			} else if (protocol.equals("ExitRoom")) {

			}
		}

		public void Message() {
			if(protocol.equals("Message")) {
				broadcastMessage("Message/" + message);
			}

		}

		public void SecretMessage() {
			if(protocol.equals("SecretMessage")) {
				broadcastMessage("SecretMessage/" + message);
			}

		}
		public void MakeRoom() {
			
		}

	}

	private static void broadcastMessage(String message) {
		for (PrintWriter writer : clientWriter) {
			writer.println(message);
		}

	}

	public static void main(String[] args) {
		new TangServer();
	}
}
