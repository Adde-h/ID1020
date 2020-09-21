/*
Adeel Hussain
Generated: 2020-09-17, Updated: 2020-09-21
A program used to calculate time complexity using mergesort and insertionsort with different arraylengths, output in milliseconds
Input: Size of array
Reference: https://algs4.cs.princeton.edu/14analysis/Mergesort.java.html, Intro text v3 ID1020 Algorithms and Data structures.pdf
*/

import java.util.Random;

public class Assignment5 
{
    public static int randomFill(int maximum) //Creates random numbers
    {
        Random random = new Random();
        int randomNumber = random.nextInt(maximum);
        return randomNumber;
    }

    public static int[] arrayCreator(int arraySize) //Creates array with chosen length and random values
    {
        int[] array = new int[arraySize];

        for(int i = 0; i < arraySize; i++)
        {
            array[i] = randomFill(arraySize);
        }

        return array;
    }

    public static int[] worstCase(int arraySize)
    {
        int[] array = new int[arraySize];
        int j = 0;
        for(int i = 0; i < arraySize-1; i++)
        {
            array[i] = arraySize - j;
            j++;
        }

        return array;
    }

    public static void insertionsort(int arraySize, int data[])
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
    }

    private static int[] merge(int[] leftArray, int[] rightArray)
    {
        //Creates an array with the combine length of incoming subarrays
        int[] sortedArray = new int[leftArray.length + rightArray.length];
        int i = 0, j = 0;
        
        //Variable k to iterate to place sorted integer from subarray to sortedarray
        //Variable i and j to iterate in each subarray to compare
        for(int k = 0; k < sortedArray.length; k++)
        {
            if(i >= leftArray.length)               //If leftarray is empty
            {
                sortedArray[k] = rightArray[j++];   //Continue to insert values from rightarray, increment j
            }
            else if(j >= rightArray.length)         //If rightarray is empty
            {
                sortedArray[k] = leftArray[i++];    //Continue to insert values from leftarray, increment i
            }
            else if(leftArray[i] <= rightArray[j])  //If integer on rightarray is greater than the integer on the leftarray
            {
                sortedArray[k] = leftArray[i++];    //insert integer from leftarray to sortedarray and increment i
            }
            else                                    //If integer on leftarray is greater than the integer on the rightarray
            {
                sortedArray[k] = rightArray[j++];   //insert integer from rightarray to sortedarray and increment j
            }
        }
        return sortedArray;
    }

    public static int[] mergesort(int[] input)
    {
        int arrLength = input.length;
        int middle = (arrLength / 2);
        if(arrLength <= 1) //Basecase if array is 1 or subarray is already fully devided
        {
            return input;
        }
        // Creates two subarrays, left and right that splits input array
        int[] leftArray = new int[arrLength / 2];
        int[] rightArray = new int[arrLength - middle];

        for(int i = 0; i < leftArray.length; i++)   //Copies first half of array
        {
            leftArray[i] = input[i];
        }

        for(int i = 0; i < rightArray.length; i++)   //Copies second half of array
        {
            rightArray[i] = input[i + middle];
        }
        return merge(mergesort(leftArray), mergesort(rightArray)); //recursive function, recalls the function
    }   


    public static void main(String[] args) 
    {
        int arraySize = 40000;
        int[] array =  arrayCreator(arraySize); //Creates array before calculating execution time of each method
        int[] arrayWorstCase = worstCase(arraySize);
        long startTime;
        long elapsedTime;

        startTime = System.nanoTime();
        mergesort(arrayWorstCase);
        elapsedTime = System.nanoTime() - startTime;
        
        System.out.println("Mergesort took " + elapsedTime/1000000 + " milliseconds to process " + arraySize + " integers in Worst Case Scenario\n");
        
        startTime = System.nanoTime();
        mergesort(array);
        elapsedTime = System.nanoTime() - startTime;
        
        System.out.println("Mergesort took " + elapsedTime/1000000 + " milliseconds to process " + arraySize + " integers\n");
       
        startTime = System.nanoTime();
        insertionsort(arraySize,arrayWorstCase);
        elapsedTime = System.nanoTime() - startTime;

        System.out.println("Insertionsort took " + elapsedTime/1000000 + " milliseconds to process " + arraySize + " integers in Worst Case Scenario\n");

        startTime = System.nanoTime();
        insertionsort(arraySize,array);
        elapsedTime = System.nanoTime() - startTime;

        System.out.println("Insertionsort took " + elapsedTime/1000000 + " milliseconds to process " + arraySize + " integers\n");

    }
}
