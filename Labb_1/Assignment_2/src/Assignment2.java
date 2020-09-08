/*
Adeel Hussain
Generated: 2020-08-27, Updated: 2020-09-01
Reverses characters order that is submitted by user
Input: Characters
Reference: https://algs4.cs.princeton.edu/13stacks/Stack.java.html
*/

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Assignment2<Data> {                //Generic data type
    
    private Node<Data> top;                     //Top of stack
    private static int stackcounter;             //Size of Stack
    
    // Stack implementation
    private static class Node<Data>             //Node with datatype "<Data>"
    {
        private Data data;                      //Abstract Data type "Data"
        private Node<Data> next;                //Pointer to next node
    }
    
    public Assignment2()                        //Constructor, initializes an empty stack
    {
        top = null;      
        stackcounter = 0;
    }

    public boolean isEmpty()                    //Return true if stack is empty, false otherwise
    {
        return top == null;
    }

    public int size()                           //Returns size of stack
    {
        return stackcounter;
    }
   
    public void push(Data data)
    {
        Node<Data> oldfirst = top;              // points "top" to top before node creation
        top = new Node<Data>();                 //Changes reference "top" to new node
        top.data = data;                        // stores incoming data to top of stack
        top.next = oldfirst;                    //sets next reference to previous top
        stackcounter++;                         //Increments stackcount
    }

    public Data pop()
    {
        if(isEmpty()) throw new NoSuchElementException("Stack is empty");
        Data data = top.data;                   //Saves data from top of stack to return it
        top = top.next;                         //Sets the new top to next order in stack
        stackcounter--;                         //Deceremts the stackcount
        return data;                            //Returns the data from top of stack
    }

    public Data peek()
    {
        if(isEmpty()) throw new NoSuchElementException("Stack is empty");
        return top.data;                        //Returns data from top of stack
    }

    public static void test() 
    {
        Assignment2<String> stack = new Assignment2<String>();   //Creates stack with datatype String

        stack.push("f");
        stack.push("i");
        stack.push("r");
        stack.push("s");
        stack.push("t");

        stack.push("1");
        stack.push("23");
        
    }


    public static void main(final String[] args) 
    {
        Scanner in = new Scanner(System.in);

        System.out.println("Insert your characters:");
        String read = in.nextLine();
        int length = read.length();
        
        Assignment2<String> stack = new Assignment2<String>();   //Creates stack with datatype String

        for(int i = 0; i < length; i++)                             
        {
            stack.push(read.substring(i, i+1));                  //Pushes 1 letter at a time from string
        }
                   
            for(int n = stack.size(); 0 < n; n-- )
            {
                System.out.print(stack.pop());                   //Pops 1 letter at a time from string, LIFO order
            }

        in.close();
    }

}