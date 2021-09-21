import java.io.*;
import java.util.*;

public class CountingRooms {
	static char[][] buildings;
	static int n;
	static int m;
	static StringBuilder outBuffer = new StringBuilder();

	public static void main(String[] args) {
		n = ni();
		m = ni();

		buildings = new char[n][m];
		for (int i = 0; i < n; i++) {
			buildings[i] = ns().toCharArray();
		}

		boolean flag = true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (buildings[i][j] == 'A') {
					flag = dfs(i, j);
					break;
				}
			}
		}
		if (flag) {
			System.out.println("YES");
			System.out.println(outBuffer);
		} else {
			System.out.println("NO");
		}
	}

	public static boolean dfs(int i, int j) {
		if (buildings[i][j] == 'B') {
			buildings[i][j] = '#';
			return true;
		}
		buildings[i][j] = '#';

		if (i > 0 && buildings[i - 1][j] != '#') {
			outBuffer.append("U");
			if (dfs(i - 1, j)) {
				return true;
			}
		}

		if (i < n - 1 && buildings[i + 1][j] != '#') {
			outBuffer.append("D");
			if (dfs(i + 1, j)) {
				return true;
			}
		}

		if (j > 0 && buildings[i][j - 1] != '#') {
			outBuffer.append("L");
			if (dfs(i, j - 1)) {
				return true;
			}
		}

		if (j < m - 1 && buildings[i][j + 1] != '#') {
			outBuffer.append("R");
			if (dfs(i, j + 1)) {
				return true;
			}
		}
		return false;
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
