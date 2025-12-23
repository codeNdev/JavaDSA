package algorithms.graph.traversal.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class RottenOranges {
//    Given an n*m grid
//    Each cell in the grid is :
//    0-> empty cell
//    1-> fresh orange
//    2-> rotten orange

    public int orangesRotting(int[][] grid) {
        Queue<int []> q=new ArrayDeque<>();
//        i,j
        int n=grid.length;
        int m=grid[0].length;
        int freshOrange=0;
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if(grid[i][j]==2){
                    q.add(new int[]{i, j});
                }else if(grid[i][j]==1){
                    freshOrange++;
                }
            }
        }
        if(freshOrange==0)return 0;
        int []dx={-1,1,0,0};
        int []dy={0,0,-1,1};
        int time=0;
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                int[] node=q.poll();
                for(int k=0;k<4;k++){
                    int x=node[0]+dx[k];
                    int y=node[1]+dy[k];
                    if(x<n && y<m && x>=0 && y>=0 && grid[x][y]==1){
                        q.add(new int[]{x, y});
                        grid[x][y]=2;
                        freshOrange--;
                    }
                }
            }
            time++;
        }
        if(freshOrange!=0){
            return -1;
        }
        return time-1;
    }
}
