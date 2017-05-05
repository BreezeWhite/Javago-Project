package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
	
	public enum Error {
		NONE, INVALID_HOST, SOCKET_EXCEPTION
	}
	
	public Client(String serverIPString, int port) {
		this.serverIPString = serverIPString;
		this.port = port;
	}
	
	public boolean connect() {
		try {
			serverIP = InetAddress.getByName(serverIPString);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			errorCode = Error.INVALID_HOST;
			return false;
		}
		try {
			// 不需要指定一個特定的埠。
			// 和伺服器溝通時會有返回位址。
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
			errorCode = Error.SOCKET_EXCEPTION;
			return false;
		}
		sendConnectionPacket();
		// 等伺服器的回應。
		return true;
	}
	
	public Error getErrorCode() {
		return errorCode;
	}
	
	public void send(byte[] data) {
		assert (socket.isConnected());
		DatagramPacket packet = new DatagramPacket(data, data.length, serverIP, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendConnectionPacket() {
		byte[] data = "ConnectionPacket".getBytes();
		send(data);
	}
	
	private Error errorCode = Error.NONE;
	private String serverIPString;
	private int port;
	private InetAddress serverIP;
	private DatagramSocket socket;
	
}
