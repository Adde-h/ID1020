public class DepthFirstSearch
{
    private boolean[] marked;           // Creates a boolean marked array to indicate if DFS has been used on the chosen vertex
    private int[] edgeTo;               // Last vertex on known path to this vertex
    private final int s;                // Start vertex
    
    public DepthFirstSearch(Graph G, int s)
    {
        marked = new boolean[G.V()];    //Creates a boolean array with size of amount of vertices to store visited vertices
        edgeTo = new int[G.V()];        //Creates an edgeTo int Array to store edge to vertices
        this.s = s;                     //Sets the start vertex
        dfs(G, s);                      //Runs the DFS method to look for
    }
        //Iterates through all vertexes and fills the edgeTo array
        private void dfs(Graph G, int v)
        {
            marked[v] = true;           //Sets the start vertex to true
            for (int w : G.adj(v))      //Gets all the adjecent verteces to V
            if (!marked[w])             //If it is not marked as visited
            {
                edgeTo[w] = v;          //Set the vertex V as an EdgeTo vertex W
                dfs(G, w);              //Continue searching in vertex W, recursive method
            }
        }
        
        public boolean hasPathTo(int v)
        { 
            return marked[v];           //Return if the vertex in question has been visited or not during DFS
        }
        
        public Iterable<Integer> pathTo(int v)              //Loads in stack each vertex from Start s to Finish v
        {
            if (!hasPathTo(v)) return null;                 //If path does not exist, return null
            Stack<Integer> path = new Stack<Integer>();     //Create a stack called Path containing integers
            for (int x = v; x != s; x = edgeTo[x])          //For loop that begins at last vertex and iterates all the way to start vertex
            {
                path.push(x);                               //Pushing all the vertex from last vertex to start vertex to stack
            }
            path.push(s);                                   //Pushes start vertex to the stack  
            
            return path;                                    //Returns the stack
        }
}