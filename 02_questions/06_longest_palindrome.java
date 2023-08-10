// https://leetcode.com/problems/longest-palindromic-substring/
// Question : Given a string, find the longest substring which is palindrome.
// For example, if the given string is "forgeeksskeegfor" the output should be "geeksskeeg"

class LongestPalindrome {
    
    public static String findLength(String inputStr){

        int maxLenght = 1;
        int n = inputStr.length();
        int maxleft = 0, maxRight = 0;

        int i = 1;
        int left, right, count;
        while (i < n){
            left = i - 1 ;
            right = i + 1;
            count = 1;
            while (left >= 0 && right < n){
                if (inputStr.charAt(left) == inputStr.charAt(right)){
                    count += 2;
                    if (maxLenght < count){
                        maxLenght = count;
                        maxleft = left;
                        maxRight = right;
                    }
                }else{
                    break;
                }
                left--;
                right++;
            }
            i++;
        }

        i = 0;
        while (i < n){
            left = i;
            right = i + 1;
            count = 0;
            while (left >= 0 && right < n){
                if (inputStr.charAt(left) == inputStr.charAt(right)){
                    count += 2;
                    if (maxLenght < count){
                        maxLenght = count;
                        maxleft = left;
                        maxRight = right;
                    }
                }else{
                    break;
                }
                left--;
                right++;
            }
            i++;
        }
        
        return inputStr.substring(maxleft, maxRight + 1);
    }

    public static void main(String[] args){
        System.out.println(findLength("forgeeksskeegfor"));
        System.out.println(findLength("babad"));
    }
    
}
