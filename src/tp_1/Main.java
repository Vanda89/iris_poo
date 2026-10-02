package tp_1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var p0 = Priority.HIGH;
        var p1 = Priority.MEDIUM;
        var p2 = Priority.LOW;
        var p3 = Priority.HIGH;
        // var p4 = Priority.VERY_HIGH;
        System.out.println(p0);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p0.equals(p1));
        System.out.println(p0.equals(p3));
        System.out.println();
        // Test with a bad argument
        //Task invalidTask1 = new Task("invalidTask1 1", "This is the invalidTask1", badPriority);

        try {
            Priority badPriority = Priority.valueOf("VERY_HIGH");
            Task invalidTask1 = new Task("invalidTask1 1", "This is the invalidTask1", badPriority);
        } catch (IllegalArgumentException exception) {
            System.out.println("This priority is not valid");
        }
        // Test with a null value to reach the constructor checks
        try {
            Task invalidTask2 = new Task("invalidTask2 2", "This is the invalidTask2", null);
        } catch (IllegalArgumentException exception) {
            System.out.println("Error : " + exception.getMessage());
        }
        // Test with a null value to reach the constructor checks
        try {
            Task invalidTask3 = new Task("", "This is the invalidTask3", Priority.HIGH);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println();
        Task task1 = new Task("task 1", "This is the first task", Priority.MEDIUM);
        Task task2 = new Task("task 2", "This is the second task", Priority.HIGH);
        Task task3 = new Task("task 3", "This is the third task", Priority.LOW);
        Task task4 = new Task("task 4", "This is the fourth task", Priority.HIGH);
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.addTask(task4);
        taskManager.removeTask(task1);
        taskManager.displayTasks();
        System.out.println(task2.getTitle());

        try {
            System.out.println("Titre 1 " + taskManager.searchByTitle("task 1"));
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println("Title 2 " + taskManager.searchByTitle("task 2"));
        System.out.println("Title 3 " + taskManager.searchByTitle("task 3"));
        System.out.println("Medium priority " + taskManager.searchByPriority(Priority.MEDIUM));
        System.out.println("High priority " + taskManager.searchByPriority(Priority.HIGH));
        System.out.println("Low priority " + taskManager.searchByPriority(Priority.LOW));
        System.out.println("Number of tasks : " + taskManager.getNumberOfTasks());
        System.out.println();

        /* Test avec importTask en public pour vérifier comportement avant le 1.5
        String[][] taskList = TaskTestData.getValidTasks();
        for (String[] task : taskList) {
            boolean imported = taskManager.importTask(task);
            System.out.println(imported);
        }
        System.out.println();
        String[][] taskList2 = TaskTestData.getProblematicTasks();
        for (String[] task : taskList2) {
            boolean imported = taskManager.importTask(task);
            System.out.println("Status of the import : " + imported);
        }*/

        System.out.println("=== Test données valides ===");
        taskManager.importTasks(TaskTestData.getValidTasks());
        System.out.println("=== Test données problématiques ===");
        taskManager.importTasks(TaskTestData.getProblematicTasks());
        System.out.println("=== Test grande liste ===");
        taskManager.importTasks(TaskTestData.getLargeTaskSet());







    }
}
