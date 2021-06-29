import java.io.*;
import java.util.*;

public class PanlindromeReorder {
	static int odd = 0;
	static String ch = "";

	public static void main(String[] args) {
		String string = ns();
		HashMap<String, Integer> occurrences = new HashMap<>();
		int length = string.length();
		for (int i = 0; i < length; i++) {
			String s = String.valueOf(string.charAt(i));
			if (occurrences.get(s) == null) {
				occurrences.put(s, 1);
			} else {
				int value = occurrences.get(s) + 1;
				occurrences.put(s, value);
			}
		}
		occurrences.forEach((k, v) -> {
			if (v % 2 != 0) {
				odd++;
				ch = k;
			}
		});

		if (odd > 1 || (odd == 1 && length % 2 == 0)) {
			System.out.println("NO SOLUTION");
		} else {
			String start = "";
			String end = "";
			for (Map.Entry<String, Integer> hm : occurrences.entrySet()) {
				String key = hm.getKey();
				int value = hm.getValue() / 2;
				
				key = key.repeat(value);
				
				start = start + key;
				end = key + end;
			}
			if (odd == 1) {
				System.out.println(start + ch + end);
			} else {
				System.out.println(start + end);
			}
		}
	}

	static InputStream is = System.in;
	static byte[] inbuf = new byte[1 << 24];
	static int lenbuf = 0, ptrbuf = 0;

	static int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}

	static boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	static int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	static double nd() {
		return Double.parseDouble(ns());
	}

	static char nc() {
		return (char) skip();
	}

	static String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	static char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	static int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}
		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	static long nl() {
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}
		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
}
