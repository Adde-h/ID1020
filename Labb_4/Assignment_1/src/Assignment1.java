import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment1 
{
    public static void main(String[] args) throws FileNotFoundException 
    {
        SymbolGraph sg = new SymbolGraph("Assignment_1/TheDatabase.txt");
        Scanner in = new Scanner(System.in);
        
        System.out.println("Enter State to start from: ");
        String startState = in.nextLine().toUpperCase();
        
        int startIndex = sg.indexOf(startState);
        System.out.println("Enter state to end in: ");
        String endState = in.nextLine().toUpperCase();
        
        int endIndex = sg.indexOf(endState);

        Graph graph = sg.graph();
        
        DepthFirstSearch dfs = new DepthFirstSearch(graph, startIndex);
        
        if(!dfs.hasPathTo(startIndex))
        {
            System.out.println("There is no path between the chosen states");
        }
        else
        {
            for(int f : dfs.pathTo(endIndex))
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
