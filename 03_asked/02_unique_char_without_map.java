// Question : Implement an algorithm to determine if a string has all unique characters.
// What if you cannot use additional data structures?



class Solution_2{

    static int charToIndex(char ch){
        return ch - 'a';
    }

    static Boolean isUnique(String inp){
        int bitVector = 0;
        for (int i =0; i < inp.length(); i++){
            int index = charToIndex(inp.charAt(i));
            int thisPos = 1 << index;
            int result = thisPos & bitVector;
            //System.out.println(bitVector);
            //System.out.println(thisPos);
            if (result == thisPos){
                return false;
            } else{
                bitVector |= thisPos;
            }
        }
        
        return true;
    }

    public static void main(String[] args){
       String inp = "abcde";
       System.out.println(isUnique(inp));

       inp = "abcdea";
       System.out.println(isUnique(inp));
    }
}