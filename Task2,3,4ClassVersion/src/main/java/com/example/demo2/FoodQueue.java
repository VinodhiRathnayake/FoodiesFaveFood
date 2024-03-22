package com.example.demo2;
import java.util.ArrayList;
import java.util.Scanner;

public class FoodQueue {

    //initializing data
    private static Scanner input = new Scanner(System.in);
    private Customer[] customers;
    private int income;
    private final int burgerPrice = 650;
    private int queueSize;
    private String[] cashier1;
    private String[] cashier2;
    private String[] cashier3;
    private ArrayList<Customer> waitingList;


    //Constructs a FoodQueue object with the specified parameters.
    public FoodQueue(int queueSize, String[] cashier1, String[] cashier2, String[] cashier3,ArrayList<Customer> waitingList) {
        this.customers = new Customer[queueSize];
        this.queueSize = queueSize;
        this.income = 0;
        this.cashier1 = cashier1;
        this.cashier2 = cashier2;
        this.cashier3 = cashier3;
        this.waitingList = waitingList;

    }

    /*Add a customer to the cashier queue
    @param  customersList The list of customers to add
    @return True if the customer was added to the queue, false otherwise*/
    public boolean addToQueue(ArrayList<Customer> customersList) {
        boolean addedToQueue = false;
        for (int i = 0; i < 10; i++) {
            if (i < cashier1.length && cashier1[i].equals("X")) {
                cashier1[i] = customersList.get(customersList.size() - 1).getFullName();
                System.out.println("Added to cashier 1: " + cashier1[i]);
                addedToQueue = true;
                break;
            }

            if (i < cashier2.length && cashier2[i].equals("X")) {
                cashier2[i] = customersList.get(customersList.size() - 1).getFullName();
                System.out.println("Added to cashier 2: " + cashier2[i]);
                addedToQueue = true;
                break;
            }

            if (i < cashier3.length && cashier3[i].equals("X")) {
                cashier3[i] = customersList.get(customersList.size() - 1).getFullName();
                System.out.println("Added to cashier 3: " + cashier3[i]);
                addedToQueue = true;
                break;
            }
        }
        return addedToQueue;
    }

    /*Removes a customer from the specified cashier's queue.
    @param Removes a customer from the specified cashier's queue.
    @param customerPosition the position of the customer in the queue.
    @param waitingList the waiting list of customers.*/
    public void removeFromCashier(String[] selectedCashier,int cashierNum,int customerPosition,ArrayList<Customer> waitingList) {

        if (customerPosition >= 0 && customerPosition < selectedCashier.length) {
            if (!selectedCashier[customerPosition].equals("X")) {
                String customerFullName = selectedCashier[customerPosition];
                for (int i = customerPosition; i < selectedCashier.length - 1; i++) {
                    selectedCashier[i] = selectedCashier[i + 1];

                }
                System.out.println("Customer '" + customerFullName + "' removed from the queue");
                if(waitingList.isEmpty()) {
                    selectedCashier[selectedCashier.length - 1] = "X";
                }
                else{
                    selectedCashier[selectedCashier.length - 1] = waitingList.get(0).getFullName();
                    System.out.println("Customer " + waitingList.get(0).getFullName() + " in waiting list added to queue");
                    waitingList.remove(0);
                }

            } else {
                System.out.println("No customer in the queue");
            }
        }
        else{
            System.out.println("Invalid customer position");
        }
    }


}







