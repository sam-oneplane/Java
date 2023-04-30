package Q3;

import java.util.Map;

public class Display {

    private final Map<String, Integer> wordsCounter;
    public Display(Map<String, Integer> wordsCounter) {
        this.wordsCounter = wordsCounter;
    }
    public void displayStatus() {
        int totalCount = 0;
        for (Map.Entry<String, Integer> entry : wordsCounter.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            totalCount += entry.getValue();
        }
        System.out.println("** total: " + totalCount);
    }
}
