import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by yzwang on 2017/11/20.
 */
public class FileCounter {

    private int characterCount, wordCount, lineCount;

    public FileCounter(){

        characterCount = 0;
        wordCount = 0;
        lineCount = 0;
    }

    /**
     Processes an input source and adds its character, word, and line
     counts to the respective variables.
     @param in the scanner to process
     */
    public void read(Scanner in) throws IOException {
        // your code

        while (in.hasNext()){
            lineCount++;
            String line = in.nextLine();
            characterCount += line.length();
            wordCount += line.split("\\s+").length;
        }

    }

    public int getCharacterCount(){

        return characterCount;
    }

    public int getWordCount(){

        return wordCount;
    }

    public int getLineCount(){

        return lineCount;
    }
}
