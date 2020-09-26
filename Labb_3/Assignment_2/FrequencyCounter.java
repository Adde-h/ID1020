/*
Adeel Hussain
Generated: 2020-09-26, Updated: 2020-09-27
A program that uses Symbol Table to count frequency of words from a txt file

Input: txt file
Reference: https://algs4.cs.princeton.edu/31elementary/FrequencyCounter.java.html
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FrequencyCounter 
{
    public static void main(String[] args) throws FileNotFoundException
    {
        int words = 0;
        int maxWords = 125000;
        int minlength = 1;
        long starttime, endtime, time;    

        BST<String, Integer> BST = new BST<String,Integer>();
        BinarySearchST<String, Integer> binarySearchST = new BinarySearchST<String, Integer>();

        //Read in the txt file
        File myText = new File("Assignment_2//filteredText.txt");
        Scanner ScanBST = new Scanner(myText);
        Scanner ScanBinarySearchST = new Scanner(myText);
        

        /* BST Execution */
        starttime = System.nanoTime();
        while((ScanBST.hasNext()) && (words < maxWords))
        {
            String key = (ScanBST.next()).toLowerCase();

            if(key.length() < minlength)
            {
                continue;
            }
            words++;

            if(BST.contains(key))
            {
                BST.put(key, BST.get(key) + 1);
            }
            else
            {
                BST.put(key,1);
            }
        }

        String max = "";
        BST.put(max,0);
        for(String word: BST.keys())
        {
            if(BST.get(word) > BST.get(max))
            {
                max = word;
            }
        }

        endtime = System.nanoTime();
        time = (endtime - starttime) / 1000000;
        System.out.println("----------------------------------------------------");
        System.out.println("BST operation took: " + time + " milliseconds to execute");
        System.out.println("The most frequent word was \"" + max + "\" that occured " + BST.get(max) + " times");
        
        /*BinarySearchST Execution */
        words = 0;
        starttime = System.nanoTime();
        while((ScanBinarySearchST.hasNext()) && (words < maxWords))
        {
            String key = (ScanBinarySearchST.next()).toLowerCase();

            if(key.length() < minlength)
            {
                continue;
            }
            words++;

            if(binarySearchST.contains(key))
            {
                binarySearchST.put(key, binarySearchST.get(key) + 1);
            }
            else
            {
                binarySearchST.put(key,1);
            }
        }

        String max2 = "";
        binarySearchST.put(max2,0);
        for(String word2: binarySearchST.keys())
        {
            if(binarySearchST.get(word2) > binarySearchST.get(max2))
            {
                max2 = word2;
            }
        }

        endtime = System.nanoTime();
        time = (endtime - starttime) / 1000000;

        System.out.println("----------------------------------------------------");
        System.out.println("BinarySearchST operation took: " + time + " milliseconds to execute");
        System.out.println("The most frequent word was \"" + max2 + "\" that occured " + binarySearchST.get(max2) + " times");
        
        
        ScanBST.close();
        ScanBinarySearchST.close();

    }



}
