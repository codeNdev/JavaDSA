package algorithms.graph.traversal.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int n = image.length, m = image[0].length;
        int original = image[sr][sc];

        // Edge case: nothing to change
        if (original == color) return image;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc});
        image[sr][sc] = color; // mark visited

        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int x = cell[0], y = cell[1];

            for (int[] d : dirs) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m
                        && image[nx][ny] == original) {
                    image[nx][ny] = color;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        return image;
    }
}
