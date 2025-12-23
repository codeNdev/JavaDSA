package algorithms.graph.traversal.dfs;

public class FloodFill {
    int [][] dir={{-1,0},{1,0},{0,1},{0,-1}};
    private void  dfs(int x,int y,int[][] image,int color){
        int n=image.length;
        int m=image[0].length;
        int original=image[x][y];
        image[x][y]=color;
        for(int i=0;i<4;i++){
            int nx=x+dir[i][0];
            int ny=y+dir[i][1];
            if(nx>=0 && ny>=0 && nx<n && ny<m && image[nx][ny]==original){
                dfs(nx,ny,image,color);
            }
        }
    }
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int n = image.length, m = image[0].length;
        int original = image[sr][sc];

        // Edge case: nothing to change
        if (original == color) return image;

        dfs(sr,sc,image,color);
        return image;
    }
}
