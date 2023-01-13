# Ex2

## Part 1

### In this part of the assignment we create several text files and calculate the total number of lines in these files. 

### We will use four methods: 
#### 1) public static String[] createTextFiles(int n, int seed, int bound){…} - 
        In this method we are creating n files,
        The number of lines in each file is a random number, obtained with the help of a Random class usage and the seed and bound                 parameters.
        the function returns String Array that contains the name of the files we created.

#### 2) public static int getNumOfLines(String[] fileNames){…} -
        Using integer sum veriable initialized with 0 at first.
        This method going over the fileNames that we created in function 1 ,
        checks if there is a file with the name provided in the Array -> open the file and read it line by line -> for each line , sum++.
        after going over all files the function returns the sum veriable that contain the total number of lines in these files.
        
#### 3) public static int getNumOfLinesThreads(String[] fileNames){…}
        
       Using Class LineCounter that extands Thread 
       LineCounter contains:
       two data members:  
        private String fileName;
        AtomicInteger lineCounter;
        
       puclib void run() function:
        This method is used to count number of lines in a file using AtomicInteger.
        
        In getNumOfLinesThreads function -> at first LineCounter array is anitialized  with the size of fileNames length.
        going over the whole array and for every index we create a new LineCounter with the fileName[i] and right after we stand this Thread.
        aftter statrting all of the Thread we sum up the lines of every files and return this sum value.
        
#### 4) public int getNumOfLinesThreadPool(String[] fileNames){…}    
        
        Using Class MyCallable that implements Callable
        MyCallable contains:
        one data member -> String fileName
        
         public object call() function:
         This method is used to count number of lines in a file using int veriable cald count,
         the function returns count -> contains the number of lines in the file;
        
        In getNumOfLinesThreadPool function -> 
        initialized  a List of Future<Integer> 
        inisialized  a new ThreadPool using ExecutorService.
        ---> going in loop from 0 to fileNames length --> creat a new Mycalable with fileName[i] and adding to the list this submited Calablle.
        ---> going in loop and get the futrue value into sum veriable.
        return sum.
        
        
 ## Part 2
 
 ### In this part we will implement PriorityQueue of theard which return value.
      in order to implement this part ,  we created 4 classes.
      
#### Task
        Task implements the Callable interface in order to make threads which can return value
        It is also has a field of TaskType , in order to be sorted in PriorityQueue
        
        
#### TaskType
        TaskType is an Enum of priority values of Tasks. 
        Includes three types of priority values:
        1) COMPUTATIONAL(1) - the highest prioirity of each Task.
        2) IO(2) -            the medium prioirity of each Task.
        3) OTHER(3) -         the lowest prioirity of each Task.
        
#### AdapterTask 
        Extends FutureTask and implements Comparable
        ThreadpoolExecutor has only BlockingQueue<Runnable> .
        Therefore we need an AdapterTask in order to get the Task(type of Callable) in its constructor , so the Tasks could be sorted.
        The Tasks are sorted according to their priority values.
        
#### CumstomExecutor 
        Extends ThreadPoolExecutor.
        Has ThreadPool of PriorityBlockingQueue<Runnable>.
        Each Task that we get in the submit-function , is casted to be an AdapterTask in order to be sorted in the queue.
        
        
