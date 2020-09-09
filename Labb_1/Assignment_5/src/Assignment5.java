/*
Adeel Hussain
Generated: 2020-09-03, Updated: 2020-09-04
Creates a queue that is double linked circular
Input: String
Reference: https://algs4.cs.princeton.edu/13stacks/DoublyLinkedList.java.html
*/
import java.util.Scanner;

public class Assignment5<Data> {    //Queue, Generic datatype
    
    private Node<Data> top;         //Creates top of queue
    private Node<Data> bottom;      //Creates bottom of queue
    private int counter;            //Counter

    private static class Node<Data> //Datatypes for queue
    {
        private Data data;
        private Node<Data> next;
        private Node<Data> previous;
    }

    public Assignment5()                        //Constructor for double linked queue, initializes two nodes, top and bottom
    {
        top = new Node<Data>();                 //Create top of queue
        bottom = new Node<Data>();              //Create bottom of queue
        top.next = bottom;                      //Points top next "path" to bottom
        top.previous = bottom;                  //Creates circular list
        bottom.previous = top;                  //Points bottoms previous "path" to top
        bottom.next = top;                      //Creates circular list
        counter = 0;
    }

    public boolean isEmpty()                    //If counter equals 0 then return true, else false
    {  
        return counter == 0;
    }

    public void enqueue(Data data)              //Method to adding to queue
    {
        Node<Data> last = bottom.previous;      //Labels bottom previous as "last"
        Node<Data> newData = new Node<Data>();  //Creatas a new node
        newData.data = data;                    //Saves data to new node
        newData.next = bottom;                  //Points new node to bottom (end of the queue)
        newData.previous = last;                //Points the new node "previous" to the now second to last of queue
        last.next = newData;                    //Points second to last "next" to the new data (last data)
        bottom.previous = newData;              //Points the bottom "previous" to the new data (last data)
        counter++;                         //Increment counter
    }

    public void dequeue(int location)
    {
        if(!isEmpty())                 //If queue is not empty then run, else print "queue is empty!"
        {
            Node<Data> main = bottom;      //Labels data second in queue as "second"

            if(location <= counter)   //If selected location exists in stack then proceed
            {
                for(int i = 1; i <= location; i++)  //Iterate to the selected location
                {
                    main = main.previous;
                }
                
                Data data = main.data;                  //Loads data from first in queue
                Node<Data> front = main.previous;       //Labels data infront of the selected location
                Node<Data> back = main.next;            //Lavbels data behind of the selected location

                front.next = back;                      //Reroutes around the selected location
                back.previous = front;                  //Reroutes around the selected location

                counter--;
                
                System.out.println("The data dequeued from the list is: " + data);
            }
            else
            {
                System.out.println("No element in " + location + " location");
            }
    
        }
        else
        {
            System.out.println("The queue is empty!");
        }
    }
    static void test()
    {
        Assignment5<String> testqueue = new Assignment5<String>();

        System.out.println("\nStarted Test:");
        testqueue.enqueue("First");
        testqueue.printqueue();
        
        testqueue.enqueue("Second");
        testqueue.printqueue();
        
        testqueue.enqueue("Third");
        testqueue.printqueue();
        
        testqueue.enqueue("4th");
        testqueue.printqueue();
        
        testqueue.enqueue("5");
        testqueue.printqueue();

        testqueue.dequeue(1);
        testqueue.printqueue();

        testqueue.dequeue(3);
        testqueue.printqueue();

        testqueue.dequeue(111);     //Testing out of bound number
        testqueue.printqueue();

        testqueue.dequeue(1);
        testqueue.printqueue();

        testqueue.dequeue(1);
        testqueue.printqueue();

        testqueue.dequeue(1);
        testqueue.printqueue();

        testqueue.dequeue(1);
        testqueue.printqueue();

        testqueue.enqueue("First");
        testqueue.printqueue();
        testqueue.enqueue("Second");
        testqueue.printqueue();
        testqueue.enqueue("Third");
        testqueue.printqueue();

        testqueue.dequeue(1);     
        testqueue.printqueue();
        testqueue.dequeue(2);
        testqueue.printqueue();
        testqueue.dequeue(1);
        testqueue.printqueue();
        System.out.println("\n Test Finished");

    }


    public void printqueue()
    {
        int index = 0;
        Node<Data> peekdata = top;                          //Labels top as "peekdata"
        System.out.println("\nContent of queue:");
        if(!isEmpty())
        {
            for(int i = 0; i <= counter-1; i++)         //Iterates the "peekdata" reference until requested queue number
            {
                peekdata = peekdata.next;

                if(index >= counter-1)
                {
                    System.out.print("[" + peekdata.data + "] ");
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
        System.out.println("");
        
    }


    public static void main(String[] args) 
    {
        boolean on = true;
        Scanner in = new Scanner(System.in);
        Scanner data = new Scanner(System.in);
        
        Assignment5<String> queue = new Assignment5<String>();

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
            int location = 0;

            switch(command)
            {
                case 1:
                {
                    System.out.println("What would you like to add?");
                    queue.enqueue(data.next());
                    break;
                }

                case 2:
                {
                    System.out.println("Where would you like to remove from the queue? (1 being the last data)");
                    location = data.nextInt();
                    queue.dequeue(location);
                    break;
                }

                case 3:
                {
                    queue.printqueue();
                    break;
                }

                case 4: 
                {
                    Assignment5.test();
                    break;
                }

                case 5:
                {
                    on = false;
                    System.exit(0);
                }

                default:
                {
                    System.out.println("Choose one of the options!");
                }

            }
        }

        in.close();
        data.close();
    }

}
