package utilities;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class VisionClient2 {

	public Socket s;
	public DataOutputStream stream;
	public DataInputStream dis;
	public BufferedReader inFromClient;

	public void initalizeSocket() throws UnknownHostException, IOException {
		s = new Socket("10.45.0.80", 31415);
		stream = new DataOutputStream(s.getOutputStream());

		dis = new DataInputStream(s.getInputStream());
		inFromClient = new BufferedReader(new InputStreamReader (s.getInputStream()));
	}

	public int getData() throws IOException {
		stream.writeUTF("go");
		stream.flush();

		String str = (String)inFromClient.readLine();
		System.out.println("Recived: " + str);

		int colonIndex = str.indexOf(":");
		//int stringLength = str.length();
		String xS = "";
		//String yS = "";

		for(int xl = 0; xl < colonIndex; xl++) {
			xS = xS + str.charAt(xl);
		}
		/*for(int yl = colonIndex + 1; yl < stringLength; yl++) {
			yS = yS + str.charAt(yl);
		}*/
		//System.out.println(yS);
		int x = Integer.parseInt(xS);
		//int y = Integer.parseInt(yS);
		System.out.println("X cord is " + x);
		return x;
	}

	public boolean socketInitalized() {
		return ! (s == null);
	}

}