package leetcodedaily;

public class ValidWord {
    private boolean isVowel(char ch){
        ch=Character.toLowerCase(ch);
        if(ch=='a'|| ch=='e'||ch=='i'|| ch=='o' || ch=='u'){
            return true;
        }
        return false;
    }
    public boolean isValid(String word) {
        if(word.length()<3){
            return false;
        }
        boolean containsVowel=false,containsConsonant=false;
        for (int i=0;i<word.length();i++){
            if(!Character.isLetterOrDigit(word.charAt(i))){
                return false;
            } else if (Character.isLetter(word.charAt(i))) {
                if (isVowel(word.charAt(i))){
                    containsVowel=true;
                }else {
                    containsConsonant=true;
                }
            }
        }
        return (containsConsonant && containsVowel);
    }
}
