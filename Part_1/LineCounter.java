package Part_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * LineCounter class extends the Thread class and is used to count the number of lines in a specified file.
 * It takes in a fileName as a constructor parameter and an AtomicInteger object to store the line count.
 * The class overrides the run method to read the specified file line by line and increments the line counter for each line.
 * The reader is closed once the reading is complete.
 */
public class LineCounter extends Thread{
    private String fileName;
    AtomicInteger lineCounter;
    int sum =0;

    /**
     * Constructor that takes in a fileName as parameter
     * and initializes an AtomicInteger object to store the line count.
     *
     * @param fileName the name of the file to count the lines for
     */
    public LineCounter(String fileName)
    {
        this.fileName = fileName;
        this.lineCounter = new AtomicInteger(0);
    }

    /**
     * This method is called when the thread is started.
     * It uses a BufferedReader to read the specified file line by line and increments the lineCounter for each line.
     * The reader is closed once the reading is complete.
     *
     */
    @Override
    public void run(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null)
            {
                this.lineCounter.incrementAndGet();
                this.sum ++;
            }
            reader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
