/*
Adeel Hussain
Generated: 2020-10-06, Updated: 2020-10-07
Dependencies: SymbolDigraph.java, Digraph.java, DepthFirstSearch.java
A program that can answer if there is a path between any to vertices using a Directed Graph
Input: Graph & Vertex Source
Reference: https://algs4.cs.princeton.edu/41graph/
*/

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment3 
{
    public static void main(String[] args) throws FileNotFoundException 
    {
        //Construct a symbolDigraph using inserted filename
        SymbolDigraph sg = new SymbolDigraph("TheDatabase.txt");
        Scanner in = new Scanner(System.in);
        
        System.out.println("Enter State to start from: ");
        String startState = in.nextLine().toUpperCase();
        
        int startIndex = sg.indexOf(startState);
        System.out.println("Enter state to end in: ");
        String endState = in.nextLine().toUpperCase();
        
        int endIndex = sg.indexOf(endState);

        //Construct the graph
        Digraph digraph = sg.digraph();
        
        //Start the DFS search and map all vertices and edges
        DepthFirstDirectedSearch dfs = new DepthFirstDirectedSearch(digraph, startIndex);
        
        if(!dfs.hasPathTo(endIndex))
        {
            System.out.println("There is no path between the chosen states");
        }
        else
        {
            for(int f : dfs.pathTo(endIndex)) //Retrieve stack of vertices from start vertex to end vertex
            {
                if(f == endIndex)
                {
                    System.out.print(sg.nameOf(f));
                }
                else
                {
                    System.out.print(sg.nameOf(f) + " ==> ");
                }
            }
        }
        in.close();
    }
}
