import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SeparateChainingLiteHashST<Key, Value> {

    private int m; // hash table size
    private Node[] st; // array of linked-list symbol tables

    // a helper linked list data type
    private static class Node {
        private Object key;
        private Object val;
        private Node next;

        public Node(Object key, Object val, Node next) 
        {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    // create separate chaining hash table
    public SeparateChainingLiteHashST() 
    {
        this(31);
    }

    // create separate chaining hash table with m lists
    public SeparateChainingLiteHashST(int m) 
    {
        this.m = m;
        st = new Node[m];
    }

    // hash value between 0 and m-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    // is the key in the symbol table?
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // return value associated with key, null if no such key
    public Value get(Key key) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) 
        {
            if (key.equals(x.key)) 
            {
                return (Value) x.val;
            }
        }
        return null;
    }

    // insert key-value pair into the table
    public void put(Key key, Value val) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) //Only runs when key does exist in hash table
        {

            if (key.equals(x.key)) 
            {
                System.out.println("The word \"" + val + "\" has the key " + i + " has been found again!");
                x.val = val;
                return;
            }
        }

        System.out.println("The word \"" + val + "\" has the key " + i);
        st[i] = new Node(key, val, st[i]);
    }

    // return all keys as an Iterable
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++) 
        {
            for (Node x = st[i]; x != null; x = x.next) 
            {
                queue.enqueue((Key) x.key);
            }
        }
        return queue;
    }

    public static void main(String[] args) throws FileNotFoundException 
    { 
        SeparateChainingLiteHashST<String, String> st = new SeparateChainingLiteHashST<String, String>(97);
        
        File myText = new File("filteredText.txt");
        Scanner Scan = new Scanner(myText);
        int words = 0;
        int maxWords = 20;
        int minlength = 1;


        /* Insertion of words */
        while((Scan.hasNext()) && (words < maxWords))
        {
            String word = (Scan.next()).toLowerCase();

            if(word.length() < minlength)
            {
                continue;
            }
            words++;

            st.put(word, word);
        }
        
        for (String s : st.keys()) 
        System.out.println(st.get(s)); 

        Scan.close();
        
    }
}