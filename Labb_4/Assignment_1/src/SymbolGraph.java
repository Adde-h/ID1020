import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class SymbolGraph 
{
    private BST<String, Integer> st; // string -> index
    private String[] keys; // index -> string
    private Graph graph; // the underlying graph

    /**
     * Initializes a graph from a file using the specified delimiter. Each line in
     * the file contains the name of a vertex, followed by a list of the names of
     * the vertices adjacent to that vertex, separated by the delimiter.
     * 
     * @param filename  the name of the file
     * @param delimiter the delimiter between fields
     * @throws FileNotFoundException
     */
    public SymbolGraph(String filename) throws FileNotFoundException 
    {
        st = new BST<String, Integer>();

        //Read in the txt file
        Scanner in = new Scanner(new FileReader(filename));

        // First pass builds the index by reading strings to associate
        // distinct strings with an index
        while (in.hasNextLine()) 
        {
            String[] a = in.nextLine().split(" ");
            for (int i = 0; i < a.length; i++) 
            {
                if (!st.contains(a[i]))
                {
                    st.put(a[i], st.size());
                }
            }
        }

        // inverted index to get string keys in an array
        keys = new String[st.size()];

        for (String name : st.keys()) 
        {
            keys[st.get(name)] = name;
        }

       // second pass builds the graph by connecting first vertex on each
        // line to all others
        graph = new Graph(st.size());

        in = new Scanner(new FileReader(filename));        
        
        while (in.hasNextLine()) 
        {
            String[] a = in.nextLine().split(" ");
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) 
            {
                int w = st.get(a[i]);
                graph.addEdge(v, w);
            }
        }
    }

    /**
     * Does the graph contain the vertex named {@code s}?
     * @param s the name of a vertex
     * @return {@code true} if {@code s} is the name of a vertex, and {@code false} otherwise
     */
    public boolean contains(String s) 
    {
        return st.contains(s);
    }

    /**
     * Returns the integer associated with the vertex named {@code s}.
     * @param s the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named {@code s}
     * @deprecated Replaced by {@link #indexOf(String)}.
     */
    @Deprecated
    public int index(String s) 
    {
        return st.get(s);
    }


    /**
     * Returns the integer associated with the vertex named {@code s}.
     * @param s the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named {@code s}
     */
    public int indexOf(String s) 
    {
        return st.get(s);
    }

    /**
     * Returns the name of the vertex associated with the integer {@code v}.
     * @param  v the integer corresponding to a vertex (between 0 and <em>V</em> - 1) 
     * @return the name of the vertex associated with the integer {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @deprecated Replaced by {@link #nameOf(int)}.
     */
    @Deprecated
    public String name(int v) 
    {
        return keys[v];
    }

    /**
     * Returns the name of the vertex associated with the integer {@code v}.
     * @param  v the integer corresponding to a vertex (between 0 and <em>V</em> - 1) 
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @return the name of the vertex associated with the integer {@code v}
     */
    public String nameOf(int v) 
    {

        return keys[v];
    }

    /**
     * Returns the graph assoicated with the symbol graph. It is the client's responsibility
     * not to mutate the graph.
     * @return the graph associated with the symbol graph
     * @deprecated Replaced by {@link #graph()}.
     */
    @Deprecated
    public Graph G() 
    {
        return graph;
    }

    /**
     * Returns the graph assoicated with the symbol graph. It is the client's responsibility
     * not to mutate the graph.
     * @return the graph associated with the symbol graph
     */
    public Graph graph() 
    {
        return graph;
    }

    /**
     * Unit tests the {@code SymbolGraph} data type.
     *
     * @param args the command-line arguments
     * @throws FileNotFoundException
     */

    public static void main(String[] args) throws FileNotFoundException 
    {
        String filename  = "Assignment_1/routes.txt";
        SymbolGraph sg = new SymbolGraph(filename);
        Graph graph = sg.graph();
        Scanner in = new Scanner (System.in);
        while (in.hasNextLine()) 
        {
            String source = in.nextLine();
            if (sg.contains(source)) 
            {
                int s = sg.index(source);
                for (int v : graph.adj(s)) 
                {
                    System.out.println("   " + sg.name(v));
                }
            }
            else 
            {
                System.out.println("input not contain '" + source + "'");
            }
        }
    }

}