//-----------------------------------------------------
// Title: TrieST class
// Author:Do�uhan Cumao�lu Doruk Arslan
// Description: TrieST object function and variable comes from here.
//-----------------------------------------------------

public class TrieST<Value> {
	private static final int R = 256;
	private Node root = new Node();

	private static class Node {
		private Object value;
		private Node[] next = new Node[R];
	}

	public void put(String key, Value val) {
		root = put(root, key, val, 0);
	}

	private Node put(Node x, String key, Value val, int d) {
		if (x == null)
			x = new Node();
		if (d == key.length()) {
			x.value = val;
			return x;
		}
		char c = key.charAt(d);
		x.next[c] = put(x.next[c], key, val, d + 1);
		return x;
	}

	public boolean contains(String key) {
		return get(key) != null;
	}

	public Value get(String key) {
		Node x = get(root, key, 0);
		if (x == null)
			return null;
		return (Value) x.value;
	}

	private Node get(Node x, String key, int d) {
		if (x == null)
			return null;
		if (d == key.length())
			return x;
		char c = key.charAt(d);
		return get(x.next[c], key, d + 1);
	}

	public Iterable<String> keys() {
		Queue<String> queue = new Queue<String>();
		collect(root, "", queue);
		return queue;
	}

	private void collect(Node x, String prefix, Queue<String> q) {
		if (x == null)
			return;
		if (x.value != null)
			q.enqueue(prefix);
		for (char c = 0; c < R; c++)
			collect(x.next[c], prefix + c, q);
	}

	public Iterable<String> keysWithPrefix(String prefix) {
		Queue<String> queue = new Queue<String>();
		Node x = get(root, prefix, 0);
		collect(x, prefix, queue);
		
		
		return queue;
	}

	public Iterable<String> keysWithSuffix(String suffix) {
		Queue<String> queue = new Queue<String>();

		Suffixcollect(root, "", queue, suffix);
		return queue;
	}

	private void Suffixcollect(Node x, String prefix, Queue<String> q, String suffix) {
		if (x == null)
			return;
		if (x.value != null) {
			
			int suffixLenght = suffix.length();
			
			if(suffixLenght > prefix.length()) {
				return;
			}
			String obtainedSuffix = "";
			for (int i = prefix.length() - suffixLenght; i < prefix.length(); i++) {
				
				obtainedSuffix += prefix.charAt(i);
			}
			if (obtainedSuffix.equals(suffix) ) {
				q.enqueue(prefix);
			}

		}

		for (char c = 0; c < R; c++)
			Suffixcollect(x.next[c], prefix + c, q, suffix);
	}

	public Iterable<String> keysThatMatch(String pat) {
		Queue<String> queue = new Queue<String>();
		collect(root, "", pat, queue);
		return queue;
	}

	private void collect(Node x, String prefix, String pat, Queue<String> q) {
		if (x == null)
			return;
		int d = prefix.length();
		if (d == pat.length() && x.value != null)
			q.enqueue(prefix);
		if (d == pat.length())
			return;
		char next = pat.charAt(d);
		for (char c = 0; c < R; c++)
			if (next == '.' || next == c)
				collect(x.next[c], prefix + c, pat, q);
	}

	public String longestPrefixOf(String query) {
		int length = search(root, query, 0, 0);
		return query.substring(0, length);
	}

	private int search(Node x, String query, int d, int length) {
		if (x == null)
			return length;
		if (x.value != null)
			length = d;
		if (d == query.length())
			return length;
		char c = query.charAt(d);
		return search(x.next[c], query, d + 1, length);
	}


}