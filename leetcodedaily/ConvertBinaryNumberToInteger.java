package leetcodedaily;
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class ConvertBinaryNumberToInteger {
    private int length(ListNode head, int [] ans){
        if(head==null){
            return 0;
        }
        int len=length(head.next,ans);
        if(head.val==1){
            ans[0]+=(1<<len);
        }
        return 1+len;
    }
    public int getDecimalValue(ListNode head) {
        int []ans=new int[1];
        ans[0]=0;
        length(head,ans);
        return ans[0];
    }
}
