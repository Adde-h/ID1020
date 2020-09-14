                                              /*
Adeel Hussain
Generated: 2020-09-03, Updated: 2020-09-04
Creates a queue that is double linked circular
Input: String
Reference: https://algs4.cs.princeton.edu/13stacks/DoublyLinkedList.java.html
*/
import java.util.InputMismatchException;
import java.util.Scanner;

public class Assignment6 {    //Queue, Generic datatype
    
    private Node top;         //Creates top of queue
    private Node bottom;      //Creates bottom of queue
    private int counter;      //Counter

    private static class Node //Datatypes for queue
    {
        private int data;
        private Node next;
        private Node previous;
    }

    public Assignment6()                        //Constructor for double linked queue, initializes two nodes, top and bottom
    {
        top = new Node();                       //Create top of queue
        bottom = new Node();                    //Create bottom of queue
        top.next = bottom;                      //Points top next "path" to bottom
        bottom.previous = top;                  //Points bottoms previous "path" to top
        top.previous = bottom;                  //Circular list
        bottom.next = top;                      //Circular list
        counter = 0;
    }

    public boolean isEmpty()                    //If counter equals 0 then return true, else false
    {  
        return counter == 0;
    }

    public void behindCheck(Node check, Node newData)   //insert node before ([5],[4],[3],[data],[1] if data == 2, and check.data == 3)
    {
        Node back = check.next;     //Labels the node before the data is equal to back
        newData.next = back;        //Routes newData next path to back
        newData.previous = check;   //Routes newData previous to check where data is equal
        check.next = newData;       //Reroutes check next from back to newData instead
        back.previous = newData;    //Reroutes back previous to newData instead of check
    }

    public void inputqueue() //Handles user input values
    {
        Scanner incommingData = new Scanner(System.in);
        int data = 0;
 
        System.out.println("Insert value you wish to queue:");
        try 
        {
            data = incommingData.nextInt(); 
        } 
        catch (InputMismatchException e)       //Catches error if user enters other values then integers
        {
            System.out.println("\nError! Only insert integers!");
            return;
        }

        enqueue(data);                        //Runs enqueue metod
    }

    public void enqueue(int data)             //Method to adding to queue
    {
        Node newData = new Node();            //Creatas a new node
        newData.data = data;                  //Saves data to new node
       
        Node check = bottom.previous;         //Labels top next as "first"
        if(check == top)                      //Exception when queue is empty, just add the node
        {
            behindCheck(check, newData);
        }
        else
        {
            while(data > check.data && check != top)   //While data is bigger then checked node and is not top of queue
            {
                check = check.previous;                //Move "pointer" check up in queue
            }
            
            behindCheck(check, newData);
        }

        System.out.println("\n" + data + " has been added to the queue");
        counter++;                             
    }

    public void dequeue()
    {
        if(!isEmpty())                              //If queue is not empty then run, else print "queue is empty!"
        {
            Node second = top.next.next;            //Labels data second in queue as "second"
            int data = top.next.data;               //Loads data from first in queue
            top.next = second;                      //Reroutes "next" path of top to second in line (now becoming first after dequeue)
            second.previous = top;
            counter--;
            
            System.out.println("The data dequeued from the list is: " + data);
        }
        else
        {
            System.out.println("The queue is empty!");
        }
    }

    static void test()
    {
        Assignment6 testqueue = new Assignment6();
        int i = 0;
        System.out.println("\nStarted Test:");

        for(i = 0; i < 6; i++)
        {
            testqueue.enqueue((int) (Math.random() * 100) + 1);
            testqueue.printqueue();
        }
        while(i > 0)
        {
            testqueue.dequeue();
            testqueue.printqueue();
            i--;
        }

        System.out.println("\n Test Finished");
    }

    public void printqueue()
    {
        Node peekdata = top;                                //Labels top as "peekdata"
        int index = 0;
        System.out.println("Content of queue:");
        if(!isEmpty())
        {
            for(int i = 0; i <= counter-1; i++)             //Iterates the "peekdata" reference until requested queue number
            {
                peekdata = peekdata.next;
                if(index >= counter-1)
                {
                    System.out.println("[" + peekdata.data + "] \n");
                }
                else
                {
                    System.out.print("[" + peekdata.data + "], ");

                }
                index++;
            }
        }
        else
        {
            System.out.println("Queue is empty!");
        }
        
    }


    public static void main(String[] args) 
    {
        boolean on = true;
        Scanner in = new Scanner(System.in);
        
        Assignment6 queue = new Assignment6();

        while(on == true)
        {
            System.out.println("\n-----------------------------------------------");
            System.out.println("What would you like to do?");
            System.out.println("1: Enqueue (Add to queue)");
            System.out.println("2: Dequeue (Load from queue)");
            System.out.println("3: Print contents of queue");
            System.out.println("4: Run Test");
            System.out.println("5: Exit");
            System.out.println("-----------------------------------------------");
            
            int command = in.nextInt();

            switch(command)
            {
                case 1:
                {
                    queue.inputqueue();
                    queue.printqueue();
                    break;
                }

                case 2:
                {
                    queue.dequeue();
                    queue.printqueue();
                    break;
                }

                case 3:
                {
                    queue.printqueue();
                    break;
                }

                case 4: 
                {
                    Assignment6.test();
                    break;
                }

                case 5:
                {
                    on = false;
                    System.exit(0);
                    in.close();
                }
                default:
                {
                    System.out.println("Choose one of the options!");
                }

            }
        }


    }

}
