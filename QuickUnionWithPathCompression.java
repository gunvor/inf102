package chapter1.part5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionWithPathCompression {

	private int[] id;		//access to component id (site indexed)
	private int count;		//number of components
	
	public QuickUnionWithPathCompression(int N) {
		count = N;
		id = new int[N];
		for(int i = 0; i < N; i++) {
			id[i] = i;
		}
	}
	
	public int count() {
		return count;
	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	public int find(int p) {
		//TODO tror denne fungerer - men klarer ikke sjekke pga at main ikke fungerer som den skal...
		int pOld = p;
		int root, temp;
		
		//finding the root
		while(p != id[p]) {
			p = id[p];
		}
		
		root = p;
		p = pOld;
		
		//changing every id to root
		while(id[p] != root) {
			temp = id[p];
			id[p] = root;
			p = temp;
		}
		
		return p;		
	}
	
	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);
		if(i == j)
			return;
		
		id[i] = j;
		
		count--;
	}
	
	public static void main(String[] args) {

		//read number of sites and initialize that many components
		int N = StdIn.readInt();
		QuickUnionWithPathCompression qu = new QuickUnionWithPathCompression(N);
		
		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();	//read pair to connect
			if(qu.connected(p, q))		//ignore if connected
				continue;
			qu.union(p, q);				//combine components and print connections
			StdOut.println(p + " " + q);
		}
		
		StdOut.println(qu.count() + " components");	
	}
}
