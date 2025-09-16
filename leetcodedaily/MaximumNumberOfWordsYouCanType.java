package leetcodedaily;

import java.util.HashSet;

public class MaximumNumberOfWordsYouCanType {
    public int canBeTypedWords(String text, String brokenLetters) {
        String [] strings=text.split(" ");
        HashSet<Character> brokenCharacters = new HashSet<>();
        for (char c : brokenLetters.toCharArray()) {
            brokenCharacters.add(c);
        }
        int count =0;
        for (String word:strings){
            if(!hasBrokenLetters(word,brokenCharacters)){
                count++;
            }
        }
        return count;
    }
    boolean hasBrokenLetters(String s,HashSet<Character> brokenCharacters){
        for (char ch:s.toCharArray()){
            if (brokenCharacters.contains(ch)){
                return true;
            }
        }
        return false;
    }
}
