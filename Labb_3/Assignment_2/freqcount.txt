public class FrequencyCounter {

    // Do not instantiate.
    private FrequencyCounter() { }

        public static void main(String[] args) throws FileNotFoundException {

        File myText = new File("newText1.txt");
        Scanner reader = new Scanner(myText);
        Scanner reader2 = new Scanner(myText);

        int NUMBEROFWORDS = 100;
        int distinct = 0, words = 0;
        int minlen = 1;

        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
        BST<String, Integer> st3 = new BST<String, Integer>();


        //Binary search ST
        long start = System.nanoTime();
        while(reader.hasNextLine() && words < NUMBEROFWORDS){
            String key = reader.next();
            if(key.length() < minlen) {
                words++;
                continue;
            }
            words++;
            if(st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
            }
        }
        long end = System.nanoTime();
        long time = end - start;

        //BST
        int distinct2 = 0, words2 = 0;
        long start2 = System.nanoTime();
        while(reader2.hasNextLine() && words2 < NUMBEROFWORDS){
            String key = reader2.next();
            if(key.length() < minlen) {
                words2++;
                continue;
            }
            words2++;
            if(st3.contains(key)) {
                st3.put(key, st3.get(key) + 1);
            }
            else {
                st3.put(key, 1);
                distinct2++;
            }
        }
        long end2 = System.nanoTime();
        long time2 = end2 - start2;

        StdOut.println("running time Binary search ST: " + time + " ns");
        StdOut.println("running time BST:              " + time2 + " ns");
        StdOut.println("Number of words: " + NUMBEROFWORDS);