package leetcodedaily;

import java.util.Arrays;

public class MinimumNumberOfOperationsToMakeArraysSimilar {
    public long makeSimilar(int[] nums, int[] target) {
//        Sorting array within range
        int n=nums.length;
        int oddCount = (int) Arrays.stream(nums).filter(x -> (x & 1) == 1).count();
        int evenCount=n-oddCount;
        int[] oddNums=new int[oddCount];
        int[] evenNums=new int[n-oddCount];
        int odd=0,even=0;
        for (int i:nums){
            if((i&1)==1){
                oddNums[odd++]=i;
            }else{
                evenNums[even++]=i;
            }
        }
        odd=0;
        even=0;
        int[] oddTarget=new int[oddCount];
        int[] evenTarget=new int[n-oddCount];
        for (int i:target){
            if((i&1)==1){
                oddTarget[odd++]=i;
            }else{
                evenTarget[even++]=i;
            }
        }
        // Since the array is always possible to make similar
        // Total sum of elements of both the array is always equal
        // Since, an operation can't change the sum of elements
        // Number of increments required == number of decrement required
        Arrays.sort(oddNums);
        Arrays.sort(evenNums);
        Arrays.sort(oddTarget);
        Arrays.sort(evenTarget);
        long increment=0,decrement=0;
        for(int i=0;i<oddCount;i++){
            if(oddNums[i]>oddTarget[i]){
                decrement+=((oddNums[i]-oddTarget[i])/2);
            }else{
                increment+=((oddTarget[i]-oddNums[i])/2);
            }
        }
        for(int i=0;i<evenCount;i++){
            if(evenNums[i]>evenTarget[i]){
                decrement+=((evenNums[i]-evenTarget[i])/2);
            }else{
                increment+=((evenTarget[i]-evenNums[i])/2);
            }
        }
        return increment;
    }
}
