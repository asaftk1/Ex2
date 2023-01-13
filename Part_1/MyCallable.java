package Part_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * MyCallable class implements the Callable interface and is used to count the number of lines in a specified file.
 * It takes in a fileName as a constructor parameter and returns the line count as the result of the call method.
 *
 */
public class MyCallable implements Callable {
    //  AtomicInteger counter = new AtomicInteger(0);
    String fileName;
    /**
            * Constructor that takes in a fileName as parameter
     *
             * @param fileName the name of the file to count the lines for
            */
    public MyCallable(String fileName) {
        this.fileName = fileName;
    }

    /**
     * This method is called when the callable task is executed.
     * It uses a BufferedReader to read the specified file line by line and increments a count for each line.
     * The reader is closed once the reading is complete and the count is returned as the result.
     *
     * @return an integer representing the number of lines in the specified file
     * @throws Exception
     */
    @Override
    public Object call() throws Exception {
        int count = 0;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while (reader.readLine() != null) {
            count++;
        }
        reader.close();
        return count;
    }
}
