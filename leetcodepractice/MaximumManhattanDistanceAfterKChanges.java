package leetcodepractice;

public class MaximumManhattanDistanceAfterKChanges {
    public int maxDistance(String S, int k) {
        int x=0,y=0;
        int n=0,s=0,e=0,w=0;
        int ans=0;
        for(char ch:S.toCharArray()){
            switch (ch) {
                case 'N' -> {
                    y++; n++;
                }
                case 'S' -> {
                    y--; s++;
                }
                case 'E' -> {
                    x++; e++;
                }
                case 'W' -> {
                    x--; w++;
                }
            }
            int mnX=Math.min(e,w);
            int mnY=Math.min(n,s);
            int curr=Math.abs(x)+Math.abs(y) + 2*Math.min(k,mnX+mnY);
            ans=Math.max(ans,curr);
        }
        return ans;
    }
}
