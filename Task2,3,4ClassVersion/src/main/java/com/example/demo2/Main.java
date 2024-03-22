package com.example.demo2;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    //initializing necessary data

    private static Scanner input = new Scanner(System.in);
    private static String[][] queues={
            {"X","X"},
            {"X","X","X"},
            {"X","X","X","X","X"}
    };
    private static String[] cashier1 = queues[0];
    private static String[] cashier2 = queues[1];
    private static String[] cashier3 = queues[2];
    private static int queueSize = 10;
    private static int burgersStock = 50;
    private static int[] income = new int[3];
    private static ArrayList<Customer>waitingList = new ArrayList<Customer>();
    private static ArrayList<Customer> customersList = new ArrayList<Customer>();

    private static FoodQueue foodQueue = new FoodQueue(queueSize, cashier1, cashier2, cashier3,waitingList);


    //check conditions
    public static void main(String[] args) {

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
        System.out.println("110 or IFQ: Print the income of each queue.");
        System.out.println("112 or GUI: GUI");
        System.out.println("999 or EXT: Exit the Program.");
        System.out.println("");
        String task;


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
                    System.out.println("The remaining burgers: " + burgersStock);
                    break;

                case "109":
                case "AFS":
                    addBurgers();
                    break;

                case "110":
                case "IFQ":
                    queueIncome();
                    break;

                case "112":
                case "GUI":
                    GUI();
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

    //Counts the number of empty slots in each cashier and display the results.
    private static void viewEmptyQueues() {
        int emptySlotsCashier1 = 0;
        int emptySlotsCashier2 = 0;
        int emptySlotsCashier3 = 0;
        for (int i = 0; i < cashier1.length; i++) {
            if (cashier1[i].equals("X")) {
                emptySlotsCashier1++;
            }
        }
        for (int i = 0; i < cashier2.length; i++) {
            if (cashier2[i].equals("X")) {
                emptySlotsCashier2++;
            }
        }
        for (int i = 0; i < cashier3.length; i++) {
            if (cashier3[i].equals("X")) {
                emptySlotsCashier3++;
            }
        }
        System.out.println("Empty slots in cashier 1: " + emptySlotsCashier1);
        System.out.println("Empty slots in cashier 2: " + emptySlotsCashier2);
        System.out.println("Empty slots in cashier 3: " + emptySlotsCashier3);
    }




    //display the status of the cashier queues, indicating which slots are occupied and which are not.
    private static void viewQueues() {
        System.out.println("*".repeat(17));
        System.out.println("*    Cashiers   *");
        System.out.println("*".repeat(17));

        for (int i = 0; i < queues[0].length; i++) {
            String cashier1Slot = (i < queues[0].length) ? (queues[0][i].equals("X") ? "X" : "O") : " ";
            String cashier2Slot = (i < queues[1].length) ? (queues[1][i].equals("X") ? "X" : "O") : " ";
            String cashier3Slot = (i < queues[2].length) ? (queues[2][i].equals("X") ? "X" : "O") : " ";

            System.out.println(" " + cashier1Slot + "   " + cashier2Slot + "   " + cashier3Slot);
        }

        for (int j = queues[0].length; j < queues[1].length; j++) {
            String cashier2Slot = (j < queues[1].length) ? (queues[1][j].equals("X") ? "X" : "O") : " ";
            String cashier3Slot = (j < queues[2].length) ? (queues[2][j].equals("X") ? "X" : "O") : " ";

            System.out.println("     " + cashier2Slot + "   " + cashier3Slot);
        }

        for (int k = queues[1].length; k < queues[2].length; k++) {
            String cashier3Slot = (k < queues[2].length) ? (queues[2][k].equals("X") ? "X" : "O") : " ";
            System.out.println("         " + cashier3Slot);
        }

        System.out.println("X - Not Occupied  O - Occupied");
    }

    //add a new customer to the queue
    private static void addCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first name of customer: ");
        String firstName = input.next();
        System.out.print("Enter second name of customer: ");

        try {
            String secondName = input.next();
            System.out.print("Enter required burgers: ");
            int requiredBurgers = input.nextInt();
            Customer customer = new Customer(firstName, secondName, requiredBurgers);

            customersList.add(customer);

            //add to waiting list if queues are full

            if (!foodQueue.addToQueue(customersList)) {
                System.out.println("All food queues are full. Customer added to waiting list.");
                waitingList.add(customer);
                System.out.println("Waiting List: ");
                for (Customer waitingCustomer : waitingList) {
                    System.out.print(waitingCustomer.getFullName() + ", ");
                }
                System.out.println();

            }
        }
        catch (Exception e){
            System.out.println("Invalid input");
            System.out.println("Customer not added to queue");
        }
        }

    /*remove a customer from a specific cashier queue.
    @param foodQueue an instance of the FoodQueue class, which provides methods to remove customers from cashiers' queues.
    waitingList holds customers on the waiting list.*/
    private static void removeCustomer() {
        System.out.print("Enter cashier number of the customer to be removed: ");
        String cashierInput = input.next();
        int cashierNum;
        try {
            cashierNum = Integer.parseInt(cashierInput);
            if (cashierNum >= 1 && cashierNum <= 3) {
                System.out.print("Enter the position of the customer to remove: ");
                int customerPosition;
                try {
                    customerPosition = input.nextInt();
                    customerPosition--;

                    String[] selectedCashier;
                    if (cashierNum == 1) {
                        selectedCashier = cashier1;
                    } else if (cashierNum == 2) {
                        selectedCashier = cashier2;
                    } else {
                        selectedCashier = cashier3;
                    }

                    //remove from cashier when served and adding customer from waiting list if any
                    foodQueue.removeFromCashier(selectedCashier, cashierNum, customerPosition, waitingList);
                    System.out.println("Waiting List: ");
                    for (Customer waitingCustomer : waitingList) {
                        System.out.print(waitingCustomer.getFullName() + ", ");
                        System.out.println("");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid customer position.");
                    input.nextLine();
                }
            } else {
                System.out.println("Invalid cashier number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
    }

    /*remove a served customer from a specific cashier queue.
    @param foodQueue an instance of the FoodQueue class, which provides methods to remove customers from cashiers' queues.
    waitingList holds customers on the waiting list.
    burgersStock  represent the stock of burgers available.*/
    private static void removeServedCustomer() {
        System.out.print("Enter cashier number of the customer to be served: ");
        try {
            int cashierNum = input.nextInt();
            if (cashierNum >= 1 && cashierNum <= 3) {
                String[] selectedCashier;
                int incomeIndex;

                if (cashierNum == 1) {
                    selectedCashier = cashier1;
                    incomeIndex = 0;
                } else if (cashierNum == 2) {
                    selectedCashier = cashier2;
                    incomeIndex = 1;
                } else {
                    selectedCashier = cashier3;
                    incomeIndex = 2;
                }

                if (selectedCashier[0] != null) {
                    String customerName = selectedCashier[0];
                    Customer customer = findCustomerByName(customersList, customerName);

                    //calculate income and reduce the burger count
                    if (customer != null) {
                        int requiredBurgers = customer.getRequiredBurgers();
                        if (requiredBurgers <= burgersStock) {
                            burgersStock -= requiredBurgers;
                            if (income == null) {
                                income = new int[3];
                            }
                            income[incomeIndex] += (650 * requiredBurgers);
                            System.out.println("Income from customer: " + 650 * requiredBurgers);
                            foodQueue.removeFromCashier(selectedCashier, cashierNum, 0, waitingList);
                        } else {
                            System.out.println("Not enough burgers in stock to serve the customer.Please add burgers to stock");
                        }
                    } else {
                        System.out.println("Customer not found.");
                    }
                } else {
                    System.out.println("No customer in the specified cashier.");
                }

                //print waiting list
                System.out.println("Waiting List: ");
                for (Customer waitingCustomer : waitingList) {
                    System.out.print(waitingCustomer.getFullName() + ", ");
                    System.out.println("");
                }
            } else {
                System.out.println("Invalid cashier number.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid cashier number.");
            input.nextLine();
        }
    }


    /*search for a customer in a list of customers by their full name.
      @param customersList represents the list of customers to search through.
       customerName represent the full name of the customer to find.
       @return  a Customer object if a customer with the specified name is found in the customersList.
       If no matching customer is found, the method returns null.*/
    public static Customer findCustomerByName(ArrayList<Customer> customersList, String customerName) {
        for (Customer customer : customersList) {
            if (customer.getFullName().equals(customerName)) {
                return customer;
            }
        }
        return null;
    }

    //sort the customersList in alphabetical order
    private static void sort () {
        int n = customersList.size();
        int bottom = n - 2;
        boolean exchanged = true;

        while (exchanged) {
            exchanged = false;

            for (int i = 0; i <= bottom; i++) {
                String name1 = customersList.get(i).getFullName();
                String name2 = customersList.get(i + 1).getFullName();

                if (name1.compareTo(name2) > 0) {
                    Customer temp = customersList.get(i);
                    customersList.set(i, customersList.get(i + 1));
                    customersList.set(i + 1, temp);
                    exchanged = true;
                }
            }

            bottom--;
        }

        System.out.println("Customers sorted in alphabetical order:");
        for (Customer customer : customersList) {
            System.out.println(customer.getFullName());
        }
    }

    //store the customer data, remaining burger stock, and cashier incomes into a text file.
    private static void storeData () {

        //creating a file
        try {
            File file = new File("text.txt");
            boolean fileExists = file.exists();

            // Check for a file with the same name
            if (fileExists) {
                boolean deleted = file.delete();
                if (deleted) {
                    System.out.println("Previous file deleted: " + file.getName());
                } else {
                    System.out.println("Failed to delete previous file: " + file.getName());
                }
            }
            boolean fileCreated = file.createNewFile();

            // Store data into file
            if (fileCreated) {
                System.out.println("File created: " + file.getName());

                FileWriter fileWriter = new FileWriter("text.txt");
                PrintWriter printWriter = new PrintWriter(fileWriter);

                printWriter.println("Customer names and required burgers:");

                for (Customer customer : customersList) {
                    printWriter.println(customer.getFullName() + ": " + customer.getRequiredBurgers() + " burgers");
                }

                printWriter.println();
                printWriter.println("Amount of remaining burgers: " + burgersStock);
                printWriter.println("Income of Cashier 1: " + income[0]);
                printWriter.println("Income of Cashier 2: " + income[1]);
                printWriter.println("Income of Cashier 3: " + income[2]);

                printWriter.close();
                System.out.println("Successfully wrote to the file.");
            } else {
                System.out.println("Error while creating file: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    //load program data from a file
    private static void loadData () {
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




    //add burgers to the remaining stock of burgers.
    private static void addBurgers () {
        System.out.println("The remaining burgers: " + burgersStock);
        System.out.print("Enter the number of burgers to be added: ");
        try {
            int burgersAdded = input.nextInt();
            burgersStock += burgersAdded;
        }
        catch(Exception e){
            System.out.println("Invalid input. Please enter a valid number of burgers.");
            input.nextLine();
        }
    }

    //to display the income of each cashier.
    private static void queueIncome () {
        System.out.println("Income of Cashier 1: " + income[0]);
        System.out.println("Income of Cashier 2: " + income[1]);
        System.out.println("Income of Cashier 3: " + income[2]);
    }

    private static void GUI () {
        try {
            // Load the FXML file
            Parent root = FXMLLoader.load(Main.class.getResource("hello-view.fxml"));


            // Create a new stage and set the FXML scene
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("FXML Example");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}