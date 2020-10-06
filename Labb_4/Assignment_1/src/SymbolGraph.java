import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class SymbolGraph 
{
    private BST<String, Integer> st;    //Creates a Symbol Table that is used to save strings as integers
    private String[] keys;              //A string array is created to "translate" the integer values stored in the symboltable 
    private Graph graph;                //Cointains the graph

    //Initializes a graph from a file
    public SymbolGraph(String filename) throws FileNotFoundException 
    {
        st = new BST<String, Integer>();            //Creates a symbol table with the type BST

        //Read in the txt file
        Scanner in = new Scanner(new FileReader(filename));

        //Builds the ST by reading strings to associate to the vertices
        while (in.hasNextLine()) 
        {
            String[] a = in.nextLine().split(" ");  //Read in the string line by line and split the string at each space
            for (int i = 0; i < a.length; i++)      //Iterates through string array "a"
            {
                if (!st.contains(a[i]))             //If the String doesn't exist in the symbol table
                {
                    st.put(a[i], st.size());        //Insert it with the key (String) and value (size of the symbol table). 
                }                                   //At first, symboltable is empty, value becomes 0, then gradually increases at each iteration
            }
        }

        //Creates an string array to associate index values from ST to Strings
        keys = new String[st.size()];

        for (String name : st.keys())               //Loads each key from ST
        {  
            keys[st.get(name)] = name;              //Puts the name loaded from ST in the String array position of its value 
        }                                           //(i.e Key: "ABC" Value: 2 --> keys[2] = "ABC")

        graph = new Graph(st.size());               //Builds a graph with size of ST
        in = new Scanner(new FileReader(filename)); 

        //Builds the graph by connecting first read vertex of each line
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

    public boolean contains(String s) 
    {
        return st.contains(s);
    }


    //Returns the integer value associated with the vertex in question
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

    //Returns the graph assoicated with the symbol graph
    public Graph graph() 
    {
        return graph;
    }

    //Main to test the functions
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
                int s = sg.indexOf(source);
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