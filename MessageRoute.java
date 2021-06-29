import java.util.*;
import java.io.*;

public class MessageRoute {

	public static void main(String[] args) {
		StringBuilder outBuffer = new StringBuilder();
		int n = ni();
		Node[] nodes = readGraph(n);
		bfs(nodes[1]);
		List<Node> shortestPath = new ArrayList<>();
		Node parent = nodes[n];
		shortestPath.add(parent);
		while (parent != null && parent.id != 1) {
			parent = parent.parent;
			shortestPath.add(parent);
		}
		if (shortestPath.get(shortestPath.size() - 1) == null || shortestPath.get(shortestPath.size() - 1).id != 1) {
			outBuffer.append("IMPOSSIBLE");
		} else {
			outBuffer.append(nodes[n].level).append("\n");
			for (int i = shortestPath.size() - 1; i >= 0; i--) {
				outBuffer.append(shortestPath.get(i).id).append(" ");
			}
		}
		System.out.println(outBuffer);
	}

	static void bfs(Node node) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(node);
		while (!queue.isEmpty()) {
			Node element = queue.remove();
			List<Node> adjacentNodes = element.getNeighbor();
			for (int i = 0; i < adjacentNodes.size(); i++) {
				Node n = adjacentNodes.get(i);
				if (n != null && !n.visited) {
					queue.add(n);
					n.level = element.level + 1;
					n.parent = element;
					n.visited = true;
				}
			}
		}
	}

	static Node[] readGraph(int n) {
		Node[] nodes = new Node[n + 1];
		for (int i = 1; i < n + 1; i++) {
			nodes[i] = new Node(i);
		}

		int m = ni();
		for (int i = 0; i < m; i++) {
			Node u = nodes[ni()];
			Node v = nodes[ni()];
			u.addNeighbor(v);
			v.addNeighbor(u);
		}

		return nodes;
	}

	static class Node {
		public int id;
		public boolean visited = false;
		public int level = 1;
		public Node parent;
		public List<Node> adjacentNodes = new ArrayList<Node>();

		public Node(int id) {
			this.id = id;
		}

		public void addNeighbor(Node node) {
			adjacentNodes.add(node);
		}

		public List<Node> getNeighbor() {
			return adjacentNodes;
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
