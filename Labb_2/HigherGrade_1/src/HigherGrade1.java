/*
Adeel Hussain
Generated: 2020-09-21, Updated: 2020-09-21
A program that sorts an array using insertionsort and a method to reverse it
Input: Integers
Reference: Intro text v3 ID1020 Algorithms and Data structures.pdf
*/

import java.util.Scanner;

public class HigherGrade1 
{
    //To print array
    static void printArray(int arraySize, int data[]) 
    {  
        for (int i = 0; i < arraySize; i++)
        {
            System.out.print("[" + data[i] + "], "); 
        }  
        System.out.println();
    } 

    public static void insertionsort(int arraySize, int data[]) 
    {
        for (int i = 0; i < arraySize; i++) //Interates through all numbers in array and reverses sign
        {
            data[i] = data[i] * -1;
        }

        data = insertionsortDecending(arraySize, data);

        for (int i = 0; i < arraySize; i++) //Interates through all numbers in array again and re reverse the sign
        {
            data[i] = data[i] * -1;
            
        }
        printArray(arraySize, data);
        
    }

    //Insertion sort, i iterates and increments along the array, j is used to compare left of i pointer with j-1
    public static int[] insertionsortDecending(int arraySize, int data[])
    {
        int temp = 0;
        for(int i = 1; i < arraySize; i++)
        {
            for (int j = i; j > 0; j--)
            {
                if(data[j] < data [j-1])
                {
                    temp = data[j-1];
                    data[j-1] = data[j];
                    data[j] = temp;
                }
                else
                {
                    break;
                }
            }
        }
        return data;
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

        System.out.println("\nInsertionsorted in decending order: ");

        insertionsort(arraySize,data);

        indata.close();
    }
}
