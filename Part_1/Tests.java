package Part_1;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class Tests {

    int n = 100, seed = 10, bound = 99999, count = 0;
    String fileNames[] = Ex2_1.createTextFiles(n, seed, bound);

    @Test
    public void createTextFilesTest() {
        String line;


        File dir = new File("C:\\Users\\Asaf\\IdeaProjects\\Ex2_OPP");
        /**
         * checks if n files were created
         */
        Assert.assertEquals(fileNames.length, n);
        /**
         * checks if for every file that were created there in no more than the bound amount of lines
         */
        for (int i = 0; i < fileNames.length; i++) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileNames[i]));
                while ((line = br.readLine()) != null) {
                    count++;

                }
                Assert.assertFalse(count > bound);
                count = 0;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void NumOfLines() throws Exception
    {
        /**
         * checks if all of our LineCounting functions returns the same value
         */
        Assert.assertEquals(Ex2_1.getNumOfLines(fileNames),Ex2_1.getNumOfLinesThreads(fileNames),Ex2_1.getNumOfLinesThreadPool(fileNames));
    }


}
