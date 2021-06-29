import java.io.*;
import java.util.*;

public class RestaurantCustomers {
	static long max = Integer.MIN_VALUE;

	public static void main(String[] args) {
		interval_improved();
	}

	public static void interval_improved() {
		int n = ni();
		long max = Integer.MIN_VALUE;
		TreeMap<Long, Long> customerCounts = new TreeMap<>();
		for (int i = 0; i < n; i++) {
			long start = nl();
			long end = nl();
			long value;
			if (customerCounts.get(start) != null) {
				value = customerCounts.get(start) + 1;
			} else {
				value = 1;
			}
			customerCounts.put(start, value);
			if (customerCounts.get(end) != null) {
				value = customerCounts.get(end) - 1;
			} else {
				value = -1;
			}
			customerCounts.put(end, value);
		}
		long sum = 0;
		for (long next : customerCounts.values()) {
			sum += next;
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}

	public static void interval() {
		int n = ni();
		long max = Integer.MIN_VALUE;
		long[] countCustomer = new long[1000001];
		for (int i = 0; i < n; i++) {
			countCustomer[ni()]++;
			countCustomer[ni()]--;
		}
		long sum = 0;
		for (int i = 0; i < countCustomer.length; i++) {
			sum += countCustomer[i];
			max = Math.max(max, sum);
		}
		System.out.println(max);
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
