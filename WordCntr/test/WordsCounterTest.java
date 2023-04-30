import Q3.WordsCounter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class WordsCounterTest {

    private static String testDir;

    @BeforeAll
    static void setup() {
        // Create a temporary directory for test files
        try {
            testDir = Files.createTempDirectory("wc-test").toString() + "/";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testLoad() throws IOException {
        // Create some test files
        Files.write(Paths.get(testDir + "file1.txt"), "this is a first file".getBytes());
        Files.write(Paths.get(testDir + "file2.txt"), "this one is the second file".getBytes());
        Files.write(Paths.get(testDir + "file3.txt"), "and this is the third file".getBytes());

        // Load the word counts
        WordsCounter wc = new WordsCounter();
        wc.load(testDir+"file1.txt", testDir+"file2.txt", testDir+"file3.txt");

        // Check that the word counts are correct
        assertEquals(3, wc.getWordCount("is"));
        assertEquals(3, wc.getWordCount("this"));
        assertEquals(2, wc.getWordCount("the"));
        assertEquals(3, wc.getWordCount("file"));
        assertEquals(1, wc.getWordCount("and"));
        assertEquals(1, wc.getWordCount("first"));
        assertEquals(1, wc.getWordCount("second"));
        assertEquals(1, wc.getWordCount("third"));
        assertEquals(1, wc.getWordCount("one"));
    }

    @Test
    void testLoadWithSpecialCharacters() throws IOException {
        // Create some test files
        Files.write(Paths.get(testDir + "file4.txt"), "this is @ first,  file.".getBytes());
        Files.write(Paths.get(testDir + "file5.txt"), "this (not the other), the second file!".getBytes());

        // Load the word counts
        WordsCounter wc = new WordsCounter();
        wc.load(testDir+"file4.txt", testDir+"file5.txt");

        assertEquals(2, wc.getWordCount("this"));
        assertEquals(2, wc.getWordCount("the"));
        assertEquals(2, wc.getWordCount("file"));
        assertEquals(1, wc.getWordCount("first"));
        assertEquals(1, wc.getWordCount("second"));
        assertEquals(1, wc.getWordCount("not"));
        assertEquals(1, wc.getWordCount("other"));

    }

    @Test
    void testLoadNoFiles() {
        // Load with no files and verify that the word count is 0
        WordsCounter wc = new WordsCounter();
        wc.load();
        assertEquals(0, wc.getTotalCount());
    }

    @Test
    void testLoadNonexistentFile() {
        // Load a nonexistent file and verify that an error message is printed
        WordsCounter wc = new WordsCounter();
        wc.load("nonexistent.txt");
        assertEquals(0, wc.getTotalCount());
    }

    @Test
    void testLoadEmptyFile() throws IOException {
        // Create an empty test file
        Files.write(Paths.get(testDir + "empty.txt"), "".getBytes());

        // Load and verify that the word count is 0
        WordsCounter wc = new WordsCounter();
        wc.load(testDir+"empty.txt");
        assertEquals(0, wc.getTotalCount());
    }

    @Test
    void testLoadDirectory() throws IOException {
        // Create a directory inside the test directory
        Files.createDirectory(Paths.get(testDir + "subdir"));

        // Load the directory and verify that an error message is printed
        WordsCounter wc = new WordsCounter();
        wc.load(testDir+"subdir");
        assertEquals(0, wc.getTotalCount());
    }

    @Test
    void testLoadNull() {
        // Load with null argument and verify that an error message is printed
        WordsCounter wc = new WordsCounter();
        wc.load(null);
        assertEquals(0, wc.getTotalCount());
    }

}
