/*
Adeel Hussain
Generated: 2020-09-16, Updated: 2020-09-16
A program that sorts an array using insertionsort and shows number of swaps performed, [5], [4], [3], [2], [1] -> [1], [2], [3], [4], [5] Swaps: 10
Input: Integers
Reference: Intro text v3 ID1020 Algorithms and Data structures.pdf
*/

import java.util.Scanner;

public class Assignment2 
{
    static void printArray(int arraySize, int data[]) 
    {  
        for (int i = 0; i < arraySize; i++)
        {
            System.out.print("[" + data[i] + "], "); 
        }  
        System.out.println();
    } 
    
    //Insertion sort, i iterates and increments along the array, j is used to compare left of i pointer with j-1
    public static void insertionsort(int arraySize, int data[])
    {
        int temp = 0;
        int swapCounter = 0;
        for(int i = 1; i < arraySize; i++)
        {
            for (int j = i; j > 0; j--)
            {
                if(data[j] < data [j-1])
                {
                    temp = data[j-1];
                    data[j-1] = data[j];
                    data[j] = temp;
                    swapCounter++;
                }
                else
                {
                    break;
                }
                printArray(arraySize, data);
            }
        }
                System.out.println("Amount of swaps: " + swapCounter);
    }

    public static void main(String[] args) 
    {
        System.out.println("Enter the size of the array: ");
        Scanner indata = new Scanner(System.in);

        int arraySize = indata.nextInt();
        System.out.println("Enter the " + arraySize + " numbers to be sorted");
        int[] data = new int[arraySize];
        
        for(int i = 0; i <= arraySize-1; i++)
        {
            data[i] = indata.nextInt();
        }

        System.out.println("Array to be sorted: ");
        printArray(arraySize, data);

        System.out.println("\nSorting algorithm started: ");
        
        insertionsort(arraySize,data);

        indata.close();
    }
}
