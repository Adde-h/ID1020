/*
Adeel Hussain
Generated: 2020-09-17, Updated: 2020-09-19
A program that orders an array after negative and positive number (not sorting!)

Data before ordered: 
[7], [2], [-4], [-8], [7], [0], [-3], [-12], [2]

Data now ordered: 
[-4], [-8], [-3], [-12], [7], [0], [7], [2], [2]

Input: Integers
Reference: Intro text v3 ID1020 Algorithms and Data structures.pdf
*/


#include <stdio.h>

void insertionOrder(int size, int array[])
{
    printf("%s", "\nData before ordered: \n");
    printArray(size,array);

    //Insertion sort variant, i iterates and increments along the array to find a positive number, j iterates to find a negative number, than swap those
    int temp;
    int i,j = 0;
    for(i; i < size-1; i++)
    {
        for(j = i+1; j <= size-1; j++)
        {
            if(array[i] > 0 && array[j] < 0)
            {
                temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }
    }
    printf("%s", "Data now ordered: \n");
    printArray(size, array);

}

void printArray(int arraySize, int data[]) 
{  
    int i;
    for (i = 0; i < arraySize; i++)
    {
        printf("[%d], ", data[i]); 
    }  
    printf("%s", "\n\n");

} 

int main()
{
    int array[] = {7,2,-4,-8,7,0,-3,-12,2};
    int size = sizeof(array) / sizeof(array[0]);

    insertionOrder(size,array);
    return 0;
}