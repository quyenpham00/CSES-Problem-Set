import java.util.*;
import java.io.*;

public class CompanyQueriesI {
	static HashMap<Integer, ArrayList<Vertex>> levels = new HashMap<>();

	public static void main(String[] args) {
		StringBuilder outBuffer = new StringBuilder();
		int n = ni();
		int q = ni();

		Vertex[] vertices = readGraph(n);
		for (int i = 1; i <= n; i++) {
			levels.put(i, new ArrayList<Vertex>());
		}

		for (int i = 0; i < q; i++) {
			int v = ni();
			ArrayList<Vertex> listVertices = levels.get(v);
			if (listVertices.isEmpty()) {
				for (int j = 1; j < vertices.length; j++) {
					vertices[j].visited = false;
				}
				bfs(vertices[v], n);
				listVertices = levels.get(v);
			}
			int k = ni();
			if (k < listVertices.size()) {
				outBuffer.append(listVertices.get(k).id).append("\n");
			} else {
				outBuffer.append(-1).append("\n");
			}
		}
		System.out.println(outBuffer);
	}

	static void bfs(Vertex vertex, int n) {
		Queue<Vertex> queue = new ArrayDeque<>();
		queue.add(vertex);
		vertex.level = 0;
		while (!queue.isEmpty()) {
			Vertex element = queue.remove();
			if (vertex.id == 1) {
				break;
			}
			levels.get(vertex.id).add(element);

			List<Vertex> adjacentVertices = element.getNeighbor();
			for (int i = 0; i < adjacentVertices.size(); i++) {
				Vertex v = adjacentVertices.get(i);
				if (v != null && !v.visited) {
					queue.add(v);
					v.visited = true;
					v.level = element.level + 1;
				}
			}
		}

	}

	static Vertex[] readGraph(int n) {

		Vertex[] vertices = new Vertex[n + 1];
		for (int i = 1; i <= n; i++) {
			vertices[i] = new Vertex(i);
		}
		for (int i = 2; i <= n; i++) {
			Vertex u = vertices[i];
			Vertex v = vertices[ni()];
			u.addVertex(v);
		}

		return vertices;
	}

	static class Vertex {
		public int id;
		public int level = 1;
		public boolean visited = false;
		public List<Vertex> adjacentVertices = new ArrayList<Vertex>();

		public Vertex(int id) {
			this.id = id;
		}

		public void addVertex(Vertex vertex) {
			adjacentVertices.add(vertex);
		}

		public List<Vertex> getNeighbor() {
			return adjacentVertices;
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