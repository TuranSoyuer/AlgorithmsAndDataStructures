package com.algorithms.unionFind;

public class WeightedQuickUnion {
    private int[] id;
    private int[] sz;

    public WeightedQuickUnion(int N) {
        //System.out.println("WeightedQuickUnion");
        this.id = new int[N];
        this.sz = new int[N];
        for(int i = 0; i < N; i++){
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int root(int i){
        while (i != id[i])
            i = id[i];
        return i;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        //System.out.println("union");
        int pRoot = root(p);
        int qRoot = root(q);

        if(pRoot == qRoot) return;

        if(sz[qRoot] < sz[pRoot] ) {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        else{
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
    }
}
