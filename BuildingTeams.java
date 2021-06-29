
import java.util.*;
import java.io.*;

class BuildingTeams {
	static StringBuilder outBuffer = new StringBuilder();

	public static void main(String[] args) {
		boolean flag = true;
		Vertex[] vertices = readGraph();
		for (int i = 1; i < vertices.length; i++) {
			if (!vertices[i].visited) {
				vertices[i].visited = true;
				vertices[i].color = 1;
				flag = dfs(vertices[i]);
				if (!flag) {
					break;
				}
			}
		}
		if (flag) {
			for (int j = 1; j < vertices.length; j++) {
				outBuffer.append(vertices[j].color).append(" ");
			}
		} else {
			outBuffer.append("IMPOSSIBLE");
		}

		System.out.println(outBuffer);
	}

	static boolean dfs(Vertex vertex) {
		for (int i = 0; i < vertex.adjacentVertices.size(); i++) {
			Vertex next = vertex.adjacentVertices.get(i);
			if (!next.visited) {
				next.visited = true;
				next.color = 3 - vertex.color;
				if (!dfs(next)) {
					return false;
				}
			} else if (next.color == vertex.color) {
				return false;
			}
		}
		return true;
	}

	static Vertex[] readGraph() {
		int n = ni();
		Vertex[] vertices = new Vertex[n + 1];
		for (int i = 1; i < n + 1; i++) {
			vertices[i] = new Vertex(i);
		}

		int m = ni();
		for (int i = 0; i < m; i++) {
			Vertex u = vertices[ni()];
			Vertex v = vertices[ni()];
			u.addVertex(v);
			v.addVertex(u);
		}
		return vertices;
	}

	static class Vertex {
		public int id;
		public boolean visited = false;
		public int color = -1;
		public List<Vertex> adjacentVertices = new ArrayList<Vertex>();

		public Vertex(int id) {
			this.id = id;
		}

		public void addVertex(Vertex vertex) {
			adjacentVertices.add(vertex);
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
