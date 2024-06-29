public class Processor {
    private final String processorId;
    private Task currentTask;

    public Processor(String processorId) {
        this.processorId = processorId;
        this.currentTask = null;
    }

    public String getProcessorId() {
        return processorId;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void assignTask(Task task) {
        this.currentTask = task;
    }

    private Task releaseTask() {
        Task finishedTask = this.currentTask;
        this.currentTask = null;
        return finishedTask;
    }

    public boolean isIdle() {
        return this.currentTask == null;
    }

    public Task execute() {
        if (this.currentTask != null) {
            this.currentTask.execute();
            if (this.currentTask.getRemainingTime() <= 0) {
                return releaseTask();
            }
        }
        return null;
    }
}
