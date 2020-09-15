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

    public static int inversionCount(int arraySize, int data[])
    {
        int invCount = 0;
        for(int i = 1; i < arraySize; i++)
        {
            for (int j = i; j > 0; j--)
            {
                if(data[j] < data [j-1])
                {
                    invCount++;
                }
                else
                {
                    break;
                }
            }
        }

        return invCount;
    }

    public static void insertionsort(int arraySize, int data[])
    {
        int inversion = inversionCount(arraySize, data);
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
        
        insertionsort(arraySize, data);

        indata.close();
    }
}
