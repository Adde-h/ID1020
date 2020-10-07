import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class SymbolDigraph 
{
    private BST<String, Integer> st;    //Creates a Symbol Table that is used to save strings as integers
    private String[] keys;              //A string array is created to "translate" the integer values stored in the symboltable 
    private Digraph Digraph;            //Cointains the Digraph

    //Initializes a Digraph from a file
    public SymbolDigraph(String filename) throws FileNotFoundException 
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

        Digraph = new Digraph(st.size());           //Builds a Digraph with size of ST
        in = new Scanner(new FileReader(filename)); 

        //Builds the Digraph by connecting first vertex of each line
        while (in.hasNextLine()) 
        {
            String[] a = in.nextLine().split(" ");  //Read in the string line by line and split the string at each space
            int v = st.get(a[0]);                   //Retrieve index value / integer value of String from ST
            for (int i = 1; i < a.length; i++)      //Iterates through string array "a"
            {
                int w = st.get(a[i]);               //Retrieve index value / integer value of String from ST
                Digraph.addEdge(v, w);              //Call addEdge function and connect vertices v and w
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

    //Returns the name of the integer value in question
    public String nameOf(int v) 
    {
        return keys[v];
    }

    //Returns the Digraph assoicated with the symbol Digraph
    public Digraph digraph() 
    {
        return Digraph;
    }

    //Main to test the functions
    public static void main(String[] args) throws FileNotFoundException 
    {
        String filename  = "Assignment_1/routes.txt";
        SymbolDigraph sg = new SymbolDigraph(filename);
        Digraph Digraph = sg.digraph();
        Scanner in = new Scanner (System.in);
        while (in.hasNextLine()) 
        {
            String source = in.nextLine();
            if (sg.contains(source)) 
            {
                int s = sg.indexOf(source);
                for (int v : Digraph.adj(s)) 
                {
                    System.out.println("   " + sg.nameOf(v));
                }
            }
            else 
            {
                System.out.println("input not contain '" + source + "'");
            }
        }
    }

}