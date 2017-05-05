package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {

	public Server(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public void send(InetAddress address, int port, byte[] data) {
		assert (socket.isConnected());
		DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
			return; // 如果發生例外，不要開始跑伺服器。
		}
		listening = true;
		listener = new Thread(() -> listen());
		listener.start();
	}

	private final int MAX_PACKET_SIZE = 1024;
	private final int DATA_BUFFER_SIZE = 10;
	private byte[] incomingData = new byte[MAX_PACKET_SIZE * DATA_BUFFER_SIZE];
	private boolean listening = false;
	private int port;
	private Thread listener;
	private DatagramSocket socket;
	
	private void dumpPacket(DatagramPacket packet) {
		byte[] data = packet.getData();
		InetAddress address = packet.getAddress();
		int port = packet.getPort();
		System.out.println("封包資訊：");
		System.out.println("\t" + address.getHostAddress() + ":" + port);
		System.out.println();
		System.out.println("\t內容：");
		System.out.println("\t\t:" + new String(data));
	}

	private void listen() {
		while (listening) {
			DatagramPacket packet = new DatagramPacket(incomingData, MAX_PACKET_SIZE);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			process(packet);
		}
	}

	private void process(DatagramPacket packet) {
		byte[] data = packet.getData();
		// 確認封包和遊戲有關並把它並行化。
		InetAddress address = packet.getAddress();
		int port = packet.getPort();
		dumpPacket(packet);
	}

}
