import java.io.*;
import java.util.*;

public class TwoSets {

	public static void main(String[] args) {
		StringBuilder outBuffer = new StringBuilder();
		int n = ni();
		int[] numbers = new int[n + 1];
		long sum = 0;
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = i;
			sum += i;
		}

		if (sum % 2 == 0) {
			List<Integer> first = new ArrayList<>();
			List<Integer> second = new ArrayList<>();
			if (n % 2 == 0) {
				for (int i = 1; i <= numbers.length / 2; i++) {
					if (i % 2 == 0) {
						second.add(i);
						second.add(numbers.length - i);
					} else {
						first.add(i);
						first.add(numbers.length - i);
					}
				}
			} else {
				for (int i = 0; i < numbers.length / 2; i++) {
					if (i == 0) {
						first.add(n);
						continue;
					}
					if (i % 2 == 0) {
						first.add(i);
						first.add(n - i);
					} else {
						second.add(i);
						second.add(n - i);
					}
				}
			}
			outBuffer.append("YES").append("\n");
			outBuffer.append(first.size()).append("\n");
			for (int i = 0; i < first.size(); i++) {
				outBuffer.append(first.get(i)).append(" ");
			}
			outBuffer.append("\n").append(second.size()).append("\n");
			for (int i = 0; i < second.size(); i++) {
				outBuffer.append(second.get(i)).append(" ");
			}
		} else {
			outBuffer.append("NO");
		}
		System.out.println(outBuffer);

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
