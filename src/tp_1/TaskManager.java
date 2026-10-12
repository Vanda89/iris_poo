package tp_1;

import java.util.ArrayList;
import java.util.List;

import static tp_1.Priority.fromString;

public class TaskManager {
    private List<Task> tasks;
    private static final int CAPACITY = 15;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public TaskManager(List<Task> tasks) {
        if (tasks == null) {
            throw new IllegalArgumentException("Tasks cannot be null");
        }

        if (tasks.size() > CAPACITY) {
            throw new IllegalArgumentException(
                    "The task list cannot contain more than " + CAPACITY + " tasks"
            );
        }

        this.tasks = tasks;
    }

    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        if (this.tasks.contains(task)) {
            throw new IllegalArgumentException("The task already exists");
        }
        if (this.tasks.size() >= CAPACITY) {
            throw new IllegalArgumentException("The task list is full");
        }

        this.tasks.add(task);
    }

    public void removeTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        if (this.tasks.isEmpty()) {
            throw new IllegalArgumentException("The task list is empty");
        }

        if (!this.tasks.contains(task)) {
            throw new IllegalArgumentException("The task does not exist");
        }
        this.tasks.remove(task);
    }

    public void displayTasks() {
        if (this.tasks.isEmpty()) {
            throw new IllegalArgumentException("The task list is empty");
        }
        for (Task task : this.tasks) {
            System.out.println(task);
        }
    }

    public Task searchByTitle(String title) {
        for (Task task : this.tasks) {
            if (task.getTitle().equals(title)) {
                return task;
            }
        }
        return null;
    }

    public List<Task> searchByPriority(Priority priority) {
        for (Task task : this.tasks) {
            if (task.getPriority() == priority) {
                return tasks;
            }
        }
        return null;
    }

    public int getNumberOfTasks() {
        return this.tasks.size();
    }

    public int getNumberOfTasksByPriority(Priority priority) {
        List<Task> tasksByPriority = this.searchByPriority(priority);

        return tasksByPriority.size();
    }


    public List<Task> getTasks() {
        if (tasks.isEmpty()) {
            throw new IndexOutOfBoundsException("There are no tasks in the task manager");
        }
        return tasks;
    }

    private boolean importTask(String[] taskData) {
        try {
            String name = taskData[0];
            String description = taskData[1];
            String priority = taskData[2];
            Priority priorityEnum = fromString(priority);
            Task task = new Task(name, description, priorityEnum);
            addTask(task);
            return true; // Succès
        } catch (IllegalArgumentException e) { // On capture IllegalArgument
            System.out.println("Tâche non importée : "+e.getMessage());
            return false; // Échec
        } catch (IndexOutOfBoundsException e) {// On capture IndexOutOfBounds
            System.out.println("Tâche non importée : Data mal formatée");
            return false; // Échec
        }
    }

    public void importTasks(String[][] tasks) {
        int validTasks = 0;
        int invalidTasks = 0;

        for (String[] task : tasks) {
            boolean imported = importTask(task);
            if (imported) {
                validTasks++;
            }  else {
                invalidTasks++;
            }
        }
        System.out.println("Imported : " + validTasks + ", failed : " + invalidTasks);
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
