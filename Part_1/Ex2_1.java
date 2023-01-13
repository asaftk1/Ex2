package Part_1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Ex2_1 {


///////////////////////////////Q1/////////////////////////////
    public static String[] createTextFiles(int n, int seed, int bound)
    {
        Random rand = new Random(seed);
        String Files[] = new String[n];

        for (int i = 0; i < n; i++) {
            int x = rand.nextInt(bound);
            try(BufferedWriter bw = new BufferedWriter(new FileWriter("file_"+(i+1)+".txt")))
            {
                for (int j = 0; j < x; j++) {
                    bw.write("hello World");
                    bw.newLine();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            //   System.out.println(x);
            Files[i] = "file_"+(i+1)+".txt";

        }


        return Files;
    }

///////////////////////////////Q2//////////////////////////////////
    public static int getNumOfLines(String[] fileNames)
    {
        long startTime = System.currentTimeMillis();
        int sum=0;

        String line;
        for (int i = 0; i < fileNames.length; i++) {
            try(BufferedReader br = new BufferedReader(new FileReader(fileNames[i]))) {
                while ((line = br.readLine()) != null)
                {
                    sum++;
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time for getNumOfLines is: "+(endTime-startTime)+" Millis");

        return sum;
    }


/////////////////////////////////Q3////////////////////////////////////////////////////


    public static int getNumOfLinesThreads(String[] fileNames)
    {
        long startTime = System.currentTimeMillis();

       LineCounter[] thread = new LineCounter[fileNames.length];
        int sum=0;

        for (int i = 0; i < fileNames.length; i++) {
            thread[i] = new LineCounter(fileNames[i]);
            thread[i].start();
        }
        for (int i = 0; i < thread.length; i++)
        {
            try {
                thread[i].join();
                sum += thread[i].lineCounter.get();
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
           }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time for getNumOfLinesThreads is: "+(endTime-startTime)+" Millis");
        return sum;
    }



//////////////////////////////Q4///////////////////////////////


    public static int getNumOfLinesThreadPool(String[] fileNames) throws Exception {
        int sum = 0;
        long startTime = System.currentTimeMillis();
        List<Future<Integer>> Linelist= new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(fileNames.length);
        for (int i = 0; i < fileNames.length; i++)
        {
            Callable c1 = new MyCallable(fileNames[i]);
            Linelist.add(executor.submit(c1));
        }
        for (Future<Integer> num: Linelist) {
            sum += num.get();
        }

        executor.shutdown();
        while (executor.isTerminated())
        {

        }
        long endTime = System.currentTimeMillis();
       System.out.println("Time for getNumOfLinesThreadPool is: "+(endTime-startTime)+" Millis");
        return sum;
    }
    public static void clean(String[] fileNames) {
        for (int i = 0; i <fileNames.length ; i++) {
            File file = new File(fileNames[i]);
            file.delete();
        }
    }



    public static void main(String[] args) throws Exception {
        String test[] = createTextFiles(1000,10,100000);

        System.out.println(getNumOfLines(test));
        Ex2_1 instance = new Ex2_1();
        System.out.println(instance.getNumOfLinesThreads(test));

        System.out.println(instance.getNumOfLinesThreadPool(test));

        clean(test);



    }



}
