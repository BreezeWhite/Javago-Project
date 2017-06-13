package network;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import entities.Update;
import main.JavaGo;

public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2329440133307961773L;
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

	public void send(byte[] data) {
		assert (socket.isConnected());
		DatagramPacket packet = new DatagramPacket(data, data.length, serverIP, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void process(DatagramPacket packet) {
		byte[] data = packet.getData();
		// 確認封包和遊戲有關並把它並行化。
		Update update = Update.deserialise(data);
		if(update != null) {
			if(update.player == false) {
				JavaGo.getBattle().getEntities().get(update.index).processUpdate(update);
			}
			else {
				JavaGo.getBattle().getPlayers().get(update.index).processUpdate(update);
			}
		}
		InetAddress address = packet.getAddress();
		int port = packet.getPort();
	}

	private void sendConnectionPacket() {
		byte[] data = "ConnectionPacket".getBytes();
		send(data);
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
	private final int DATA_BUFFER_SIZE = 1024;
	private byte[] incomingData = new byte[MAX_PACKET_SIZE * DATA_BUFFER_SIZE];
	private Error errorCode = Error.NONE;
	private Thread listener;
	private int port;
	private InetAddress serverIP;
	private String serverIPString;
	private DatagramSocket socket;
	private boolean listening = false;

}
