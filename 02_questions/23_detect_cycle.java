import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// https://www.geeksforgeeks.org/detect-cycle-in-a-graph/
// Similar : https://leetcode.com/problems/course-schedule/description/
// Question : Given a directed graph, check whether the graph contains a cycle or not.
// Your function should return true if the given graph contains at least one cycle,
// else return false. For example, the following graph contains three
// cycles 0->2->0, 0->1->2->0 and 3->3, so your function must return true.

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

    private boolean isCyclicUtils(int node, boolean[] visited, boolean[] recStack){
        visited[node] = true;
        recStack[node] = true;

        for (int nei : this.adjacency_map.get(node)){
            if (visited[nei] == false){
                if (isCyclicUtils(nei, visited, recStack)){
                    return true;
                }
            }
            else if (recStack[nei] == true){
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
                if (isCyclicUtils(node, visited, recStack)){
                    return true;
                }
            }
        }
        return false;
    }
}

class DetectCycle{

    static boolean cyclePresent(int n, int[][] edges){
        Graph graph = new Graph(n);

        for (int[] edge : edges){
            graph.addEdge(edge[0], edge[1]);
        }

        return graph.isCyclic();
    }

    public static void main(String args[]){
        int n = 5;
        int[][] edges = {{0,1}, {0,2}, {1,2}, {2, 0}, {2, 3}, {3, 3}};
        System.out.println(cyclePresent(n, edges));
    }

}

