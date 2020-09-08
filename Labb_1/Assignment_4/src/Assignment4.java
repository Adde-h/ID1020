/*
Adeel Hussain
Generated: 2020-09-04, Updated: 2020-09-08
Creates a queue that is double linked circular, user can add from end and start of queue
Input: Abstract Data Type
Reference: https://algs4.cs.princeton.edu/13stacks/DoublyLinkedList.java.html
*/
import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Assignment4<Data> implements Iterable<Data> {    //Queue, Generic datatype
    
    private Node<Data> top;         //Creates top of queue
    private int counter;            //Counter

    private static class Node<Data> //Datatypes for queue
    {
        private Data data;
        private Node<Data> next;
        private Node<Data> previous;
    }

    public Assignment4()                        //Constructor for double linked queue, initializes two nodes, top and bottom
    {
        top = null;
        counter = 0;
    }

    public boolean isEmpty()                    //If counter equals 0 then return true, else false
    {  
        return counter == 0;
    }

    public void enqueue(Data data, int location)    //Method to adding to queue
    {

        if(top == null)                         //If list is empty
        {
            top = new Node<Data>();             //Create new node called referenced with top
            top.data = data;                    //load the data to top
            top.next = top;                     //Circular list
            top.previous = top;                 //Circular list
        }
        else
        {
            Node<Data> newData = new Node<Data>();  //Creatas a new node
            newData.data = data;                    //Saves data to new node
            Node<Data> last = top.previous;         //Labels bottom previous as "last"


            if(location == 1)
            {
                top.previous = newData;         //Sets top previous path to new Data, creating new data at first pos
                last.next = newData;            //Sets last next path to newData, linking it as circular
                newData.next = top;             //Sets new Data next path to top (now second in queue)
                newData.previous = last;        //Sets new Data previous path to last, linking as circular
                top = newData;                  //Moving top pointer to new Data, placing it as first in queue

            }
            else
            {
                last.next = newData;                    //Points last next path to the new node
                newData.previous = last;                //Points new node previous path to last (second last now)
                top.previous = newData;                 //Points top previous path to new node (creating circular list)
                newData.next = top;                     //Points new node next path to top (Creating circular list)
            }
            
        }
        counter++;                                      //Increment counter
    }

    public void dequeue(int location)
    {
        if(!isEmpty())                                  //If queue is not empty then run, else print "queue is empty!"
        {
            if(counter <= 1)
            {
                Data data = top.data;          //Loads data from first in queue
                top.next = null;               //Removes top next path
                top.previous = null;           //Removes top previous path
                top = null;                    //Sets top to null  
                System.out.println("The data dequeued from bottom of the list is: " + data);

            }
            else
            {
                if(location == 2)                                   //Remove from last in queue
                {
                    Node<Data> last = top.previous;                     //Points to last in queue
                    Data data = last.data;                              //Loads data from last in queue
                    Node<Data> secondlast = last.previous;
                    secondlast.next = top;
                    top.previous = secondlast;
                    System.out.println("The data dequeued from bottom of the list is: " + data);

                }
                else                                    //Else remove from first in queue
                {
                    Data data = top.data;                   //Loads data from first in queue
                    Node<Data> second = top.next;           //Labels data second in queue as "second"
                    Node<Data> last = top.previous;         //Labels data in last position as "last"
                    second.previous = last;                 //Reroutes second (now first in queue) to point previous to last
                    last.next = second;                     //Reroutes last in queue next path to point to second (now first in queue)
                    top = second;                           //Move top reference to second (now first in queue)

                    System.out.println("The data dequeued from top of the list is: " + data);
                }
            }

            counter--;
        }
        else
        {
            System.out.println("The queue is empty!");
        }
    }

    static void test()
    {
        Assignment4<String> testqueue = new Assignment4<String>();

        System.out.println("\nStarted Test:");
        testqueue.enqueue("1st", 1);
        testqueue.printqueue();
        testqueue.enqueue("second", 2);
        testqueue.printqueue();
        testqueue.enqueue("third", 2);
        testqueue.printqueue();
        testqueue.enqueue("first", 1);
        testqueue.printqueue();

        testqueue.dequeue(1);
        testqueue.printqueue();
        testqueue.dequeue(1);
        testqueue.printqueue();
        testqueue.dequeue(1);
        testqueue.printqueue();
        testqueue.dequeue(1);
        testqueue.printqueue();
        testqueue.dequeue(1);   //Testing one extra
        testqueue.printqueue();


        testqueue.enqueue("Still can queue", 1);
        testqueue.printqueue();
        testqueue.enqueue("Nothing is broken", 2);
        testqueue.printqueue();
        testqueue.enqueue("No errors!", 1);

        testqueue.dequeue(2);
        testqueue.dequeue(2);
        testqueue.dequeue(2);
        testqueue.dequeue(2);   //Testing one extra
        System.out.println("\n Test Finished");

    }

    public void printqueue()                            //Uses iterator to print queue
	{
        Iterator<Data> queue = this.iterator();
        int index = 0;
        System.out.println("\nContent of queue:");

		while(index < counter)                          //While iterator pointer has not reached end of queue
		{
            Data data = queue.next();                   //Get data of current node in queue and then move pointer to next
			if(index >= counter-1)                            //If data to be printed is at the end then print without ","
			{
				System.out.println("[" + data + "] ");
			}
			else                                        //Else print with ","
			{
				System.out.print("[" + data + "], ");
			}
			index++;
        }
        if(isEmpty())
        {
            System.out.println("Queue is empty!");
        }
	}

    public Iterator<Data> iterator()                        //Set up Iterator and use iterateQueue class
	{
		return new iterateQueue();
	}

    private class iterateQueue implements Iterator<Data>    //Define interateQueue class that implements Iterator
	{
		private Node<Data> current = top;                   //Create local variable "current" and point to first of queue

        public boolean hasNext()                            //If queue has a next data value, return true else false
        {
			return current.next != null;
		}

        public Data next() 
        {
            if(!hasNext())
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
        
        Assignment4<String> queue = new Assignment4<String>();

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
            int location = 0;     // 1 = Front, 2 = Back
            switch(command)
            {
                case 1:
                {
                    System.out.println("Where would you like to add to the queue?\n 1: Front\n 2: Back");
                    location = data.nextInt();
                
                    System.out.println("What data would you like to add?");
                    queue.enqueue(data.next(), location);
                    queue.printqueue();
                    break;
                    
                }

                case 2:
                {
                    System.out.println("Where would you like to remove from the queue?\n 1: Front\n 2: Back");
                    location = data.nextInt();
                    queue.dequeue(location);
                    queue.printqueue();
                    break;
                }

                case 3:
                {
                    queue.printqueue();
                    break;
                }

                case 4: {
                    Assignment4.test();
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