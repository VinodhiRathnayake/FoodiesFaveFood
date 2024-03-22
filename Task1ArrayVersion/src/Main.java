import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    //Initializing arrays and burgher stock
    private static String[] cashier_1 = {"X","X"};
    private static String[] cashier_2 = {"X","X","X"};
    private static String[] cashier_3 = {"X","X","X","X","X"};
    private static String[] customerNames = new String[10];
    private static Scanner input = new Scanner(System.in);
    private static int burgersStock = 50;

    public static void main(String[] args) {
        //printing task list

        System.out.println("100 or VFQ: View all Queues.");
        System.out.println("101 or VEQ: View all Empty Queues.");
        System.out.println("102 or ACQ: Add customer to a Queue.");
        System.out.println("103 or RCQ: Remove a customer from a Queue.");
        System.out.println("104 or PCQ: Remove a served customer.");
        System.out.println("105 or VCS: View Customers Sorted in alphabetical order.");
        System.out.println("106 or SPD: Store Program Data into file.");
        System.out.println("107 or LPD: Load Program Data from file.");
        System.out.println("108 or STK: View Remaining burgers Stock.");
        System.out.println("109 or AFS: Add burgers to Stock.");
        System.out.println("999 or EXT: Exit the Program.");
        System.out.println("");
        String task;
        Scanner input = new Scanner(System.in);

        //Do-while loop to check the inputs

        do {
            System.out.print("Enter the task required: ");
            task = input.next();
            task = task.toUpperCase();

            switch (task) {
                case "100":
                case "VFQ":
                    viewQueues();
                    break;

                case "101":
                case "VEQ":
                    viewEmptyQueues();
                    break;

                case "102":
                case "ACQ":
                    addCustomer();
                    break;

                case "103":
                case "RCQ":
                    removeCustomer();
                    break;
                case "104":
                case "PCQ":
                    removeServedCustomer();
                    break;

                case "105":
                case "VCS":
                    sort();
                    break;

                case "106":
                case "SPD":
                    storeData();
                    break;

                case "107":
                case "LPD":
                    loadData();
                    break;

                case "108":
                case "STK":
                    //Print remaining burgers stock
                    System.out.println("The remaining burgers: " + burgersStock);
                    break;

                case "109":
                case "AFS":
                    addBurgers();
                    break;

                case "999":
                case "EXT":
                    System.out.println("Thank you");
                    break;

                default:
                    System.out.println("Incorrect code");
            }
        } while (!task.equals("999") && !task.equals("EXT"));
    }

    //add a new customer to the queue
        private static void addCustomer() {
        int count = 0;
        System.out.print("Enter the name of the customer to add: ");
        String customer = input.next();

        System.out.print("Enter cashier number to join queue: ");
        if (input.hasNextInt()) {
            int cashierNum = input.nextInt();

            // Add customer names to array
            for (int i = 0; i < customerNames.length; i++) {
                if (customerNames[i] == null) {
                    customerNames[i] = customer;
                    break;
                }
            }
            //Add customers to relevant queue
            if (cashierNum == 1) {
                for (int i = 0; i < 10; i++) {
                    if (i < cashier_1.length && cashier_1[i].equals("X")) {
                        cashier_1[i] = "O";
                        System.out.println("Customer added to queue 1");
                        break;
                    }
                    count += 1;
                    if (count == 5) {
                        System.out.println("No more can be added");
                        break;
                    }
                }
            } else if (cashierNum == 2) {
                for (int i = 0; i < 10; i++) {
                    if (i < cashier_2.length && cashier_2[i].equals("X")) {
                        cashier_2[i] = "O";
                        System.out.println("Customer added to queue 2");
                        break;
                    }
                    count += 1;
                    if (count == 5) {
                        System.out.println("No more can be added");
                        break;
                    }
                }
            } else if (cashierNum == 3) {
                for (int i = 0; i < 10; i++) {
                    if (i < cashier_3.length && cashier_3[i].equals("X")) {
                        cashier_3[i] = "O";
                        System.out.println("Customer added to queue 3");
                        break;
                    }
                    count += 1;
                    if (count == 5) {
                        System.out.println("No more can be added");
                        break;
                    }
                }
            }
            else{
                System.out.println("Incorrect cashier number");
            }
        }

        else{
            System.out.println("Invalid input");
            input.next();
        }
    }

    //Method to remove a customer from a specific cashier queue
    private static void removeCustomer(){
        System.out.print("Enter cashier number: ");
        if (input.hasNextInt()) {
            int cashierNum = input.nextInt();
            //check cashier number and location to remove the customer
            if (cashierNum == 1) {
                System.out.print("Enter the position of customer to remove: ");
                if (input.hasNextInt()) {
                    int customerPosition = input.nextInt();
                    customerPosition--;
                    if (customerPosition >= 0 && customerPosition < cashier_1.length) {
                        if (cashier_1[customerPosition].equals("O")) {
                            for (int i = customerPosition; i < cashier_1.length - 1; i++) {
                                cashier_1[i] = cashier_1[i + 1];
                            }
                            cashier_1[cashier_1.length - 1] = "X";
                            System.out.println("Customer removed from the queue");
                        } else {
                            System.out.println("No customer in the queue");
                        }
                    }
                    else{
                        System.out.println("Invalid customer position");
                    }
                }
                else {
                    System.out.println("Error: Invalid customer position.");
                    input.next();
                }
            }
            else if (cashierNum == 2) {
                System.out.print("Enter the position of customer to remove: ");
                if (input.hasNextInt()) {
                    int customerPosition = input.nextInt();
                    customerPosition--;
                    if (customerPosition >= 0 && customerPosition < cashier_2.length) {
                        if (cashier_2[customerPosition].equals("O")) {
                            for (int i = customerPosition; i < cashier_2.length - 1; i++) {
                                cashier_2[i] = cashier_2[i + 1];
                            }
                            cashier_2[cashier_2.length - 1] = "X";
                            System.out.println("Customer removed from the queue");
                        } else {
                            System.out.println("No customer in the queue");
                        }
                    }
                    else{
                        System.out.println("Invalid customer position");
                    }
                }
                else {
                    System.out.println("Error: Invalid customer position.");
                    input.next();
                }
            }
            else if (cashierNum == 3) {
                System.out.print("Enter the position of customer to remove: ");
                if (input.hasNextInt()) {
                    int customerPosition = input.nextInt();
                    customerPosition--;
                    if (customerPosition >= 0 && customerPosition < cashier_3.length) {
                        if (cashier_3[customerPosition].equals("O")) {
                            for (int i = customerPosition; i < cashier_3.length - 1; i++) {
                                cashier_3[i] = cashier_3[i + 1];
                            }
                            cashier_3[cashier_3.length - 1] = "X";
                            System.out.println("Customer removed from the queue");
                        } else {
                            System.out.println("No customer in the queue");
                        }
                    }
                    else{
                        System.out.println("Invalid customer position");
                    }
                }
                else {
                    System.out.println("Error: Invalid customer position.");
                    input.next();
                }
            }
            else {
                System.out.println("Error: Invalid cashier number.");
            }
        }
        else{
            System.out.println("Invalid input");
            input.next();
        }
    }

    //display the status of the cashier queues, indicating which slots are occupied and which are not.
    private static void viewQueues(){
        System.out.println("*".repeat(17));
        System.out.println("*    Cashiers   *");
        System.out.println("*".repeat(17));
        System.out.println(" ".repeat(4) + cashier_1[0] + " ".repeat(4) + cashier_2[0] + " ".repeat(4) + cashier_3[0]);
        System.out.println(" ".repeat(4) + cashier_1[1] + " ".repeat(4) + cashier_2[1] + " ".repeat(4) + cashier_3[1]);
        System.out.println(" ".repeat(4) + " " + " ".repeat(4) + cashier_2[2] + " ".repeat(4) + cashier_3[2]);
        System.out.println(" ".repeat(4) + " " + " ".repeat(4)  + " " + " ".repeat(4) + cashier_3[3]);
        System.out.println(" ".repeat(4) + " " + " ".repeat(4)  + " " + " ".repeat(4) + cashier_3[4]);
        System.out.println("X-Not Occupied  O-Occupied");
    }

    //remove a served customer from a specific cashier queue.
    private static void removeServedCustomer(){
        System.out.print("Enter cashier number to serve customer: ");

        if (input.hasNextInt()) {
            int cashierNumber = input.nextInt();
            boolean customerServed = false;
            if (cashierNumber == 1) {
                for (int i = 0; i < cashier_1.length - 1; i++) {
                    if (cashier_1[i].equals("O")) {
                        cashier_1[i] = cashier_1[i + 1];

                        System.out.println("Customer served");
                        cashier_1[cashier_1.length - 1] = "X";
                        burgersStock -= 5;
                        customerServed = true;
                    }
                }
            }

            else if (cashierNumber == 2) {
                for (int i = 0; i < cashier_2.length - 1; i++) {
                    if (cashier_2[i].equals("O")) {
                        cashier_2[i] = cashier_2[i + 1];

                        System.out.println("Customer served");
                        cashier_2[cashier_2.length - 1] = "X";
                        burgersStock -= 5;
                        customerServed = true;
                    }
                }
            }
            else if (cashierNumber == 3) {
                for (int i = 0; i < cashier_3.length - 1; i++) {
                    if (cashier_3[i].equals("O")) {
                        cashier_3[i] = cashier_3[i + 1];

                        System.out.println("Customer served");
                        cashier_3[cashier_3.length - 1] = "X";
                        burgersStock -= 5;
                        customerServed = true;
                    }
                }
            }

            else {
                System.out.println("Incorrect cashier number");
            }
            if (!customerServed) {
                System.out.println("No customer");
            }


            if (burgersStock == 10) {
                System.out.println("Only 10 burghers remaining.");
            }
        }
        else{
            System.out.println("Invalid input");
            input.next();
        }
    }

    //Method to add burgers to stock
    private static void addBurgers(){
        System.out.println("The remaining burgers: " + burgersStock);
        System.out.print("Enter the number of burgers to be added: ");
        if (input.hasNextInt()) {
            int addedBurgers = input.nextInt();
            burgersStock += addedBurgers;
            System.out.println("The total number of burgers is: " + burgersStock);
        }
        else{
            System.out.println("Error: Invalid input.");
            input.next();
        }

    }

    //Counts the number of empty slots in each cashier and display the results.
    private static void viewEmptyQueues(){
        int emptySlotsCashier1 = 0;
        int emptySlotsCashier2 = 0;
        int emptySlotsCashier3 = 0;
        for (int i = 0; i < cashier_1.length; i++) {
            if (cashier_1[i].equals("X")) {
                emptySlotsCashier1++;
            }
        }
        for (int i = 0; i < cashier_2.length; i++) {
            if (cashier_2[i].equals("X")) {
                emptySlotsCashier2++;
            }
        }
        for (int i = 0; i < cashier_3.length; i++) {
            if (cashier_3[i].equals("X")) {
                emptySlotsCashier3++;
            }
        }
        System.out.println("Empty slots in cashier 1: " + emptySlotsCashier1);
        System.out.println("Empty slots in cashier 2: " + emptySlotsCashier2);
        System.out.println("Empty slots in cashier 3: " + emptySlotsCashier3);
    }

    //Method to view customers sorted in alphabetical order
    private static void sort() {
        // Create a new array for sorted customer names
        String[] sortedNames = new String[customerNames.length];

        // Copy customer names to sortedNames array
        for (int i = 0; i < customerNames.length; i++) {
            sortedNames[i] = customerNames[i];
        }

        // Sort the sortedNames array
        int n = sortedNames.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compareStrings(sortedNames[j], sortedNames[j + 1]) > 0) {
                    String temp = sortedNames[j];
                    sortedNames[j] = sortedNames[j + 1];
                    sortedNames[j + 1] = temp;
                }
            }
        }

        // Print the original customer names
        System.out.print("Customer names: ");
        for (String name : customerNames) {
            if (name != null) {
                System.out.print(name + ", ");
            }
        }
        System.out.println();

        // Print the sorted customer names
        System.out.print("Sorted customer names: ");
        for (String name : sortedNames) {
            if (name != null) {
                System.out.print(name + ", ");
            }
        }
        System.out.println();
    }

    private static int compareStrings(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return 0;
        } else if (str1 == null) {
            return -1;
        } else if (str2 == null) {
            return 1;
        }

        String s1 = str1.toLowerCase();
        String s2 = str2.toLowerCase();

        int len1 = s1.length();
        int len2 = s2.length();
        int minLength = Math.min(len1, len2);

        for (int i = 0; i < minLength; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 != c2) {
                return c1 - c2;
            }
        }

        return len1 - len2;
    }

    //Method to store program data into a file
    private static void storeData(){
        try {
            File file = new File("text.txt");
            boolean fileExists = file.exists();

            //check for a file with same name
            if (fileExists) {
                boolean deleted = file.delete();
                if (deleted) {
                    System.out.println("Previous file deleted: " + file.getName());
                } else {
                    System.out.println("Failed to delete previous file: " + file.getName());
                }
            }
            boolean fileCreated = file.createNewFile();

            //store data into file
            if (fileCreated) {
                System.out.println("File created: " + file.getName());

                FileWriter fileWriter = new FileWriter("text.txt");
                PrintWriter printWriter = new PrintWriter(fileWriter);

                printWriter.print("Customer names: ");
                for(int i = 0; i < customerNames.length;i++){
                    if (customerNames[i] != null) {
                        printWriter.print(customerNames[i] + ", ");
                    }
                    else{
                        printWriter.print(" ");
                    }

                }
                printWriter.println();
                printWriter.print("Amount of remaining burghers: " + burgersStock);


                printWriter.close();
                System.out.println("Successfully wrote to the file.");
            }
            else{
                System.out.println("Error while creating file: " + file.getName());
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    //Method to load program data from a file
    private static void loadData() {
        //read and print the program data from file
        try {
            File file = new File("text.txt");
            Scanner file_reader = new Scanner(file);
            while (file_reader.hasNextLine()) {
                String text = file_reader.nextLine();
                System.out.println(text);
            }
            file_reader.close();
        } catch (IOException e) {
            System.out.println("Error while reading a file.");
            e.printStackTrace();
        }
    }

}