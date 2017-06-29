package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import entities.Entity;
import entities.Projectile;
import entities.Update;
import main.JavaGo;
import mathematics.Vector2d;
import settings.Settings;

public class Client {
	public enum Error {
		NONE, INVALID_HOST, SOCKET_EXCEPTION
	}

	public Client() {
		serverIPString = Settings.getServerIP();
		port = Integer.parseInt(Settings.getClientPort());
		serverPort = Integer.parseInt(Settings.getServerPort());
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
		DatagramPacket packet = new DatagramPacket(data, data.length, serverIP, serverPort);
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
		if (update != null) {
			if (!update.player) {
				if (update.newProjectile) {
					Projectile projectile = new Projectile(new Vector2d(update.x, update.y), update.spriteSheetIndex,
							update.spriteIndex, update.angle, update.speed, update.range, update.damage);
					projectile.id = update.index;
					JavaGo.getInstance().getBattle().add(projectile);
				} else {
					List<Entity> entities = JavaGo.getInstance().getBattle().getEntities();
					for (int i = 0; i < entities.size(); ++i) {
						if (entities.get(i).getID() == update.index) {
							if (update.removed) {
								entities.remove(i);
							} else {
								entities.get(i).processUpdate(update);
							}
							break;
						}
					}
				}
			} else {
				JavaGo.getInstance().getBattle().getPlayers().get(update.index).processUpdate(update);
			}
		}
		// InetAddress address = packet.getAddress();
		// int port = packet.getPort();
	}

	@SuppressWarnings("unused")
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
	private int serverPort;
	private DatagramSocket socket;
	private boolean listening = false;

}
