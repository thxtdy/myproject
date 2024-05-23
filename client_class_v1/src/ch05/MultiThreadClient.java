package ch05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

// 1 : 함수로 분리해서 리팩토링
public class MultiThreadClient {

	public static void main(String[] args) {
		
		try (Socket socket = new Socket("localhost", 5000)){
			//
//			BufferedReader reader = new BufferedReader()
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	} // end of main

} // end of class
