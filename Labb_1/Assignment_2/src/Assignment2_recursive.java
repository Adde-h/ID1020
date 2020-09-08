/*
Adeel Hussain
Generated: 2020-09-02, Updated: 2020-09-02
Reverses characters order that is submitted by user
Input: Characters
*/

import java.util.Scanner;

public class Assignment2_recursive {

private static StringBuilder s = new StringBuilder();       //Global String

public static void recursive(String data, int len)
{
    
    if(0 != len)                                    //If length of string is not 0, then:
    {
        s.append(data.charAt(len-1));               //Get character from position len-1
        len--;                                      //Decrement len
        recursive(data,len);                        //Calls itself again with same data but different length
    }
    else
    {
        System.out.println(s);
    }
}

    public static void main(String[] args) 
    {
        System.out.println("Insert your characters: ");
        Scanner in = new Scanner(System.in);
        String data = in.nextLine();
        int len = data.length();

        recursive(data, len);
        in.close();
    }

}