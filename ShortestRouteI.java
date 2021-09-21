import java.util.*;
import java.io.*;

class ShortestRouteI {

	public static void main(String[] args) {
		StringBuilder outBuffer = new StringBuilder();
		int n = ni();
		int m = ni();
		Node[] nodes = readGraph(n, m);

		dijkstra(nodes[1]);

		for (int i = 1; i <= n; i++) {
			outBuffer.append(nodes[i].distance).append(" ");
		}
		System.out.println(outBuffer);
	}

	static void dijkstra(Node node) {
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(node);
		node.distance = 0;

		while (!q.isEmpty()) {
			Node u = q.poll();
			u.visited = true;
			while (!u.adjacentEdges.isEmpty()) {
				Edge e = u.adjacentEdges.poll();
				Node targetNode = e.n;
				if (!targetNode.visited) {
					long totalDis = u.distance + e.w;
					if (targetNode.distance > totalDis) {
						targetNode.distance = totalDis;
						q.offer(targetNode);
					}
				}
			}
				
			
		}
	}

	static Node[] readGraph(int n, int m) {

		Node[] nodes = new Node[n + 1];
		for (int i = 1; i <= n; i++) {
			nodes[i] = new Node(i);
		}

		for (int i = 0; i < m; i++) {
			Node u = nodes[ni()];
			Node v = nodes[ni()];
			long w = nl();
			u.addEdge(new Edge(v, w));
		}
		return nodes;
	}

	static class Edge {
		Node n;
		long w;

		public Edge(Node n, long w) {
			this.n = n;
			this.w = w;
		}
	}

	static class Node implements Comparable<Node> {
		public int id;
		public boolean visited = false;
		public long distance = Long.MAX_VALUE;
		public Queue<Edge> adjacentEdges = new ArrayDeque<>();

		public Node(int id) {
			this.id = id;
		}

		public void addEdge(Edge edge) {
			adjacentEdges.add(edge);
		}

		@Override
		public int compareTo(Node otherNode) {
			return Long.compare(this.distance, otherNode.distance);
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