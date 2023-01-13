package Part_2;

import java.util.List;
import java.util.concurrent.*;

public class CustomExecutor extends ThreadPoolExecutor {

    public CustomExecutor() {
        super(2, 3, 300, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>());

    }

    /** @submit
     * Adapter_Task extends Future_Task
     * FutureTask takes Callable in its constructor
     * FutureTask implements Runnable
     * Therefore , we can sort the task in the PriorityBlockingQueue according to the Comparable implementation of the AdapterTask
     * @param task
     * @return AdapterTask
     */
    Future submit(Task task) {

        Adapter_Task b = new Adapter_Task(task);
        this.execute(b);

        return b;
    }


    Future submit(Callable callable, TaskType type) {
        return this.submit(Task.createTask(callable, type));
    }

    @Override
    public Future submit(Callable callable) {
        return this.submit(Task.createTask(callable));
    }

    public int getCurrentMax() {
       return 0;
    }


    /**@gracefullyTerminate
     * Make sure that all the workers have done and the queue is empty
     * Then shutdown the ThreadPoolExecutor
     */
    public void gracefullyTerminate() {
        while (this.getActiveCount() > 0 || this.getQueue().size() > 0) {
        }
        this.shutdown();
    }

}