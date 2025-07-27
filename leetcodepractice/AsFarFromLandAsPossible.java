package leetcodepractice;

import java.util.ArrayDeque;
import java.util.Queue;

public class AsFarFromLandAsPossible {
    public int maxDistance(int[][] grid) {
        Queue<int[]> queue=new ArrayDeque<>();
        int n=grid.length;
        boolean [][] vis=new boolean[n][n];
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                if(grid[i][j]==1){
                    queue.add(new int[] {i,j});
                    vis[i][j]=true;
                }
            }
        }
        if(queue.size()==n*n){
            return -1;
        }
        int dist=0;
        int []dx={-1,1,0,0};
        int []dy={0,0,-1,1};
        while (!queue.isEmpty()){
            int size= queue.size();
            while (size>0){
                int [] front=queue.poll();
                int x=front[0];
                int y=front[1];
                for (int k=0;k<4;k++){
                    int p=x+dx[k];
                    int q=y+dy[k];
                    if(q<0 || p<0 || q>=n || p>=n){
                        continue;
                    }
                    if(!vis[p][q]){
                        queue.add( new int[] {p,q});
                        vis[p][q]=true;
                    }
                }
                size--;
            }
            dist++;
        }
        return dist-1;
    }
}
