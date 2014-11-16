package DataService;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class DataService {

	// クライアント
	Socket clientSocket = null;
	DataOutputStream outputStream_c = null;
	BufferedReader inputStream_c = null;
	String HostName = "192.168.0.9";
	int PORT_NUMBER_IN = 4649;
	
	// サーバー
	ServerSocket myServer = null;
	Socket senderSocket = null;
	int PORT_NUMBER_OUT = 4649;
	BufferedReader inputStream_s;
	PrintStream outputStream_s;
	String str;
	
	public void serverSocket () {
		
		// Server
		try {
			myServer = new ServerSocket(PORT_NUMBER_OUT);
		} catch(IOException e) {
			System.out.println(e);
		}
		
		try {
			senderSocket = myServer.accept();
			inputStream_s = new BufferedReader(new InputStreamReader
					(senderSocket.getInputStream()));
			outputStream_s = new PrintStream(senderSocket.getOutputStream());
			
			while (true) {
				str = inputStream_s.readLine();
				outputStream_s.println(str);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void clientSocket () {
		// Client
		try {
			clientSocket = new Socket(HostName, PORT_NUMBER_IN);
			outputStream_c = new DataOutputStream(clientSocket.getOutputStream());
			inputStream_c = new BufferedReader(new InputStreamReader
					(clientSocket.getInputStream()));
		} catch(UnknownHostException e) {
			System.err.println("Don't know about host: " + HostName);
		} catch(IOException e) {
			System.out.println(e);
		}
		
		if (clientSocket != null && outputStream_c != null && inputStream_c != null) {
			try {
				outputStream_c.writeBytes("YO!\n");
				
				String responseLine;
				if ((responseLine = inputStream_c.readLine()) != null) {
					System.out.println("Server : " + responseLine);
				}
				outputStream_c.close();
				inputStream_c.close();
				clientSocket.close();
			} catch (UnknownHostException e) {
				System.err.println("IOException: " + e);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
}
