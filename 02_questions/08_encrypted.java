// https://leetcode.com/discuss/interview-question/3597465/Wayfair-or-SDE-2-(L2-4)-or-Bengaluru-or-OA
// There is a list of encrypted files of size n, each with a different value, and a
// list of n binary values. A value of 1 represents a file that has been
// decrypted, and 0 represents an encrypted file that is not yet decrypted.
// The value sum of the encrypted files is the sum of all values of the files
// where the corresponding value in the binary list is 1. In a single operation,
// a group of up to k encrypted files can be decrypted simultaneously.
// 
// The values of the encrypted files and the binary list are given, along with
// the maximum number of encrypted files that can be decrypted in a single
// operation. Find the maximum possible value sum of the decrypted files.

class EncryptedFiles {
    
    public static int findMaxValue(int[] encrypted_files, int[] binary, int k){

        int value_sum = 0;
        int i = 0;
        int max_remaining_value = 0;
        while (i < encrypted_files.length){
            if (binary[i] == 1){
                value_sum += encrypted_files[i];
            }
            i++;
        }

        int left = 0;
        int right = k-1;
        i = 0;
        int remaining_value = value_sum;
        while (i <= right){
            if (binary[i] == 0){
                remaining_value += encrypted_files[i];
            }
            i++;
        }

        while (right < (encrypted_files.length - 1)){
            if (binary[left] == 0){
                remaining_value -= encrypted_files[left];
            }
            left++;
            right++;
            if (binary[right] == 0){
                remaining_value += encrypted_files[right];
            }
            max_remaining_value = Math.max(max_remaining_value, remaining_value);
        }

        return max_remaining_value;
    }

    public static void main(String args[]){
        int[] encrypted_files = {1,3,5,2,5,4};
        int[] binary = {1,1,0,1,0,0};
        int k = 3;
        System.out.println(findMaxValue(encrypted_files, binary, k));

        int[] encrypted_files_1 = {7, 4, 3, 5};
        int[] binary_1 = {1, 0, 0, 0};
        int k_1 = 2;
        System.out.println(findMaxValue(encrypted_files_1, binary_1, k_1));
    }
}
