/*
Adeel Hussain
Generated: 2020-08-25, Updated: 2020-08-27
Reverses characters order that is submitted by user
Input: Characters
*/

#include <stdio.h>

void recursive()
{     
    char text;

    //Gets character until nextline is read
    if( (text = getchar() ) != '\n')
    {
        recursive();        //Calls itself, and runs inside itself, 
                            //"Inception" like (program in program) and continues until last char
    }
    
    //Prints out char, starting from inside out in the "inception" code, thus reversing the order
    putchar(text);
}

void iterative()
{
    int max = 10;
    char str[max], temp;
    int i, j, count;
    i = j = count = 0;

    //Counts how many characters is submitted by user
        while (((str[i] = getchar()) != '\n' && i < max ))
        {
            count++;
            i++;
        }
    
    //Prints out the reverse order
    for(j = count-1; j >= 0; j--)
    {
        printf("%c", str[j]);
    }
    
}

static void test()
{
    char testarray[] = {'t', 'e', 's', 't', 'i', 'n', 'g'};
}

int main()
{
    printf("Insert your characters: ");
    //recursive(); 
    //test();
    iterative();
    return 0;
}
