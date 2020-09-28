/*
Adeel Hussain
Generated: 2020-09-28, Updated: 2020-09-28
an "index"-program which allows the user to ask questions "on which positions 
in the text (i.e. the number of characters from the beginning) you find the word X". 
The program lists the position of all occurrences of X as answer to a query. 

Input: txt file
Reference: https://algs4.cs.princeton.edu/31elementary/BinarySearchST.java.html
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment4 {

    static int characters = 0;                                  //Counts characters

    public static String StringCreator(Scanner in)
    {
        StringBuilder sb = new StringBuilder();

        while(true)
        {
            String onechar = in.next().toLowerCase();           //read one char at a time, convert to lowercase
            if(onechar.equals(" "))                             //If space is found
            {
                characters++;                                   //Increment characters, start at 0 (beginning)
                break;                                          //End of word! Break out of loop
            }
            else
            {
                sb.append(onechar);                             //Append one char at a time until space, word is now created
            }
        }

        return sb.toString();                                   //Return stringbuilder

    }

    public static BinarySearchST<String, ArrayList<Integer>> BSSTcreator(File inputFile) throws FileNotFoundException
    {
        BinarySearchST<String, ArrayList<Integer>> BSST = new BinarySearchST<String, ArrayList<Integer>>(10000);

        Scanner scanBSST = new Scanner(inputFile);

        while(scanBSST.hasNext())                       //Inserts words to the lists aslong as there are words
        {
            scanBSST.useDelimiter("");                  //Makes sure scanner reads in 1 char at a time
           
            String word = StringCreator(scanBSST);      //Gets words

            if(!BSST.contains(word))                    //If the word does not exist in the Symbol Table, 
            {
                BSST.put(word, new ArrayList<Integer>());   //Put word and create an new ArrayList
            }

            BSST.get(word).add(characters);             //Gets the ArrayList and adds character count for the word
            characters = characters + word.length();    //Increase the character count with the word length
        }
       
        
        scanBSST.close();
        return BSST;
    }

    public static String search() {
        Scanner userin = new Scanner(System.in);
        System.out.println("What word are you searching for?");
        String searchTarget = (userin.next()).toLowerCase();

        return searchTarget;

    }

    public static void main(String[] args) throws FileNotFoundException {

        // Read in the txt file
        File myText = new File("filteredText.txt");

        boolean on = true;
        Scanner in = new Scanner(System.in);

        while (on == true) {
            System.out.println("\n-----------------------------------------------");
            System.out.println("What would you like to do?");
            System.out.println("1: Search for word");
            System.out.println("2: Exit");
            System.out.println("-----------------------------------------------");

            int command = in.nextInt();

            switch (command) {
                case 1: {
                    String searchTarget = search();

                    BinarySearchST<String, ArrayList<Integer>> BSST = BSSTcreator(myText);

                    System.out.println("\nThe word \"" + searchTarget + "\" exists in these positions:");
                    for(int i = 0; i < (BSST.get(searchTarget)).size(); i++)
                    {
                        System.out.print("[" + BSST.get(searchTarget).get(i) + "], ");
                    }
                    break;
                }

                case 2:
                {
                    on = false;
                    System.exit(0);
                }
                default: 
                {
                    System.out.println("Choose one of the options!");
                }

            }
        }
        
        in.close();

    }
    
}
