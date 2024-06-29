import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {

    @Override
    public int compare(Task t1, Task t2) {
        if(t1.getPriority() != t2.getPriority()) {
            return t2.getPriority() - t1.getPriority();
        }

        if(t1.getExecutionTime() != t2.getExecutionTime()){
            return t2.getRemainingTime() - t1.getExecutionTime();
        }

        return 0;
    }
}
