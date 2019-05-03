
import java.util.LinkedList;
import java.util.List;

public class GraphImplementation implements Graph {

    private int[][] adjMatrix;
    private int edge;

    private GraphImplementation(int size){
        edge = size;
        adjMatrix = new int[size][size]; // creates graph

    }

    @Override
    public void addEdge(int v1, int v2) {
        adjMatrix[v1][v2] = 1; // creates an edge by changing it from 0 to 1
    }

    @Override
    public List<Integer> topologicalSort() {
        int[] incident = new int[edge];
        List<Integer> list = new LinkedList<>(); // creates a LinkedList from the List interface
        for (int i = 0; i < edge; i++) {
            for (int j = 0; j < edge; j++) {
                incident[j] += adjMatrix[i][j]; // makes the array to make it easier to search for 0s
//                System.out.println(incident[j]);
            }
        }
        int[] temparr;
        for(int a = 0; a < edge; a++)
            for (int b = 0; b < edge; b++)
                if (incident[b] == 0) // checks for 0s
                {
                    temparr = neighbors(b); // calls neighbors to return updated array after 0 is checked off
                    for (int n = 0; n < temparr.length; n++)
                        incident[temparr[n]] -= 1;
                    list.add(b); // adds b to the linked list
                    incident[b] = -1; // makes it subtract 1 so it becomes -1
                }
        return list;
    }

    @Override
    public int[] neighbors(int vertex) {
        int ver = 0;
        for (int i = 0; i < edge; i++)
            if (adjMatrix[vertex][i] > 0) // checking to see if the columns are greater than 0
                ver++; // gets the number of vertices greater than 0
        int[] neigh = new int[ver]; // creates array of the size of amount of vertices
        for (int j = 0, f = 0; j < edge; j++) {
            if (adjMatrix[vertex][j] > 0)
                neigh[f++] = j;
//                System.out.println(j);
        }
//        for(int i = 0; i < neigh.length; i++)
//            System.out.println(neigh[i]);
        return neigh;
    }
}
