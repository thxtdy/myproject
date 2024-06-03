package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPGetClient {

	public static void main(String[] args) {

		// 자바 기본 코드로 HTTP 요청을 만들어보자.

		// HTTP 통신을 하기 위한 준비물
		// 서버 주소(경로) 준비
		String urlString = "https://jsonplaceholder.typicode.com/posts/5/";

		// 1. URL 클래스를 만들어준다.
		// 2. Connection 객체를 만들어준다. (URL --> 멤버로 Connection 객체를 뽑을 수 있다.)

		try {
			URL url = new URL(urlString);
			// 이때 url.openConnection() 연결 요청 진행
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // url Connection 을 구현 다운 캐스팅

			// 추가 설정을 할 수 있음
			// Method 방식 설정(약속) -- GET 요청은 해당 서버의 자원 요청..입니다!@

			conn.setRequestMethod("GET");

			// HTTP 응답 메시지에서 데이터를 추출할 수 있다.
			int responseCode = conn.getResponseCode(); // 200 == 연결 성공 . 401 Unauthorized == 승인되지 않은.
			System.out.println("HTTP CODE : " + responseCode);

			BufferedReader brIn =  new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String inputLine;
			StringBuffer responseBuffer = new StringBuffer();
			
			while((inputLine = brIn.readLine()) != null) {
				responseBuffer.append(inputLine);
			}
			brIn.close();
//			System.out.println(responseBuffer.toString());
			
			String[] strHtmls = responseBuffer.toString().split("\\s");
			System.out.println("Index Count : " + strHtmls.length);
			for(String word : strHtmls) {
				System.out.println(word);
			}
			

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	} // end of main

} // end of class
