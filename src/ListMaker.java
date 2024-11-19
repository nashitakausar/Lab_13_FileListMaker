import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListMaker {
    private static List<String> list = new ArrayList<>();
    private static boolean needsToBeSaved = false;
    private static String currentFileName = null;

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String choice;

        do {
            displayMenu();
            choice = SafeInput.getRegExString(console, "Enter your choice: ", "[AaDdIiMmVvCcOoSsQq]").toUpperCase();

            switch (choice) {
                case "A":
                    addItem(console);
                    break;
                case "D":
                    deleteItem(console);
                    break;
                case "I":
                    insertItem(console);
                    break;
                case "M":
                    moveItem(console);
                    break;
                case "V":
                    viewList();
                    break;
                case "C":
                    clearList(console);
                    break;
                case "O":
                    openFile(console);
                    break;
                case "S":
                    saveFile(console);
                    break;
                case "Q":
                    quitProgram(console);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (true);
    }

    private static void displayMenu() {
        System.out.println("\nCurrent List:");
        viewList();
        System.out.println("\nMenu:");
        System.out.println("A - Add item");
        System.out.println("D - Delete item");
        System.out.println("I - Insert item");
        System.out.println("M - Move item");
        System.out.println("V - View list");
        System.out.println("C - Clear list");
        System.out.println("O - Open list from file");
        System.out.println("S - Save list to file");
        System.out.println("Q - Quit program");
    }

    private static void addItem(Scanner console) {
        String item = SafeInput.getNonZeroLenString(console, "Item to add?");
        list.add(item);
        needsToBeSaved = true;
        System.out.println("Item added.");
    }

    private static void deleteItem(Scanner console) {
        if (list.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }
        viewList();
        int itemNumber = SafeInput.getRangedInt(console, "Enter item number to delete: ", 1, list.size()) - 1;
        String removedItem = list.remove(itemNumber);
        needsToBeSaved = true;
        System.out.println("Item '" + removedItem + "' deleted.");
    }

    private static void insertItem(Scanner console) {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Adding item at the beginning.");
        }
        int position = SafeInput.getRangedInt(console, "Position to insert at: ", 1, list.size() + 1) - 1;
        String item = SafeInput.getNonZeroLenString(console, "Enter item to insert: ");
        list.add(position, item);
        needsToBeSaved = true;
        System.out.println("Item inserted at position " + (position + 1) + ".");
    }

    private static void moveItem(Scanner console) {
        if (list.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }
        viewList();
        int fromIndex = SafeInput.getRangedInt(console, "Enter item number to move: ", 1, list.size()) - 1;
        int toIndex = SafeInput.getRangedInt(console, "Enter new position: ", 1, list.size() + 1) - 1;
        String item = list.remove(fromIndex);
        list.add(toIndex, item);
        needsToBeSaved = true;
        System.out.println("Item moved to position " + (toIndex + 1) + ".");
    }

    private static void viewList() {
        if (list.isEmpty()) {
            System.out.println("[The list is currently empty]");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ": " + list.get(i));
            }
        }
    }

    private static void clearList(Scanner console) {
        if (SafeInput.getYNConfirm(console, "Are you sure you want to clear the list?")) {
            list.clear();
            needsToBeSaved = true;
            System.out.println("List cleared.");
        }
    }

    private static void openFile(Scanner console) {
        if (needsToBeSaved && SafeInput.getYNConfirm(console, "You have unsaved changes. Save before opening a new file?")) {
            saveFile(console);
        }
        System.out.print("Enter filename to open: ");
        String filename = console.nextLine();
        try {
            list = Files.readAllLines(Paths.get(filename));
            currentFileName = filename;
            needsToBeSaved = false;
            System.out.println("File loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    private static void saveFile(Scanner console) {
        if (currentFileName == null) {
            System.out.print("Enter filename to save as: ");
            currentFileName = console.nextLine() + ".txt";
        }
        try {
            Files.write(Paths.get(currentFileName), list);
            needsToBeSaved = false;
            System.out.println("File saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private static void quitProgram(Scanner console) {
        if (needsToBeSaved && SafeInput.getYNConfirm(console, "You have unsaved changes. Save before quitting?")) {
            saveFile(console);
        }
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
