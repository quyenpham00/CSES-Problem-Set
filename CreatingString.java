import java.io.*;
import java.util.*;


public class CreatingString {

	public static void main(String[] args) {
		String string = ns();
		String[] s = new String[string.length()];
		for (int i = 0; i < s.length; i++) {
			s[i] = String.valueOf(string.charAt(i));
		}
		int count = 0;
		while(nextPermutation(s)) {
			count++;
			System.out.println(Arrays.toString(s));
		}
		System.out.println(count);
	}

	static public boolean nextPermutation(String[] s) {
		// find first decreasing digit
		if (s.length <= 1)
			return false;

		int last = s.length - 2;

		// find the longest non-increasing suffix
		// and find the pivot
		while (last >= 0) {
			if (s[last].compareTo(s[last + 1]) < 0) {
				break;
			}
			last--;
		}

		// If there is no increasing pair
		// there is no higher order permutation
		if (last < 0)
			return false;

		int nextGreater = s.length - 1;

		// Find the rightmost successor to the pivot
		for (int i = s.length - 1; i > last; i--) {
			if (s[i].compareTo(s[last]) > 0) {
				nextGreater = i;
				break;
			}
		}

		// Swap the successor and the pivot
		s = swap(s, nextGreater, last);

		// Reverse the suffix
		s = reverse(s, last + 1, s.length - 1);

		return true;
	}

	public static String[] swap(String s[], int left, int right)
	    {
	        // Swap the data
	        String temp = s[left];
	        s[left] = s[right];
	        s[right] = temp;
	  
	        // Return the updated array
	        return s;
	    }

	public static String[] reverse(String s[], int left, int right) {
		// Reverse the sub-array
		while (left < right) {
			String temp = s[left];
			s[left++] = s[right];
			s[right--] = temp;
		}
		// Return the updated array
		return s;
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
