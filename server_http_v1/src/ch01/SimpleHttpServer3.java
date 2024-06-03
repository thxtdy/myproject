package ch01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServer3 {

	public static void main(String[] args) {

		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8090), 0);

			server.createContext("/temp", new MyTempHandler());

			server.start();
			System.out.println("서버 실행");

		} catch (IOException e) {
			e.printStackTrace();
		}

	} // end of main

	static class MyTempHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			String method = exchange.getRequestMethod();
			System.out.println("Connect : " + method);

			if ("GET".equalsIgnoreCase(method)) {
				handleGetRequest(exchange);
			}

		}

		private void handleGetRequest(HttpExchange exchange) throws IOException {
			String response = """
				    <!DOCTYPE html>
					<html lang="en">
					<head>
					    <meta charset="UTF-8">
					    <meta name="viewport" content="width=device-width, initial-scale=1.0">
					    <title>MyTempServer</title>
					</head>
					<body>
					    <h1 style="color: aqua;">Hello, temp</h1>
					   <a href src = "https://www.naver.com">Naver</a>
					</body>
					</html>
					""";
			exchange.getResponseHeaders().set("Content-Type", "temp/html; charset=UTF-8");
			exchange.sendResponseHeaders(200, response.length());
		    OutputStreamWriter os = new OutputStreamWriter(exchange.getResponseBody() , "UTF-8");
			os.write(response);
			os.flush();
			os.close();

		}
	}

}
