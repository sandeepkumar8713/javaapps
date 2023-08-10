// https://leetcode.com/problems/add-strings/
// Question : Given two non-negative integers, num1 and num2 represented as string,
// return the sum of num1 and num2 as a string.
// You must solve the problem without using any built-in library for handling large
// integers (such as BigInteger). You must also not convert the inputs to integers directly.


class TwoStrings{

    static int charToNum(char ch){
        return ch - '0';
    }

    static char numToChar(int num){
        return (char)(num + '0');
    }

    public static String addTwoStrings(String num1, String num2){
        int m = num1.length();
        int n = num2.length();
        int carry = 0;
        int i = m-1;
        int j = n-1;

        StringBuffer sBuffer1=new StringBuffer("");
        while (i >= 0 && j >=0) {
            int ans = charToNum(num1.charAt(i)) + charToNum(num2.charAt(j)) + carry;
            int v = ans % 10;
            sBuffer1.append(numToChar(v));
            carry = (int) (ans / 10);
            i--;
            j--;
        }
        while (i >= 0) {
            int ans = charToNum(num1.charAt(i)) + carry;
            int v = ans % 10;
            sBuffer1.append(numToChar(v));
            carry = (int) (ans / 10);
            i--;
        }
        while (j >= 0) {
            int ans = charToNum(num2.charAt(j)) + carry;
            int v = ans % 10;
            sBuffer1.append(numToChar(v));
            carry = (int) (ans / 10);
            j--;
        }
        while (carry != 0){
            int ans = carry;
            int v = ans % 10;
            sBuffer1.append(numToChar(v));
            carry = (int) (ans / 10);
        }
        sBuffer1.reverse();
        return sBuffer1.toString();
    }

    public static void main(String[] args){
        System.out.println(addTwoStrings("11", "123"));
        System.out.println(addTwoStrings("1", "9"));
    }

}