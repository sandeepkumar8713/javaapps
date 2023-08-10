// Question : In this problem, a tree is an undirected graph that is connected and has no cycles.
// The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one
// additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that
// already existed. The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v]
// with u < v, that represents an undirected edge connecting nodes u and v. Return an edge that can be removed so
// that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs
// last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

class RedundantConnection {

    private int[] parent;
    
    RedundantConnection(int n){
        this.parent = new int[n];
        for (int i=0; i<n; i++){
            this.parent[i] = i;
        }
    }
    
    int findParent(int i, int[] parent){
        while (i != parent[i]){
            parent[i] = parent[parent[i]]; // Set grandparent to parent
            i = parent[i];
        }
        return i;
    }

    Boolean add(int u, int v){
        int x = findParent(u, parent);
        int y = findParent(v, parent);

        if (x != y){
            parent[y] = x;
            return true;
        }
        return false;
    }
    
    public static void main(String[] args){
        RedundantConnection redundantConnection = new RedundantConnection(4);
        System.out.println(redundantConnection.add(1,2));
        System.out.println(redundantConnection.add(1,3));
        System.out.println(redundantConnection.add(2,3));
    }
}
