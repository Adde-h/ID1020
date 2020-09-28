import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment3
{
    public static void main(String[] args) throws FileNotFoundException 
    {
        int words = 0;
        int sameword = 0;
        int distinct = 0;
        int maxWords = 5000;
        int minlength = 1;
        
        BST<Integer, String> BST = new BST<Integer,String>(); //Key is now the hashcode (int) and value is now the word (String)

        //Read in the txt file
        File myText = new File("98-0.txt");
        Scanner ScanBST = new Scanner(myText);
        

        /* BST Execution */
        while((ScanBST.hasNext()) && (words < maxWords))
        {
            String word = (ScanBST.next()).toLowerCase();
            int key = word.hashCode();

            if(word.length() < minlength)
            {
                continue;
            }
            words++;
            if(BST.contains(key))       //If inserted key exists
            {
                if((BST.get(key).equals(word)))
                {
                   /* System.out.println("----------------------------------------------------");
                    System.out.println("Word with the same hash found!");
                    System.out.println("\"" + word + "\" has the hash " + key);*/
                    sameword++;

                }
                else
                {
                    System.out.println("----------------------------------------------------");
                    System.out.println("Collision Warning!");
                    System.out.println("\"" + word + "\" has collided with the hash " + key);
                }
            }
            else
            {
                BST.put(key, word);
                distinct++;
                
            }
        }
        System.out.println(sameword + " words that are the exact same has been found!");
        System.out.println(distinct + " words that are distinct has been found!");


        ScanBST.close();

    }
}
