package algorithms.graph.traversal.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class NumberOfProvinces {
    void bfs(int i, boolean[] vis, int [][] isConnected){
        int n=isConnected.length;
        Queue<Integer> q=new ArrayDeque<>();
        q.add(i);
        vis[i]=true;
        while (!q.isEmpty()){
            int size=q.size();
            for(int j=0;j<size;j++){
                int node=q.poll();
                for(int k=0;k<n;k++){
                    if(isConnected[node][k]==1 && !vis[k] && node!=k){
                        vis[k]=true;
                        q.add(k);
                    }
                }
            }
        }
    }
    public int findCircleNum(int[][] isConnected) {
        int n=isConnected.length;
        boolean[] vis=new boolean[n];
        int ans=0;
        for(int i=0;i<n;i++){
            if(!vis[i]){
                bfs(i,vis,isConnected);
                ans++;
            }
        }
        return ans;
    }
}
