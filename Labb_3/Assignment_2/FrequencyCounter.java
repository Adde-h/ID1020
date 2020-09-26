/*
Adeel Hussain
Generated: 2020-09-26, Updated: 2020-09-27
A program that uses Symbol Table to count frequency of words from a txt file

Input: txt file
Reference: https://algs4.cs.princeton.edu/31elementary/FrequencyCounter.java.html
*/
import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.util.Scanner;

public class FrequencyCounter 
{
    public static void main(String[] args) throws FileNotFoundException
    {
    
    int distinct = 0, words = 0;
    int maxWords = 100;
    int minlength = 1;
    long starttime, endtime, time;    

    BST<String, Integer> BST = new BST<String,Integer>();
    //BinarySearchST<String, Integer> binarySearchST = new BinarySearchST<String, Integer>();

    //Read in the txt file
    File myText = new File("Assignment_2//filteredText.txt");
    Scanner ScanBST = new Scanner(myText);
    //Scanner ScanBinarySearchST = new Scanner(myText);
    
    starttime = System.nanoTime();
    while(ScanBST.hasNext() && words < maxWords)
    {
        String key = ScanBST.next();

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
            distinct++;
        }
    }

    int max = 0;
    String maxstring = "";
    for(int i = 0; i < words; i++)
    {
        if(max < BST.get()
    }

    endtime = System.nanoTime();
    time = (endtime - starttime) / 1000000;

    System.out.println("BST operation took: " + time + " milliseconds to execute");

    ScanBST.close();
}



}
