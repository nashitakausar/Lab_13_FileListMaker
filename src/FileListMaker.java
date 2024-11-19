import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class FileListMaker {
    private static boolean needsToBeSaved = false;
    private static List<String> currentList = new ArrayList<>();
    private static String currentFileName = null;

    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("Menu");
            System.out.println("A - Add item");
            System.out.println("D - Delete item");
            System.out.println("I - Insert item");
            System.out.println("M - Move item");
            System.out.println ("V - View entire list");
            System.out.println ("C - Clear entire list");
            System.out.println ("O - Open a file");
            System.out.println ("S - Save a file");
            System.out.println ("Q - Quit");
            System.out.print ("Please enter your choice? ");
            choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "A" -> addItem(scanner);
                case "D" -> deleteItem(scanner);
                case "I" -> insertItem(scanner);
                case "M" -> moveItem(scanner);
                case "V" -> viewList();
                case "C" -> clearList();
                case "O" -> openFile(scanner);
                case "S" -> saveFile();
                case "Q" -> quitProgram(scanner);
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("Q"));
    }

    private static void addItem (Scanner scanner) {
        System.out.print ("Please enter the item to add: ");
        currentList.add(scanner.nextLine());
        needsToBeSaved = true;
    }

    private static void deleteItem (Scanner scanner) {
        viewList();
        System.out.print("Please enter index of item to delete: ");
        int index = Integer.parseInt(scanner.nextLine());
        if (index >= 0 && index < currentList.size()) {
            currentList.remove(index);
            needsToBeSaved = true;
        } else {
            System.out.println ("You entered an invalid index.");
        }
    }

    private static void moveItem (Scanner scanner) {
        viewList();
        System.out.print("Please enter the index of the item to move from: ");
        int fromIndex = Integer.parseInt (scanner.nextLine());
        int toIndex = Integer.parseInt(scanner.nextLine());
        if (fromIndex >= 0 && fromIndex < currentList.size() && toIndex >= 0 && toIndex <= currentList.size()) {
            String item = currentList.remove(fromIndex);
            currentList.add(toIndex, item);
            needsToBeSaved = true;
        } else {
            System.out.println("Invalid indices.");
        }
    }
    private static void insertItem(Scanner scanner) {
        System.out.print("Enter index to insert at: ");
        int index = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter item to insert: ");
        String item = scanner.nextLine();
        if (index >= 0 && index <= currentList.size()) {
            currentList.add(index, item);
            needsToBeSaved = true;
        } else {
            System.out.println("Invalid index.");
        }
    }

    private static void viewList() {
        System.out.println("Current List:");
        for (int i = 0; i < currentList.size(); i++) {
            System.out.println(i + ": " + currentList.get(i));
        }
    }

    private static void clearList() {
        currentList.clear();
        needsToBeSaved = true;
    }

    private static void openFile(Scanner scanner) {
        if (needsToBeSaved) {
            System.out.print("Unsaved changes. Do you want to save before opening a new file? (Y/N): ");
            if (scanner.nextLine().equalsIgnoreCase("Y")) {
                saveFile();
            }
        }
        System.out.print("Enter file name to open: ");
        String filename = scanner.nextLine();
        try {
            currentList = Files.readAllLines(Paths.get(filename));
            currentFileName = filename;
            needsToBeSaved = false;
            System.out.println("File loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    private static void saveFile() {
        if (currentFileName == null) {
            System.out.print("Enter file name to save as: ");
            Scanner scanner = new Scanner(System.in);
            currentFileName = scanner.nextLine() + ".txt";
        }
        try {
            Files.write(Paths.get(currentFileName), currentList);
            needsToBeSaved = false;
            System.out.println("File saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private static void quitProgram(Scanner scanner) {
        if (needsToBeSaved) {
            System.out.print("Unsaved changes. Would you like to save before quitting? (Y/N): ");
            if (scanner.nextLine().equalsIgnoreCase("Y")) {
                saveFile();
            }
        }
        System.out.println("Goodbye!");
    }
}

