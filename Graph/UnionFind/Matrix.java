package Graph.UnionFind;

import java.util.ArrayList;
import java.util.HashSet;

class DSU{
    ArrayList<Integer> parent,size;
    public DSU(int n){
        parent=new ArrayList<>(n+1);
        size=new ArrayList<>(n+1);
        for(int i=0;i<=n;i++){
            parent.add(i);
            size.add(1);
        }
    }
    int findParent(int node){
        if(parent.get(node)==node){
            return node;
        }
        int par=findParent(parent.get(node));
        parent.set(node,par);
        return par;
    }
    void unionBySize(int u,int v){
        int pu=findParent(u);
        int pv=findParent(v);
        if(pu==pv){
            return;
        }
        if(size.get(pu)>size.get(pv)){
//            make pu as ultimate parent
            parent.set(pv,pu);
            size.set(pu,size.get(pu)+size.get(pv));
        }else {
//            make pv as ultimate parent
            parent.set(pu,pv);
            size.set(pv,size.get(pu)+size.get(pv));
        }
    }
    boolean isOnSameSet(int u,int v){
        int pu=findParent(u);
        int pv=findParent(v);
        if(pu==pv){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
public class Matrix {
//    https://leetcode.com/problems/making-a-large-island/
//    Leetcode: 827
    public int largestIsland(int[][] grid) {
        int row=grid.length;
        int col=grid[0].length;
        DSU ds=new DSU(row*col);
        int ans=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]==0){
                    continue;
                }
                int curr=col*i+j;
//                left
                if(j-1>=0 && grid[i][j-1]==1){
                    ds.unionBySize(curr-1,curr);
                }
//                right
                if (j+1<col && grid[i][j+1]==1){
                    ds.unionBySize(curr,curr+1);
                }
//                up
                if(i-1>=0 && grid[i-1][j]==1){
                    ds.unionBySize(curr,curr-col);
                }
//                down
                if(i+1<row && grid[i+1][j]==1){
                    ds.unionBySize(curr,curr+col);
                }
                int par=ds.findParent(curr);
                ans=Math.max(ans,ds.size.get(par));
            }
        }
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                if(grid[i][j]==0){
                    HashSet<Integer> neighbours=new HashSet<>();
                    int curr=col*i+j;
//                left
                    if(j-1>=0 && grid[i][j-1]==1){
                        neighbours.add(ds.findParent(curr-1));
                    }
//                right
                    if (j+1<col && grid[i][j+1]==1){
                        neighbours.add(ds.findParent(curr+1));
                    }
//                up
                    if(i-1>=0 && grid[i-1][j]==1){
                        neighbours.add(ds.findParent(curr-col));
                    }
//                down
                    if(i+1<row && grid[i+1][j]==1){
                        neighbours.add(ds.findParent(curr+col));
                    }
                    int total=1;
                    for(Integer nbr:neighbours){
                        total+=ds.size.get(nbr);
                    }
                    ans=Math.max(ans,total);
                }
            }
        }
        return ans;
    }
}
