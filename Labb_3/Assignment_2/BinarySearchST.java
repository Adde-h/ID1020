/*
Adeel Hussain
Generated: 2020-09-24, Updated: 2020-09-26
A method that is using Binary Search in a Symbol Table to store Keys and Values

Input: Strings and Integers (Keys & Values)
Reference: https://algs4.cs.princeton.edu/31elementary/BinarySearchST.java.html
*/

public class BinarySearchST<Key extends Comparable<Key>, Value> 
{
    private static final int INIT_CPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int n = 0;          // n = ammount of keys in ST

    public BinarySearchST()     //Initializes an empty symbol table, constructor
    {
        this(INIT_CPACITY);
    }

    public BinarySearchST(int capacity) //Constructor, initializes an empty symbol table with the chosen capacity
    {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    private void resize(int capacity)
    {
        assert capacity >= n;
        Key[] tempk = (Key[]) new Comparable[capacity];     //Creates a new temporary array for keys with new capacity
        Value[] tempv = (Value[]) new Object[capacity];     //Creates a new temporary array for values with new capacity
        for(int i = 0; i < n; i++)                          //Copies over the existing keys & values
        {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;                                       //Redirect pointers to the new array
        keys = tempk;
    }

    public int size()                                       //Returns size
    {
        return n;
    }

    public boolean isEmpty()                                //Returns true if empty, false if not
    {
        return size() == 0;
    }

    public boolean contains(Key key) //checks if the symbol table contains the given key, True if it cointains the key, otherwise false
    {
        if(key == null)
        {
            throw new IllegalArgumentException("Argument to contains() is null");
        }

        return get(key) != null;
    }

    public Value get(Key key)   //Returns value of the given key from the Symbol Table IF key exists in the ST
    {
        if (key == null)
        {
            throw new IllegalArgumentException("argument to get() is null");
        } 

        if(isEmpty())
        {
            return null;
        }

        int i = rank(key);                          //Retrive position of key in need

        if(i < n && keys[i].compareTo(key) == 0)    //If key position is less than ST size && key retrived matches key searched
        {
            return vals[i];                         //Return value of the index stored with key position
        }
        else
        {
            return null;                            //Else Key does not exist
        }
    }

    public int rank(Key key) //Returns the number of keys in the ST strictly less than chosen Key ??
    {
        if(key == null)
        {
            throw new IllegalArgumentException("argument to rank() is null");
        }

        int lo = 0, hi = n-1;

        while(lo <= hi)                         //Binary Search Algorithm
        {
            int mid = lo + (hi-lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0)                        //If key being search is less than mid key, search in left subarray
            {
                hi = mid - 1;
            }
            else if (cmp > 0)                   //If key being searched is greater than mid key, search in right array
            {
                lo = mid + 1;
            }
            else                                //Else found the key
            {
                return mid;                     //Return position of key
            }
        }
        return lo;                              //If search failed return lo position ??
    }

    public void put(Key key, Value val)         //Inserting Key and Value to the ST
    {
        if(key == null)
        {
            throw new IllegalArgumentException("First argument to put() is null");
        }

        int i = rank(key);                          //Find key in ST, Else return pos for key to be inserted

        if(i < n && keys[i].compareTo(key) == 0)    //If key already in ST
        {
            vals[i] = val;                          //Replace the value with chosen value
            return;
        }

        if(n == keys.length)                        //If key array is full, n = ammount of keys in ST
        {
            resize(2 * keys.length);                //Double the array
        }

        for(int j = n; j > i; j--)                  //Move keys and values infront of chosen key by 1 step
        {                                           //Making space for key & value to be inserted
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        
        keys[i] = key;                              //Now insert the chosen key and its value in its right position
        vals[i] = val;
        n++;                                        //Incrementing ammount of keys in ST
    }

    public static void main(String[] args) 
    {
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
        String key = "test";
        String key2 = "Rakin";
        String key3 = "Mehir";
        st.put(key, 6);
        st.put(key2, 69);
        st.put(key3, 1);

        System.out.println(st.get("test"));
        System.out.println(st.get(key2));
        System.out.println(st.get(key3));
    }
}
