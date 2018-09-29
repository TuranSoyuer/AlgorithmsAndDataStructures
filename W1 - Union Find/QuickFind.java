package com.algorithms.unionFind;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class QuickFind {
    private int[] id;

    public QuickFind(int N) {
        this.id = new int[N];
        for(int i = 0; i < N; i++){
            id[i] = i;
        }
    }

    public boolean connected(int p, int q){
        return id[p] == id[q];
    }

    public void union(int p, int q){
        int pid = id[p];
        int qid = id[q];
        for(int i = 0; i < id.length; i++){
            if (id[i] == pid) id[i] = qid;
        }
    }

    public static void main(String[] args) throws IOException {
        // write your code here
        File file = new File("codeforces.round.bes.yuz.on.ıkı.C:\\Users\\Turan\\Desktop\\Coding\\CourseEra\\Data Structures&Algorithms\\" +
                "Coding\\Part1\\Week1.Dynamic Connectivity Problem - Union Find Algorithm\\Input\\input2.txt");

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int N = Integer.parseInt(br.readLine());
        List<Pair> unionList = new ArrayList<Pair>();
        String st;
        while ((st = br.readLine()) != null) {
            String[] vars = st.split(" ");
            int p = Integer.parseInt(vars[0]);
            int q = Integer.parseInt(vars[1]);
            Pair pair = new Pair(p,q);
            unionList.add(pair);
        }

        Calendar calendar = Calendar.getInstance();
        long startTime = calendar.getTimeInMillis();
        QuickFind quickFind = new QuickFind(N);
        for(Pair union : unionList)
            quickFind.union(union.p, union.q);

        for(int i = 0; i < N; i++){
            for(int j = i+1; j < N; j++)
              System.out.println((i) + " " + (j) + " is " + (quickFind.connected(i,j) ? "connected" : "not connected") );
        }

        Calendar calendar2 = Calendar.getInstance();
        long endTime = calendar2.getTimeInMillis();

        System.out.println("Time in ms : " + (endTime - startTime)  );
    }
}
