import java.util.Scanner;

public class Assignment1 
{

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
                printArray(arraySize, data);
            }
        }
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
