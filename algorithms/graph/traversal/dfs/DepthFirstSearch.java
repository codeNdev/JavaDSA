package algorithms.graph.traversal.dfs;

import java.util.ArrayList;

public class DepthFirstSearch {
    public void dfsOfGraph(int node, boolean[] vis , ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> dfs){
        vis[node]=true;
        dfs.add(node);
        for (int child:adj.get(node)){
            if(!vis[child]){
                dfsOfGraph(child,vis,adj,dfs);
            }
        }
    }

    public static void main() {

        ArrayList < ArrayList < Integer >> adj = new ArrayList < > ();
        for (int i = 0; i < 5; i++) {
            adj.add(new ArrayList < > ());
        }
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(4);
        adj.get(4).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(1).add(3);
        adj.get(3).add(1);
//              o ---- 1---2
//              |      |
//              4      3
//
//
        DepthFirstSearch sl=new DepthFirstSearch();
//      DFS traversal of the graph
        System.out.println("DFS Traversal: ");
        ArrayList<Integer> dfs=new ArrayList<>();
        boolean[] vis=new boolean[5];
        sl.dfsOfGraph(0,vis,adj,dfs);
        for (int i:dfs){
            System.out.print(i+" ");
        }
    }
}
