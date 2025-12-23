package algorithms.graph.traversal.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class CycleDetectionInUndirectedGraph {
    // Function to check cycle in an undirected graph using BFS
    boolean isCycleBFS(int val, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {

        // Queue will store {currentNode, parentNode}
        Queue<int[]> q = new ArrayDeque<>();

        // Start BFS from the given node, parent = -1 (no parent)
        q.offer(new int[]{val, -1});
        vis[val] = true; // mark starting node as visited

        while (!q.isEmpty()) {

            // Get current node and its parent
            int[] curr = q.poll();
            int node = curr[0];
            int par = curr[1];

            // Traverse all adjacent nodes
            for (int child : adj.get(node)) {

                // If adjacent node is not visited, visit it
                if (!vis[child]) {
                    vis[child] = true;              // mark visited while enqueueing
                    q.offer(new int[]{child, node}); // current node becomes parent
                }
                // If adjacent node is visited and is NOT the parent
                // then a back-edge exists → cycle detected
                else if (child != par) {
                    return true;
                }
            }
        }

        // No cycle found in this connected component
        return false;
    }

    // Function to detect cycle in the entire undirected graph
    boolean cycleDetection(ArrayList<ArrayList<Integer>> adj) {

        int v = adj.size();
        boolean[] vis = new boolean[v]; // visited array

        // Loop to handle disconnected graph
        for (int i = 0; i < v; i++) {

            // If a node is not visited, start BFS from it
            if (!vis[i]) {

                // If cycle found in any component, return true
//                if (isCycleBFS(i, adj, vis)) {
//                    return true;
//                }

                // If a cycle is detected in any component, return true
                if(isCycleDFS(i,-1,adj,vis)){
                    return true;
                }
            }
        }

        // No cycle found in any component
        return false;
    }

// DFS helper function to detect cycle
// node   → current node
// parent → node from which current node was visited
//    In an undirected graph, during DFS, if we visit an already visited node that is not the parent,
//    it indicates a back-edge, hence a cycle.
    private boolean isCycleDFS(int node, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] vis){
//        Mark the node as visited
        vis[node]=true;
//        Traverse all adjacent nodes
        for (int child:adj.get(node)){
//        If the adjacent node is not visited, recurse
            if(!vis[child]){

                if(isCycleDFS(child,node,adj,vis)){
                    return true; // cycle found in recursion
                }
            }
//          If the adjacent node is visited and is NOT the parent,
//          then a back-edge exists → cycle detected
            else if (parent!=child){
                return true;
            }
        }
//      No cycle found from this path
        return false;
    }
}
