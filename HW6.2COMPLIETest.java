import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;//add import


public class Test {
    public static void parse(File file)throws IOException {//add ioexception
        RandomAccessFile input = null;
        String line = null;//varible is redundant

        try {
            input = new RandomAccessFile(file, "r");
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            //Delete return since void method does not need return.
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }
}
