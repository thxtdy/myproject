package ch01;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServer2 {

	public static void main(String[] args) {
		// 8080 <= https, 80 <== http ( 포트번호 생략 가능하다)
		try {

			// 포트 번호 8080 으로 HTTP 서버 생성
			HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

			// 서버에 대한 설정

			// 프로토콜 정의(경로, 핸들러 처리)
			server.createContext("/test", new MyTestHandler()); // 핸들러 처리를 내부 정적 클래스로 사용
			server.createContext("/hello", new HelloHandler());
			server.createContext("/delete", new Deletehandler());
			
			// Server Start
			server.start();
			System.out.println(">> My Http Server Start");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // end of main

	// http://localhost:8080/test < 주소 설계
	static class MyTestHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange exchange) throws IOException {

			// 사용자의 요청 방식 (METHOD) , GET, POST 알아야 우리가 동작 시킬 수 있다.
			String method = exchange.getRequestMethod();
			System.out.println("Method : " + method);

			if ("GET".equalsIgnoreCase(method)) {
				// GET 이라면 여기서 동작
//				System.out.println("GET 방식으로 호출됨");

				// GET -> path: /test 라고 들어오면 어떤 응답 처리를 내려주면 된다.
				handleGetRequest(exchange);

			} else if ("POST".equalsIgnoreCase(method)) {
				// POST 요청 시 여기 동작
//				System.out.println("POST 방식으로 호출됨");
				handlePostRequest(exchange);

			}

		}

		// Get 요청 시의 동작 만들기
		private void handleGetRequest(HttpExchange exchange) throws IOException {
			String response = """
					<!doctype html>
					<html lang=ko>
					<head></head>
					<body>
						<h1 style="color:red">Hello path by /test</h1>
					</body>

					</html>
					""";
			exchange.sendResponseHeaders(200, response.length());
			OutputStreamWriter os = new OutputStreamWriter(exchange.getResponseBody()); 
			os.write(response); // 응답 본문 전송
			os.close();

		}

		private void handlePostRequest(HttpExchange exchange) throws IOException {
			// POST 요청은 HTTP 메시지에 Body 영역이 존재한다.
			String response = """
						<!doctype html>
						<html lang=ko>
						<head></head>
						<body>
							<h1 style="background-color:red">Hello path by /test</h1>
								     <a href src = "https://www.naver.com">네이버</a>
						</body>

						</html>
						""";

			// HTTP 응답 헤더 설정1
			exchange.setAttribute("Content-Type", "text/html; charset=UTF-8");
			exchange.sendResponseHeaders(200, response.length());

			OutputStream os = exchange.getResponseBody();
			os.write(response.getBytes());
			os.flush();
			os.close();
		}

	} // end of MyTestHandler

	static class HelloHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			String method = exchange.getRequestMethod();
			System.out.println("Hello Method " + method);

		}

	}
	
	static class Deletehandler implements HttpHandler{

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			String method = exchange.getRequestMethod();
			System.out.println("Method " + method);
			
			if("GET".equalsIgnoreCase(method)) {
				
			} else if ("POST".equalsIgnoreCase(method)) {
				
			}
		}

		
	}
	
	private void deleteGetRequest(HttpExchange exchange) throws IOException{
		String response = "Delete";
	    
		
	}

}
