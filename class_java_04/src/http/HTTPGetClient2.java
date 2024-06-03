package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HTTPGetClient2 {
	
	public static void main(String[] args) {
		
		String urlString = "https://www.naver.com";
		
		try {
			URL url = new URL(urlString);
			
			HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
			
			connection.setRequestMethod("GET");
			int responsecode = connection.getResponseCode();
			System.out.println("HTTP CODE : " + responsecode);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String input;
			StringBuffer inputBuffer = new StringBuffer();
			
			while((input = br.readLine()) != null) {
				
				inputBuffer.append(input);
				
			}
			br.close();
			
			String[] strhtml = inputBuffer.toString().split("\\s");
			
			System.out.println("index Count " + strhtml.length);

			for (String word : strhtml) {
				System.out.println(word);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
