package Q3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class WordsCounter {
    /** The ConcurrentHashMap class of the Java collections framework provides a thread-safe map.
     *  That is, multiple threads can access the map at once without affecting the consistency
     *  of entries in a map.
     *  */
    private final Map<String, Integer> wordsCounter = new ConcurrentHashMap<>();

    public void load(String... files) {
        try {
            /** create a threadHandler list in the length of file list parameter */
            WCThreadHandler[] threads = new WCThreadHandler[files.length];
            /** for each file create threadHandler obj with the file
             *  and thread safe wordsCounter
             *  and then start the thread */
            for (int i = 0; i < files.length; i++) {
                final String file = files[i];
                threads[i] = new WCThreadHandler(file, wordsCounter);
                threads[i].start();
            }
            for (WCThreadHandler thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch (NullPointerException e) {
            System.out.println("NullPointerException thrown!");
        }
    }

    public void displayStatus() {
        Display display = new Display(wordsCounter);
        display.displayStatus();
    }

    /** get word count by key*/
    public int getWordCount(String word) {
        Integer count = wordsCounter.get(word);
        return count != null ? count : 0;
    }

    /** calc. total words count */
    public int getTotalCount() {
        int totalCount = 0;
        for (Integer count : wordsCounter.values()) {
            totalCount += count;
        }
        return totalCount;
    }

    public static void main(String[] args) {
        WordsCounter wc = new WordsCounter();
        String dir = System.getProperty("user.dir") + "/Q3TextFiles/";
        wc.load(dir+"file1.txt", dir+"file2.txt", dir+"file3.txt");
        wc.displayStatus();
    }
}
