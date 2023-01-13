package Part_2;

import java.util.Objects;
import java.util.concurrent.*;

/**@Task
 * implements the Callable interface in order to make threads which can return value
 *
 * @param <V> Callable that the Task can get in its constructor
 * @param <V> Type , returns the priority value of each Task.
 *
 */
public class Task<V> implements Callable {
    TaskType type;
    Callable<V> callable;


    public String toString() {
        return "Task{" +
                "type=" + this.type.getPriorityValue()+"}"
                ;
    }

    /**
     * We have 2 constructors of 2 factory-methods
     * @param callable
     */
    private Task(Callable callable) {
        this.type = TaskType.COMPUTATIONAL;
        this.callable = callable;
    }

    private Task(Callable callable, TaskType type) {
        this.type = type;
        this.callable = callable;
    }


    @Override
    public V call() throws Exception {
        return this.callable.call();
    }


    public static Task createTask(Callable callable, TaskType type) {

        return new Task(callable,type);
    }
    public static Task createTask(Callable callable) {

        return new Task(callable);
    }

    /**
     *
     * @param o Task that compared to this Task
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public boolean equals (Task o) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        boolean a = this.type.getPriorityValue() == o.type.getPriorityValue();
        Future<V> f1 =  executorService.submit(this);
        Future<V> f2 =  executorService.submit(o);
        boolean b = f1.get().equals(f2.get());
        executorService.shutdown();

        return a && b;
    }

}





