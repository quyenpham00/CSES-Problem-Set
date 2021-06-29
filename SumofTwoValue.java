import java.io.*;
import java.util.*;

public class SumofTwoValue {

	public static void main(String[] args) {
		int n = ni();
		long targetSum = nl();
		Number[] numbers = new Number[n];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = new Number(i + 1, nl());
		}
		Arrays.sort(numbers, (n1, n2) -> {
			return Long.compare(n1.value, n2.value);
		});
		int j = numbers.length - 1;
		boolean flag = false;
		for (int i = 0; i < j; i++) {
			while (j > i) {
				if (numbers[i].value + numbers[j].value == targetSum) {
					System.out.print(numbers[i].index + " " + numbers[j].index);
					flag = true;
					break;
				}
				if (numbers[i].value + numbers[j].value > targetSum) {
					j--;
				} else {
					break;
				}
			}
			if (flag) {
				break;
			}
		}
		if (!flag) {
			System.out.println("IMPOSSIBLE");
		}

	}

	static class Number {
		int index;
		long value;

		public Number(int i, long v) {
			this.index = i;
			this.value = v;
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
