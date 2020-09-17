import java.util.Scanner;

public class Assignment3 
{
    static void printArray(int arraySize, int data[]) 
    {  
        for (int i = 0; i < arraySize; i++)
        {
            System.out.print("[" + data[i] + "], "); 
        }  
        System.out.println();
    } 

    public static void inversionCount(int arraySize, int data[]) 
    {
        int invCount = 0;
        for(int i = 0; i < arraySize; i++)
        {
            for (int j = i; j < arraySize-1; j++)
            {
                if(data[i] > data [j+1])
                {
                    System.out.println("[Index: " + i + ", Value: " + data[i] + "], " + "[Index: " + (j+1) + ", Value: " + data[j+1] + "]\n ");
                    invCount++;
                }
            }
        }

        System.out.println("Amount of inversions: " + invCount);
    }

    public static void insertionsort(int arraySize, int data[])
    {
        int temp = 0;
        int swapCounter = 0;
        System.out.println("The content of the array: ");
        printArray(arraySize, data);
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
        
        inversionCount(arraySize, data);
        insertionsort(arraySize, data);


        indata.close();
    }
}
