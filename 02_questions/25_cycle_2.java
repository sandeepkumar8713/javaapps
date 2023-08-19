import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Question : Detect cycle, Cycle should start and finish at same nodes with all nodes covered.

class Graph{
    private int nodeCount;
    private Map<Integer, ArrayList<Integer>> adjacency_map = new HashMap<Integer, ArrayList<Integer>>();

    public Graph(int n){
        this.nodeCount = n;
        for (int i = 0; i < n; i++){
            adjacency_map.put(i, new ArrayList<Integer>());
        }
    }

    public void addEdge(int a, int b){
        if (!adjacency_map.get(a).contains(b)){
            adjacency_map.get(a).add(b);
        }
    }

    private boolean isCyclicUtils(int node, boolean[] visited, boolean[] recStack, int count){
        visited[node] = true;
        recStack[node] = true;

        for (int nei : this.adjacency_map.get(node)){
            if (visited[nei] == false){
                if (isCyclicUtils(nei, visited, recStack, count+1)){
                    return true;
                }
            }
            else if (recStack[nei] == true && count == this.nodeCount){
                return true;
            }
        }
        recStack[node] = false;
        return false;
    }

    public boolean isCyclic(){
        boolean[] visited = new boolean[this.nodeCount];
        boolean[] recStack = new boolean[this.nodeCount];

        for (int node : this.adjacency_map.keySet()){
            if (visited[node] == false){
                if (isCyclicUtils(node, visited, recStack, 1)){
                    return true;
                }
            }
        }
        return false;
    }
}

class DetectCycle_2{

    static boolean cyclePresent(int n, int[][] edges){
        Graph graph = new Graph(n);

        for (int[] edge : edges){
            graph.addEdge(edge[0], edge[1]);
        }

        return graph.isCyclic();
    }

    public static void main(String args[]){

        int n = 4;
        int[][] edges = {{0,1}, {1,0}, {2,3}, {3,3}};
        System.out.println(cyclePresent(n, edges));
    }

}


