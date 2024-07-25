import java.util.Scanner;

class Task {
    String description;
    int priority;
    boolean completed;

    public Task(String description, int priority) {
        this.description = description;
        this.priority = priority;
        this.completed = false;
    }

    // Getters and setters
}

class Node {
    Task task;
    Node next;

    public Node(Task task) {
        this.task = task;
    }
}

class LinkedList {
    Node head;

    public void addTask(Task task) {
        Node newNode = new Node(task);
        newNode.next = head;
        head = newNode;
    }

    public void viewTasks() {
        Node current = head;
        while (current != null) {
            System.out.println("[" + (current.task.completed ? "yes" : "no") + "] " + current.task.description + " (Priority: " + current.task.priority + ")");
            current = current.next;
        }
    }

    public void sortTasksByPriority() {
        // Bubble sort implementation for simplicity
        Node current;
        Node next;
        for (int i = 0; i < size(); i++) {
            current = head;
            next = head.next;
            for (int j = 0; j < size() - i - 1; j++) {
                if (current.task.priority > next.task.priority) {
                    Task temp = current.task;
                    current.task = next.task;
                    next.task = temp;
                }
                current = current.next;
                next = next.next;
            }
        }
        System.out.println("Tasks sorted by priority!");
    }

    public void markTaskAsCompleted(int index) {
        Node current = head;
        for (int i = 0; i < index && current != null; i++) {
            current = current.next;
        }
        if (current != null) {
            current.task.completed = true;
            System.out.println("Task marked as completed!");
        } else {
            System.out.println("Invalid task index.");
        }
    }

   public void deleteTask(int index) {
    if (head == null) {
        System.out.println("Task list is empty.");
        return;
    }

    if (index < 0 || index >= size()) { // Added check for invalid index
        System.out.println("Invalid task index.");
        return;
    }

    if (index == 0) {
        head = head.next;
        System.out.println("Task deleted successfully!");
        return;
    }

    Node current = head;
    for (int i = 0; i < index - 1; i++) { // Iterate up to index - 1
        current = current.next;
    }

    if (current.next != null) {
        current.next = current.next.next; // Skip the node to be deleted
        System.out.println("Task deleted successfully!");
    } else {
        System.out.println("Invalid task index."); // Handle edge case of invalid index
    }
}


    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}

public class ToDoList {
    private LinkedList tasks = new LinkedList();

    public static void main(String[] args) {
        ToDoList app = new ToDoList();
        Scanner scanner = new Scanner(System.in);
  
        while (true) {
System.out.println("\nTo-Do List Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Sort Tasks by Priority");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. Delete Task");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    scanner.nextLine();
                    System.out.print("Enter task priority (1-5): ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid priority. Please enter a number between 1 and 5.");
                        scanner.next(); // Consume invalid input
                        System.out.print("Enter task priority (1-5): ");
                    }
                    int priority = scanner.nextInt();
                   
                    scanner.nextLine(); // Consume newline
                    app.tasks.addTask(new Task(description, priority));
                    System.out.println("Task added successfully!");
                    break;
                case 2:
                    app.tasks.viewTasks();
                    break;
               
                case 3: // Sort tasks by priority
                    app.tasks.sortTasksByPriority();
                    break;
               
              case 4: 
                    System.out.print("Enter task index to mark as completed: ");
                    int index = scanner.nextInt();
                    app.tasks.markTaskAsCompleted(index);
                    break;
                case 5: // Delete task
                    System.out.print("Enter task index to delete: ");
                    index = scanner.nextInt();
                    app.tasks.deleteTask(index);
                    break;
                case 6: // Exit
                    System.out.println("Exiting To-Do List.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
