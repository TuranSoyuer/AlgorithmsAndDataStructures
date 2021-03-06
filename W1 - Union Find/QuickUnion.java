package com.algorithms.unionFind;

public class QuickUnion {
    private int[] id;

    public QuickUnion(int N) {
        this.id = new int[N];
        for(int i = 0; i < N; i++){
            id[i] = i;
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
        int pRoot = root(p);
        int qRoot = root(q);
        id[qRoot] = pRoot;
    }
}
