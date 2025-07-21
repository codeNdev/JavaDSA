package leetcodedaily;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Stack;

public class DeleteCharactersToMakeFancyString {
//    public String makeFancyString(String s) {
//        StringBuilder sb = new StringBuilder();
//        Deque<int[]> stack = new ArrayDeque<>();
//
//        for (char c : s.toCharArray()) {
//            if (stack.isEmpty() || stack.peek()[0] != c) {
//                // New run: push char with count=1
//                stack.push(new int[]{c, 1});
//            } else {
//                // Same char as top
//                int[] top = stack.peek();
//                if (top[1] < 2) {
//                    // Increment count and push again so we track the position
//                    top[1]++;
//                    stack.push(new int[]{c, top[1]});
//                }
//                // If top[1] is already 2, do nothing (skip this char)
//            }
//        }
//
//        // Build result from bottom of stack
//        Iterator<int[]> it = stack.descendingIterator();
//        while (it.hasNext()) {
//            sb.append((char) it.next()[0]);
//        }
//
//        return sb.toString();
//    }
    public String makeFancyString(String s) {
        StringBuilder sb = new StringBuilder();
        char prev = '\0';
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == prev) {
                count++;
            } else {
                prev = c;
                count = 1;
            }
            // Append only the first two occurrences of any run
            if (count <= 2) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
