public class Task{
    private final String taskId;
    private final int creationTime;
    private final int executionTime;
    private final int priority;
    private int remainingTime;

    public Task(String taskId, int creationTime, int executionTime, int priority) {
        this.taskId = taskId;
        this.creationTime = creationTime;
        this.executionTime = executionTime;
        this.priority = priority;
        this.remainingTime = executionTime;
    }

    public String getTaskId() {
        return taskId;
    }

    public int getCreationTime() {
        return creationTime;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void execute() {
        if (remainingTime > 0) {
            remainingTime--;
        }
    }

    @Override
    public String toString() {
        return "Task " + taskId + ": [Creation Time: " + creationTime + ", Execution Time: " + executionTime + ", Priority: " + priority + "]";
    }

}
