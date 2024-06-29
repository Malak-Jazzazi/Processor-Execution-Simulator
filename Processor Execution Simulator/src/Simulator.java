import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulator {

    private final int totalClockCycles;
    private final String taskFilePath;
    private final Clock clock;
    private final Scheduler scheduler;
    private final List<Processor> processors;
    private final List<Task> taskList;

    public Simulator(int numberOfProcessors, int totalClockCycles, String taskFilePath) {
        this.totalClockCycles = totalClockCycles;
        this.taskFilePath = taskFilePath;
        this.clock = new Clock();
        this.scheduler = new Scheduler();
        this.processors = new ArrayList<>();
        this.taskList = new ArrayList<>();
        for (int i = 1; i <= numberOfProcessors; i++) {
            processors.add(new Processor("P" + i));
        }

    }

    public void loadTasks() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(taskFilePath));
        String line;

        line = reader.readLine();
        int numberOfTasks = Integer.parseInt(line.trim());

        int taskId = 1;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length != 3) {
                System.err.println("Invalid task format in line: " + line);
                continue;
            }
            try {
                int creationTime = Integer.parseInt(parts[0]);
                int executionTime = Integer.parseInt(parts[1]);
                int priority = Integer.parseInt(parts[2]);
                Task task = new Task("T" + taskId++, creationTime, executionTime, priority);
                taskList.add(task);
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format in line: " + line);
            }
        }
        reader.close();
    }

    public void run() throws InterruptedException, IOException {
        loadTasks();

        for (int cycle = 1; cycle <= totalClockCycles; cycle++) {
            clock.tick();

            System.out.println("----------------------");
            System.out.println("Clock Cycle: " + cycle);

            taskList.removeIf(task -> {
                if (task.getCreationTime() == clock.getCurrentTime()) {
                    scheduler.addTask(task);
                    System.out.println("Task " + task.getTaskId() + " Arrived ");
                    return true;
                }
                return false;
            });

            for (Processor processor : processors) {
                if (processor.isIdle()) {
                    Task task = scheduler.getNextTask();
                    if (task != null) {
                        processor.assignTask(task);
                        System.out.println("Task " + task.getTaskId() + " is Assigned to Processor " + processor.getProcessorId());

                    } else {
                        System.out.println(processor.getProcessorId() + " is idle");
                    }
                } else {
                    System.out.println("Processor " + processor.getProcessorId() + " is executing: " + processor.getCurrentTask().getTaskId());
                }

                Task finishedTask = processor.execute();
                if (finishedTask != null) {
                    System.out.println("Processor " + processor.getProcessorId() + " finished execution on Task " + finishedTask.getTaskId());
                }
            }

            Thread.sleep(1000);
        }
    }
}
