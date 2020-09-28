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

void checkAlphabet(FILE *ifp)
{
    FILE *ofp;
    char c;
    ofp = fopen("filteredText.txt", "w");
    
    if (ofp == NULL) 
    {
        fprintf(stderr, "Can't open output file\n");
        exit(1);
    }

    while ((c = fgetc(ifp)) != EOF)
    {
        if(isalpha(c))
        {
            fputc(c, ofp); 
        }
        else
        {
            fputc(32, ofp);
        }
        
    }
    
    fclose(ofp);
}

int main(int argc, char const *argv[])
{
    FILE *ifp;

    ifp = fopen("Gutenberg.txt", "r");

    if (ifp == NULL) 
    {
        fprintf(stderr, "Can't open input file!\n");
        exit(1);
    }

    checkAlphabet(ifp);
    
    fclose(ifp);
    return 0;
}
