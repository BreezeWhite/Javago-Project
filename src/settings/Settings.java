package settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Settings {

	public static String getClientIP() {
		return clientIP;
	}

	public static String getClientPort() {
		return clientPort;
	}
	
	public static String getBroadcast() {
		return broadcast;
	}

	public static Settings getInstance() {
		if (theSettings == null) {
			theSettings = new Settings();
		}
		return theSettings;
	}

	public static int getPlayerIndex() {
		return playerIndex;
	}

	public static String getServerIP() {
		return serverIP;
	}

	public static String getServerPort() {
		return serverPort;
	}

	public static boolean isServer() {
		return server;
	}

	private static String clientIP;
	private static String clientPort;
	private static String broadcast;
	private static int playerIndex;
	private static boolean server = false;
	private static String serverIP;
	private static String serverPort;
	private static Settings theSettings;

	private Settings() {
		read();
	}

	private static void read() {
		File file = new File("settings.ini");
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String text = null;

			while ((text = reader.readLine()) != null) {
				if (text.equals("server")) {
					text = reader.readLine();
					server = text.equals("1");
				} else if (text.equals("serverip")) {
					serverIP = reader.readLine();
				} else if (text.equals("serverport")) {
					serverPort = reader.readLine();
				} else if (text.equals("clientip")) {
					clientIP = reader.readLine();
				} else if (text.equals("clientport")) {
					clientPort = reader.readLine();
				} else if (text.equals("playerindex")) {
					playerIndex = Integer.parseInt(reader.readLine());
				} else if(text.equals("broadcast")) {
					broadcast = reader.readLine();
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
			}
		}
	}

}
