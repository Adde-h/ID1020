/*
Adeel Hussain
Generated: 2020-09-25, Updated: 2020-10-07
A method that is used to store keys and values (Redacted to only use methods needed in DFS & BFS)
Dependencies: MyQueue.java
Input: Strings and Integers (Keys & Values)
Reference: https://algs4.cs.princeton.edu/32bst/BST.java.html
*/

import java.util.NoSuchElementException;

public class BST<Key extends Comparable <Key>, Value>
{
    private Node root;              //Root of Tree

    private class Node              //Propeties of the Node
    {
        private Key key;            //Sorted by key
        private Value val;          //Value associated with key
        private Node left, right;   //Left & Right Subtrees
        private int size;           //Number of nodes in the subtree

        public Node(Key key, Value val, int size)   //Constructor
        {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }
    
    public int size()
    {
        return size(root);
    }

    private int size(Node x)    //Return number of key-value pairs in tree rooted at selected node
    {
        if(x == null)
        {
            return 0;
        }
        else
        {
            return x.size;
        }
    }
    public boolean contains(Key key)    //Check if the symbol table contains the given key, Return true or false
    {
        if(key == null)
        {
            throw new IllegalArgumentException("argument to contains() is null");
        }

        return get(key) != null;        //Return only if key is found
    }

    public Value get(Key key)
    {
        return get(root, key);  //Search for key, start at root   
    }

    private Value get(Node x, Key key)
    {
        if(key == null)                 
        {
            throw new IllegalArgumentException("calls get() with a null key");
        }

        if(x == null)                   
        {
            return null;
        }

        int cmp = key.compareTo(x.key); //Compare key with x.key (root in first iteration)

        if(cmp < 0)                     //If key is less than checked node
        {
            return get(x.left,key);     //Check left node for key
        }
        else if(cmp > 0)                //Else if key is greater than checked node
        {
            return get(x.right,key);    //Check right node for key
        }
        else                            //Else key found! Return found key
        {
            return x.val;
        }
    }

    public void put(Key key, Value val)
    {
        if(key == null)                 //No key inserted
        {
            throw new IllegalArgumentException("calls put() with a null key");
        }

        root = put(root, key, val);     //Insert new key, start at root
    }

    private Node put(Node x, Key key, Value val)
    {
        if(x == null)                           //If tree / subtree is empty
        {
            return new Node(key, val, 1);       //Create a node and set key, value and size
        }

        int cmp = key.compareTo(x.key);         //Compare key to chosen node (root in first iteration)

        if(cmp < 0)                             //If key is less than checked node
        {
            x.left = put(x.left, key, val);     //Go left and try create node in subtree
        } 
        else if(cmp > 0)                        //Else if key is greater than checked node
        {
            x.right = put(x.right, key, val);   //Go right and try create node in subree
        }
        else                                    //Else if node is found, set value of chosen node
        {
            x.val = val;
        }

        x.size = 1 + size(x.left) + size(x.right);  //Update chosen node size with containing left and right subarrays (What happens to parent node when child is added??)

        return x;
    }

    public Key min()            
    {
        if(isEmpty())
        {
            throw new NoSuchElementException("calls min() with empty symbol table");
        }
        return min(root).key;
    }

    private Node min(Node x)    //Return most left key
    {
        if(x.left == null)
        {
            return x;
        }
        else
        {
            return min(x.left);
        }
    }

    public Key max()    
    {
        if(isEmpty())
        {
            throw new NoSuchElementException("calls max() with empty symbol table");
        }
        return max(root).key;
    }

    private Node max(Node x)            //Return most right key
    {
        if(x.right == null)
        {
            return x;
        }
        else
        {
            return max(x.right);
        }
    }

    public Iterable<Key> keys()
    {
        if(isEmpty())
        {
            return new MyQueue<Key>();
        }
        return keys(min(), max());      //Search from left to right
    }

    public Iterable<Key> keys(Key lo, Key hi)
    {
        if(lo == null)
        {
            throw new IllegalArgumentException("first argument to keys() is null");
        }
        if(hi == null)
        {
            throw new IllegalArgumentException("second argument to keys() is null");
        }

        MyQueue<Key> MyQueue = new MyQueue<Key>();

        keys(root, MyQueue, lo, hi);
        return MyQueue;
    }

    private void keys (Node x, MyQueue<Key> MyQueue, Key lo, Key hi)
    {
        if(x == null)
        {
            return;
        }

        int cmplo = lo.compareTo(x.key);    //Start comparing from low

        int cmphi = hi.compareTo(x.key);    //Start comparing from high

        if(cmplo < 0)                       //Keep going left until no left left
        {
            keys(x.left, MyQueue, lo, hi);
        }

        if(cmplo <= 0 && cmphi >= 0)        //When both left and right finished, MyQueue from each recursive call
        {
            MyQueue.enqueue(x.key);
        }

        if(cmphi > 0)                       //Go right from previous root node
        {
            keys(x.right, MyQueue, lo, hi);
        }
    }
}
