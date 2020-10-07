import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> 
{
    private Node<Item> first;     //Top of stack
    private int n;                //Size of the stack

    //Helper linked list class
    private static class Node<Item> 
    {
        private Item item;
        private Node<Item> next;
    }

    //Constructor, initalizes an empty stack
    public Stack() {
        first = null;
        n = 0;
    }

    //Returns true if false
    public boolean isEmpty() {
        return first == null;
    }

    //Returns size of array
    public int size() {
        return n;
    }

    //Adds items to the stack, Insert items at the top of stack
    public void push(Item item) 
    {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }
    
    //Removes items from the top of stack
    public Item pop() 
    {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;        //Save item to return
        first = first.next;            //Delete first node
        n--;
        return item;                   //Return the saved item
    }

    public String toString() 
    {
        StringBuilder s = new StringBuilder();
        for (Item item : this) 
        {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
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