import java.util.*;
import java.io.*;

class RoundTrip {
	static StringBuilder outBuffer = new StringBuilder();
	static List<Integer> output = new ArrayList<>();
	static Vertex start;

	public static void main(String[] args) {
		Vertex[] vertices = readGraph();
		boolean flag = false;
		for (int i = 1; i < vertices.length; i++) {
			if(!vertices[i].visited) {
				if (IsCycle(vertices[i], new Vertex(-1))) {
					printCycle(output);
					flag = true;
					break;
				}
			}
		}
		if (!flag) {
			outBuffer.append("IMPOSSIBLE");
		}
		System.out.println(outBuffer);
	}

	static void printCycle(List<Integer> output) {
		boolean flag = false;
		int pos = output.size();
		for (int i = output.size() - 1; i > 0; i--) {
			if (output.get(i) == start.id) {
				pos = i + 1;
				flag = true;
				break;
			}
		}
		if (!flag) {
			output.add(start.id);
			pos++;
		}
		if (pos > 2) {
			outBuffer.append(pos).append("\n");
			for (int i = 0; i < pos; i++) {
				outBuffer.append(output.get(i)).append(" ");
			}
		} else {
			outBuffer.append("IMPOSSIBLE");
		}

	}

	static boolean IsCycle(Vertex vertex, Vertex root) {
		vertex.visited = true;
		for (Vertex next : vertex.adjacentVertices) {
			if (!next.visited) {
				if (IsCycle(next, vertex)) {
					output.add(next.id);
					return true;
				}
			} else if (next != root) {
				output.add(next.id);
				start = next;
				return true;
			}
		}
		return false;
	}

	static Vertex[] readGraph() {
		int n = ni();
		Vertex[] vertices = new Vertex[n + 1];
		for (int i = 1; i < vertices.length; i++) {
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