import java.io.*;
import java.util.*;

public class Labyrinth {
	
	static int n, m;
	static char[][] grid;
	static Pair end;

	public static void main(String[] args) {
		StringBuilder outBuffer = new StringBuilder();
		n = ni();
		m = ni();

		grid = new char[n][m];
		boolean flag = false;
		int sx = 0, sy = 0;
		for (int i = 0; i < n; i++) {
			grid[i] = ns().toCharArray();
			if (!flag) {
				for (int j = 0; j < m; j++) {
					if (grid[i][j] == 'A') {
						sx = i;
						sy = j;
						flag = true;
						break;
					}
				}
			}
		}

		if (bfs(sx,sy)) {
			outBuffer.append("YES\n");
			StringBuilder s = new StringBuilder();

			while (end.x != sx || end.y != sy) {
				s.append(end.direct);
				end = end.parentCell;
			}
			s.reverse();
			outBuffer.append(s.length()).append("\n").append(s);
		} else {
			outBuffer.append("NO\n");
		}
		System.out.println(outBuffer);
	}

	static boolean bfs(int row, int col) {
		int dRow[] = { -1, 0, 1, 0 };
		int dCol[] = { 0, 1, 0, -1 };
		String ds[] = { "U", "R", "D", "L" };
		
		Queue<Pair> q = new ArrayDeque<>();
		q.offer(new Pair(row, col));
		grid[row][col] = '#';

		while (!q.isEmpty()) {
			Pair cell = q.poll();
			int x = cell.x;
			int y = cell.y;

			if (grid[x][y] == 'B') {
				end = cell;
				return true;
			}
			for (int i = 0; i < 4; i++) {
				int nx = x + dRow[i];
				int ny = y + dCol[i];
				if (isValid(nx, ny)) {
					Pair pair = new Pair(nx, ny);
					pair.direct = ds[i];
					pair.parentCell = cell;
					q.offer(pair);
					grid[x][y] = '#';
				}
			}
		}
		return false;
	}

	static boolean isValid(int x, int y) {
		if (x < 0 || y < 0 || x >= n || y >= m)
			return false;
		if (grid[x][y] == '#')
			return false;
		return true;
	}

	static class Pair {
		Pair parentCell;
		int x, y;
		String direct;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
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