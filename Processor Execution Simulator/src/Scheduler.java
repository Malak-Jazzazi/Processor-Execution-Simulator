import java.util.PriorityQueue;

public class Scheduler {
    private final PriorityQueue<Task> taskQueue;

    public Scheduler() {
        taskQueue = new PriorityQueue<>(new TaskComparator());
    }

    public void addTask(Task task) {
        taskQueue.offer(task);
    }

    public Task getNextTask() {
        return taskQueue.poll();
    }

    public boolean hasTasks() {
        return !taskQueue.isEmpty();
    }
}