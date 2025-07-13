package leetcodedaily;

import java.util.Arrays;

public class MatchPlayersAndTrainers {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
//        Arrays.sort(players);
//        Arrays.sort(trainers);
//        int n=players.length;
//        int m=trainers.length;
//        int i=0,j=0;
//        int ans=0;
//        while (i<n && j<m){
//            int curr=players[i];
//            while (j<m && trainers[j]<curr){
//                j++;
//            }
//            if(j<m && trainers[j]>=curr){
//                ans++;
//                j++;
//            }
//            i++;
//        }
//        return ans;
        Arrays.sort(players);
        Arrays.sort(trainers);
        int pla=0;
        int tra=0;
        while(pla<players.length && tra<trainers.length){
            if(players[pla]<=trainers[tra]){
                pla++;
            }
            tra++;
        }
        return pla;
    }
}
