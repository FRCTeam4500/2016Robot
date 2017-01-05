package utilities;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.Semaphore;

public class VisionClientFinal implements Runnable {
	
	private DatagramSocket socket;
	private Semaphore mutex;
	private double data;
	
	public VisionClientFinal(short port) throws IOException {
		socket = new DatagramSocket(port);
		mutex = new Semaphore(1);
		data = 0;
	}
	
	@Override
	public void run() {
		while(true) {
			byte[] recvData = new byte[1024];
			DatagramPacket packet = new DatagramPacket(recvData, recvData.length);
			try {
				socket.receive(packet);
				String dataS = new String(packet.getData());
				String[] parts = dataS.split(" ");
				
				mutex.acquire();
				data = Double.parseDouble(parts[0]);
				mutex.release();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public double getData() {
		double data = 0.0;
		try {
			mutex.acquire();
			data = this.data;
			mutex.release();
		} catch(InterruptedException e) {
			System.out.print("Error");
		}
		return data;
	}

}
