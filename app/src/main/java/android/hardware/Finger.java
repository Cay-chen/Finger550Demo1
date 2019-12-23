package android.hardware;

import android.annotation.SuppressLint;


@SuppressLint("DefaultLocale")
public class Finger {

	static public native int open();

	static public native int close();

	static public native int IsInit();

	//¶ÁÈ¡Êý¾Ý
	static public native int readdata(byte[] buf, int nIndex, int nCount);

	//
	static public native int writedata(byte[] buf, int nIndex, int nCount);

	static public native int UpImage(byte[] buf, int nLen, int nTimeout);

	static public native int readbuf(byte[] buf, int nIndex, int nLen,
			int nTimeout);

	static public native void Clear();

	public static String BytesToString(byte[] b, int nIndex, int nCount) {
		String ret = "";
		for (int i = nIndex; i < nIndex + nCount; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}

	public static byte[] stringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	static {
		System.loadLibrary("hardware-print");
	}

}
