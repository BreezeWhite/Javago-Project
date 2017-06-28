package inputs;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class KeyPress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2167760213498813709L;
	public int keyIndex;
	public boolean keyPressed;
	public int playerIndex;

	public byte[] serialise() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
			try {
				out = new ObjectOutputStream(bos);
				out.writeObject(this);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] data = bos.toByteArray();
			return data;
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static KeyPress deserialise(byte[] data) {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		ObjectInput in = null;
		try {
			try {
				in = new ObjectInputStream(bis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Object obj = in.readObject();
				if (obj instanceof KeyPress) {
					return (KeyPress) obj;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
			}
		}
		return null;
	}

}
