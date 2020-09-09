
/*
Adeel Hussain
Generated: 2020-09-03, Updated: 2020-09-07
Creates a queue that is double linked circular
Input: Abstract Data Type
Reference: https://algs4.cs.princeton.edu/13stacks/DoublyLinkedList.java.html
*/
import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Assignment3<Data> implements Iterable<Data>    // Queue, Generic datatype
{   

    private Node<Data> top;                                 // Creates top of queue
    private int counter;                                    // Counter

    private static class Node<Data>                         // Datatypes for queue
    {
        private Data data;
        private Node<Data> next;
        private Node<Data> previous;
    }

    public Assignment3()                                    // Constructor
    {
        top = null;
        counter = 0;
    }

    public boolean isEmpty()                                // If counter equals 0 then return true, else false
    {
        return counter == 0;
    }

    public void enqueue(Data data)                          // Method to adding to queue
    {
        if (top == null)                                    // If list is empty
        {
            top = new Node<Data>();                         // Create new node called referenced with top
            top.data = data;                                // load the data to top
            top.next = top;                                 // Circular list
            top.previous = top;                             // Circular list
        } 
        else 
        {
            Node<Data> last = top.previous;                 // Labels bottom previous as "last"
            Node<Data> newData = new Node<Data>();          // Creatas a new node
            newData.data = data;                            // Saves data to new node

            last.next = newData;                            // Points last next path to the new node
            newData.previous = last;                        // Points new node previous path to last (second last now)
            top.previous = newData;                         // Points top previous path to new node (creating circular list)
            newData.next = top;                             // Points new node next path to top (Creating circular list)
        }
        counter++;                                          // Increment counter
    }

    public void dequeue() {
        if (!isEmpty())                                     // If queue is not empty then run, else print "queue is empty!"
        {
            Data data = top.data;                           // Loads data from first in queue

            if (counter <= 1) {                             // If data being removed is last
                top.next = null;                            // Removes top next path
                top.previous = null;                        // Removes top previous path
                top = null;                                 // Sets top to null
            } 
            else 
            {
                Node<Data> second = top.next;               // Labels data second in queue as "second"
                Node<Data> last = top.previous;             // Labels data in last position as "last"
                second.previous = last;                     // Reroutes second (now first in queue) to point previous to last
                last.next = second;                         // Reroutes last in queue next path to point to second (now first in queue)
                top = second;                               // Move top reference to second (now first in queue)
            }

            counter--;

            System.out.println("The data dequeued from the list is: " + data);
        } 
        else 
        {
            System.out.println("No Element left to dequeue!\n");
        }
    }

    static void test() 
    {
        Assignment3<String> testqueue = new Assignment3<String>();

        System.out.println("\nStarted Test:");
        testqueue.enqueue("First");
        System.out.println(testqueue);
        testqueue.enqueue("Second");
        System.out.println(testqueue);
        testqueue.enqueue("Third");
        System.out.println(testqueue);

        testqueue.dequeue();
        System.out.println(testqueue);
        testqueue.dequeue();
        System.out.println(testqueue);
        testqueue.dequeue();
        System.out.println(testqueue);


        testqueue.dequeue();                                //Two extra to check incase trying to dequeue an empty list
        System.out.println(testqueue);
        testqueue.dequeue();
        System.out.println(testqueue);

        testqueue.enqueue("Working still");                 //Testing if program still can queue and no pointer error show up
        System.out.println(testqueue);
        testqueue.enqueue("Keeping it going!");
        System.out.println(testqueue);
        System.out.println("\n Test Finished");

        
    }
    
    public String toString() 
    {
        int pointer = 1;
        StringBuilder s = new StringBuilder();
        for (Data data : this)
        {
            if(pointer == counter)                    //If data to be printed is at the end then print without ","
            {
                s.append("[" + data + "]\n");
            }
            else                                        //Else print with ","
            {
                s.append("[" + data + "], ");
            }
            pointer++;
        }
        
        return s.toString();
    }

    public Iterator<Data> iterator()                        // Set up Iterator and use iterateQueue class
    {
        return new iterateQueue();
    }

    private class iterateQueue implements Iterator<Data>    // Define interateQueue class that implements Iterator
    {
        private Node<Data> current = top;                   // Create local variable "current" and point to first of queue

        public boolean hasNext()                            // If queue has a next data value, return true else false
        {
            return current.next != null;
        }

        public Data next() 
        {
            if (!hasNext()) 
            {
                throw new NoSuchElementException();
            } 
            else 
            {
                Data data = current.data;
                current = current.next;
                return data;
            }
        }
    }

    public static void main(String[] args) 
    {
        boolean on = true;
        Scanner in = new Scanner(System.in);
        Scanner data = new Scanner(System.in);

        Assignment3<String> queue = new Assignment3<String>();

        while (on == true) {
            System.out.println("\n-----------------------------------------------");
            System.out.println("What would you like to do?");
            System.out.println("1: Enqueue (Add to queue)");
            System.out.println("2: Dequeue (Load from queue)");
            System.out.println("3: Print contents of queue");
            System.out.println("4: Run Test");
            System.out.println("5: Exit");
            System.out.println("-----------------------------------------------");

            int command = in.nextInt();

            switch (command) 
            {
                case 1: 
                {
                    System.out.println("What would you like to add?");
                    queue.enqueue(data.next());
                    System.out.println(queue);
                    break;
                }

                case 2: 
                {
                    queue.dequeue();
                    System.out.println(queue);
                    break;
                }

                case 3: 
                {
                    System.out.println(queue);
                    break;
                }

                case 4: 
                {
                    Assignment3.test();
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
