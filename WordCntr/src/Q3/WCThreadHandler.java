package Q3;

import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;

public class WCThreadHandler extends Thread {

    private final String file;
    private final Map<String, Integer> wordsCounter;

    public WCThreadHandler(String file, Map<String, Integer> wordsCounter) {
        this.file = file;
        this.wordsCounter = wordsCounter;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            /** for each line
             *     1. split words in line using regex (include special chars)
             *     2. for each word compute
             *          if no entry set entry with value 1 else inc value by 1
             */
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] words = line.split("[\\s{}()!:,;@&.?$+]+");
                    for (String word : words) {
                        wordsCounter.compute(word, (key, value) -> value == null ? 1 : value + 1);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error while reading and processing file: " + file);
            e.printStackTrace();
        }
    }
}
