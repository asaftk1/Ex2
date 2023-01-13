package Part_2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Adapter_Task extends FutureTask implements Comparable<Adapter_Task>{

    int priority;
    public Adapter_Task(Callable<?> callable) {
        super(callable);
        if(callable instanceof Task<?>){
            this.priority = ((Task<?>) callable).type.getPriorityValue();
        }
        else this.priority = 1;
    }
    public String toString(){
        return "task " +this.priority;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Adapter_Task o) {
        if(o == null) throw new IllegalArgumentException();

        else if(this.priority < o.priority) return -1;
        else if(this.priority > o.priority) return  1;
        return 0;
    }
}
