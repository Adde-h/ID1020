import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/*
Adeel Hussain
Generated: 2020-09-21, Updated: 2020-09-21
A program that is used to compare execution times of quicksort and mergesort in large arrays
Input: Integers
Reference: https://algs4.cs.princeton.edu/23quicksort/Quick.java.html, Intro text v3 ID1020 Algorithms and Data structures.pdf
*/

public class HigherGrade2 
{
    static void printArray(int arraySize, int data[]) 
    {  
        for (int i = 0; i < arraySize; i++)
        {
            System.out.print("[" + data[i] + "], "); 
        }  
        System.out.println();
    } 

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

    public static void outputToText(int arraySize) //write output to text file, can also be imported to Excel
    {
        long startTime;
        long elapsedTime;

        try {
            FileWriter myWriter = new FileWriter("Algorithm output.txt");
            myWriter.write("Arraysize | Mergesort Time (ms) | Quicksort Time (ms) \n");
            for(int i = 100; i <= arraySize; i = i*10)
            {
                int[] array =  arrayCreator(i);
                int[] arrayquick = new int [i];
                for(int o = 0; o < i; o++)
                {
                    arrayquick[o] = array[o];
                }
                
                startTime = System.nanoTime();
                mergesort(array);
                elapsedTime = System.nanoTime() - startTime;

                long merge = elapsedTime/1000000;
                
                startTime = System.nanoTime();
                HigherGrade2.sort(arrayquick);
                elapsedTime = System.nanoTime() - startTime;

                long quicksort = elapsedTime / 1000000;

                myWriter.write(i + "," + merge + "," + quicksort + "\n");
            }
            
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void sort(int[] array) //calls sort method with arguments input array, left index position and right index position
    {
        sort(array,0,array.length - 1);
    }

    private static void sort(int[] array, int left, int right)
    {
        if (right <= left)          //Basecase, if rightindex is less than or equal to leftindex, return array as is (example when subarray has been fully partitioned)
        {
            return;
        }

        int j = partition(array, left, right);      //Call partition method to choose pivot element and sort around that, return pivot element index as j
        sort(array, left, j-1);                     //Divides and sorts array left of j
        sort(array, j+1, right);                    //Divides and sorts array right of j
    }

    private static int partition(int[] array, int left, int right)
    {
        int i = left;
        int j = right + 1;
        int v = array[left];            //Becomes the pivot element

        while(true)
        {
            while(array[++i] < v)       //Iterate i while left pointer is less than last element in array, ++i due to i being the pivot element. 
                                        //Stops when i pointer value is greater than v
            {
                if(i == right)          //if left pointer equals to right index value, break, meaning whole array has been iterated without finding value to be swapped
                {
                    break;
                }
            }

            while(v < array[--j])       //Iterate J while right pointer is greater than first element in array
            {
                if(j == left)           //If right pointer equals to left index value, break, meaning whole array has been iterated without finding value to be swapped
                {
                    break;
                }
            }

            if(i>= j)                   //Loop until pointers meet, meaning array has been sorted
            {
                break;
            }

            swap(array, i, j);          //Swap i (greater than v) and j (less than v)
        }
        swap(array, left, j);           //Swap pivot element with j so that elements left of v is less than v, elements right of v is greater than v

        return j;                       //Return index of j to be used to divide array
    }

    private static void swap(int[] array, int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
       outputToText(100000000);
    }
}
