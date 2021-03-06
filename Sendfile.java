package package2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Sendfile {
	public static void sendfile(String ipaddress, int port, String outputfilepath) throws IOException, IOException{
		Socket fileLoaderSocket = new Socket(ipaddress, port);
		OutputStream out = fileLoaderSocket.getOutputStream();
		InputStream fileReader = new FileInputStream(outputfilepath);
		byte [] buff = new byte [1024];
		int len = 0;
		while ((len = fileReader.read(buff)) != -1 ){
			out.write(buff, 0, len);
		}
		fileLoaderSocket.shutdownOutput();
		BufferedReader bf = new BufferedReader(new InputStreamReader(fileLoaderSocket.getInputStream()));
		String severBack = bf.readLine();
		System.out.println(severBack);
		fileLoaderSocket.close();
		fileReader.close();
	} 
	public static void main(String [] args) throws IOException{
		String ipaddress = "";
		int port = 0;
		String outputfilepath = "C:\\Users\\Administrator\\Desktop\\Testing.xls";
		sendfile(ipaddress, port, outputfilepath);
	}
}
