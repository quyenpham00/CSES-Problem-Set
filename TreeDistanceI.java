import java.util.*;
import java.io.*;

class TreeDistanceI {
	
	public static void main(String[] args) {
		StringBuilder outBuffer = new StringBuilder();
		int totalWeight = ni();
		Vertex[] vertices = readGraph(totalWeight);
		
		for (int i = 1; i < vertices.length; i++) {
			if (vertices[i].adjacentVertices.size() == 1) {
				dfs(vertices[i],totalWeight);
				break;
			}
		}

		long[] res = new long[] { -1, 0, Long.MAX_VALUE };
		for (int i = 1; i < vertices.length; i++) {
			Vertex vertex = vertices[i];
			long a = vertex.left, b = vertex.right;
			if (vertex.adjacentVertices.size() == 2) {
				if (a > b) {
					a = vertex.right;
					b = vertex.left;
				}
				if (res[2] - res[1] > b - a) {
					res = new long[] { vertex.id, a, b };
				}
			}
		}

		if (res[0] != -1) {
			outBuffer.append(res[0]).append(" ").append(res[1]).append(" ").append(res[2]);
		} else {
			outBuffer.append(-1);
		}
		System.out.println(outBuffer);

	}

	static long dfs(Vertex vertex,int totalWeight) {
		vertex.visited = true;
		long w = 1;
		long temp = 0;
		for (Vertex next : vertex.adjacentVertices) {
			if (!next.visited) {
				long res = dfs(next,totalWeight);
				if (vertex.adjacentVertices.size() == 2) {
					long other = Math.abs(totalWeight - w - res);
					vertex.left = res;
					vertex.right = other;
				}
				w += res;
			}
		}
		temp = w;
		return temp;
	}

	static Vertex[] readGraph(int n) {
		
		Vertex[] vertices = new Vertex[n + 1];
		for (int i = 1; i <= n; i++) {
			vertices[i] = new Vertex(i);
		}

		for (int i = 0; i < n - 1; i++) {
			Vertex u = vertices[ni()];
			Vertex v = vertices[ni()];
			u.addVertex(v);
			v.addVertex(u);
		}

		return vertices;
	}

	static class Vertex {
		public int id;
		public long left;
		public long right;
		public boolean visited = false;
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
