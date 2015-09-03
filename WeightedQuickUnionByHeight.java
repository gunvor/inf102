package chapter1.part5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionByHeight {


	private int[] id;		//parent link (site indexed)
	private int[] height;	//number of nodes connected to root (height)
	private int count;		//number of components
	
	public WeightedQuickUnionByHeight(int N) {
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
		int countHeight = 1;
		
		//follow links to a root
		while(p != id[p]) {
			p = id[p];
			countHeight++;
		}
		
		height[p] = countHeight;
	
		return p;		
	}
	
	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);
		if(i == j)
			return;
		
		//make smaller root larger one
		if(height[i] < height[j]) {
			id[i] = id[j];
			height[j] += height[i];
		} else {
			id[j] = id[i];
			height[i] += height[j];
		}
		
		count--;
	}
	
	public static void main(String[] args) {

		int N = StdIn.readInt();
		QuickUnionWithPathCompression qu = new QuickUnionWithPathCompression(N);
		
		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if(qu.connected(p, q))
				continue;
			qu.union(p, q);
			StdOut.println(p + " " + q);
		}
		
		StdOut.println(qu.count() + " components");	
	}
	
	
	
}
