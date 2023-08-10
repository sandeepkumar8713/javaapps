import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://www.geeksforgeeks.org/auto-complete-feature-using-trie/
// Question : We are given a Trie with a set of strings stored in it. Now the user types in a prefix of his search
// query, we need to give him all recommendations to auto-complete his query based on the strings stored in the Trie.
// We assume that the Trie stores past searches by the users.
// 
// For example if the Trie store {"abc", "abcd", "aa", "abbbaba"} and the User types in "ab" then he must be shown
// {"abc", "abcd", "abbbaba"}.

class Node{
    private String word;
    private Map<Character, Node> myMap;

    public String getWord(){
        return this.word;
    }

    public void setWord(String word){
        this.word = word;
    }


    public Map<Character, Node> getMyMap(){
        return this.myMap;
    }

    Node(){
        this.word = null;
        this.myMap = new HashMap<Character, Node>();
    }
}


class AutoComplete {

    private Node trie;

    AutoComplete(String[] store){
        int i = 0, n;
        Node temp;
        this.trie = new Node();
        Map<Character, Node> tempMap;
        for (String item : store){
            temp = this.trie;
            n = item.length();
            i = 0;
            while (i < n){
                char ch = item.charAt(i);
                tempMap = temp.getMyMap();
                if (!tempMap.containsKey(ch)){
                    tempMap.put(ch, new Node());
                }
                temp = tempMap.get(ch);
                i++;
            }
            temp.setWord(item);
        }
    }

    public static void getAllWords(Node node, List<String> result){
        if (node.getWord() != null){
            result.add(node.getWord());
        }
        Map<Character, Node> tempMap = node.getMyMap();
        for (Character key : tempMap.keySet()) {
            getAllWords(tempMap.get(key), result);
        }
    }

    public List<String> getWords(String inputStr){
        List<String> result = new ArrayList<String>();
        Node temp = this.trie;
        int n = inputStr.length();
        int i = 0;
        Map<Character, Node> tempMap;
        while (i < n && temp != null){
            char ch = inputStr.charAt(i);
            tempMap = temp.getMyMap();
            if (tempMap.containsKey(ch)){
                temp = tempMap.get(ch);
            } else{
                return null;
            }
            i++;
        }

        getAllWords(temp, result);
        return result;
    }

    public static void main(String[] args){
        String[] store = {"abc", "abcd", "aa", "abbbaba"};
        AutoComplete autoComplete = new AutoComplete(store);
        List<String> result = autoComplete.getWords("ab");
        System.out.println(result);

    }
}
