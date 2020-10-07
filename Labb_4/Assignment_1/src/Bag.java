/*
Adeel Hussain
Generated: 2020-10-01, Updated: 2020-10-06
Input: Any Items
Reference: https://algs4.cs.princeton.edu/13stacks/Bag.java.html
*/


import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> 
{
    private Node<Item> first;    //Beginning of bag
    private int n;               //Number of elements in bag

    //Helper linked list class
    private static class Node<Item> 
    {
        private Item item;
        private Node<Item> next;
    }
    
    //Initializes an empty bag, Constructor
    public Bag() 
    {
        first = null;
        n = 0;
    }

    //Returns true if the bag is empty
    public boolean isEmpty() {
        return first == null;
    }

    //Returns number of items in bag
    public int size() {
        return n;
    }

    //Adds item to bag, Inserts item to front of bag
    public void add(Item item) 
    {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Iterator<Item> iterator()  
    {
        return new LinkedIterator(first);  
    }

    private class LinkedIterator implements Iterator<Item> 
    {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) 
        {
            current = first;
        }

        public boolean hasNext()  
        { 
            return current != null;                     
        }

        public Item next() 
        {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }

}
