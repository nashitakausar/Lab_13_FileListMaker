import java.util.Scanner;

public class SafeInput {

    /**
     * Method to get a non-zero length string from the user.
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);
        return retString;
    }

    /**
     * Method to get a valid integer from the user.
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a valid integer entered by the user
     */
    public static int getInt(Scanner pipe, String prompt) {
        int retVal = 0;
        boolean valid = false;

        do {
            System.out.print(prompt + ": ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                pipe.nextLine();
            }
        } while (!valid);

        return retVal;
    }

    /**
     * Method to get a valid double from the user.
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a valid double entered by the user
     */
    public static double getDouble(Scanner pipe, String prompt) {
        double retVal = 0.0;
        boolean valid = false;

        do {
            System.out.print(prompt + ": ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter a valid double.");
                pipe.nextLine();
            }
        } while (!valid);

        return retVal;
    }

    /**
     * Method to get a valid integer from the user within a specified inclusive range.
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param low    the low end of the range (inclusive)
     * @param high   the high end of the range (inclusive)
     * @return a valid integer within the range entered by the user
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int retVal = 0;
        boolean valid = false;

        do {
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                if (retVal >= low && retVal <= high) {
                    valid = true;
                } else {
                    System.out.println("Input out of range. Please enter a number between " + low + " and " + high + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                pipe.nextLine();
            }
        } while (!valid);

        return retVal;
    }

    /**
     * Method to get a valid double from the user within a specified inclusive range.
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param low    the low end of the range (inclusive)
     * @param high   the high end of the range (inclusive)
     * @return a valid double within the range entered by the user
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double retVal = 0.0;
        boolean valid = false;

        do {
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                if (retVal >= low && retVal <= high) {
                    valid = true;
                } else {
                    System.out.println("Input out of range. Please enter a number between " + low + " and " + high + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid double.");
                pipe.nextLine();
            }
        } while (!valid);

        return retVal;
    }

    /**
     * Method to get a Yes or No (Y/N) confirmation from the user.
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return true for Yes (Y/y) and false for No (N/n)
     */
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String input;
        boolean valid = false;
        boolean result = false;

        do {
            System.out.print(prompt + " [Y/N]: ");
            input = pipe.nextLine().trim().toUpperCase();

            if (input.equals("Y")) {
                result = true;
                valid = true;
            } else if (input.equals("N")) {
                result = false;
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter 'Y' or 'N'.");
            }
        } while (!valid);

        return result;
    }

    /**
     * Method to get a string that matches a given RegEx pattern from the user.
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param regEx  the regular expression pattern the input must match
     * @return a string that matches the provided RegEx pattern
     */
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String input;
        boolean valid = false;

        do {
            System.out.print(prompt + ": ");
            input = pipe.nextLine();

            if (input.matches(regEx)) {
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter a value that matches the pattern.");
            }
        } while (!valid);

        return input;
    }

    /**
     * Method to create a pretty header.
     * Displays a message centered between two lines of asterisks.
     *
     * @param msg the message to be centered
     */
    public static void prettyHeader(String msg) {
        int width = 60;

        for (int i = 0; i < width; i++) {
            System.out.print("*");
        }
        System.out.println();


        int messageLength = msg.length();
        int padding = (width - messageLength - 6) / 2;

        System.out.print("***");
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }

        if (messageLength % 2 == 1) {
            System.out.print(" ");
        }
        System.out.println("***");

        for (int i = 0; i < width; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}
