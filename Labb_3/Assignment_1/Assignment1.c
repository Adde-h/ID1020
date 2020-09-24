/*
Adeel Hussain
Generated: 2020-09-22, Updated: 2020-09-2
A program that filters a string of text and removes all non alphabetic characters

Data before ordered: 


Data now ordered: 


Input: Strings
Reference: 
*/

#include <stdio.h>

void checkAlphabet(char c)
{
    if(isalpha(c))
    {
        printf("%c", c ); 
    }
    else
    {
        printf(" ");
    }
}

int main(int argc, char const *argv[])
{
    char c;
    while(((c = getchar()) != EOF))  //Load 1 char at a time
    {
        //printf("input: %c\n", c); 
        checkAlphabet(c);   
    }

    return 0;
}
