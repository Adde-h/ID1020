public class Digraph 
{
    private final int V;            // Number of Vertices (Hörn)
    private int E;                  // Number of Edges  (Kanter)
    private Bag<Integer>[] adj;     // A bag to contain


    //Initializes an empty Digraph with V amount of Vertices
    public Digraph(int V) 
    {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;                         //Sets the vertices amount to inserted amount
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];  //Creates a bag to contain adjecent nodes called adj
        for (int v = 0; v < V; v++)         //For loop that iterates through all the vertices and 
        {
            adj[v] = new Bag<Integer>();    //Creates a bag array for each vertex
        }
    }

    //Returns the number of Vertices in the Digraph
    public int V() {
        return V;
    }

    //Returns the number of Edges in the Digraph
    public int E() {
        return E;
    }

    //Adds the vertex edges to the adj bag array
    public void addEdge(int v, int w) 
    {
        adj[v].add(w);  //Adds node W to adjecent bag of V
        E++;            //Increments amount of edges
    }

    //Returns the adjecent bag to the chosen vertex
    public Iterable<Integer> adj(int v) 
    {
        return adj[v];
    }

}