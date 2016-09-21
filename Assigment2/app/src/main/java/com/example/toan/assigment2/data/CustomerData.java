package com.example.toan.assigment2.data;

import com.example.toan.assigment2.model.Customer;

import java.util.ArrayList;

/**
 * Created by toan on 16-Sep-16.
 */
public class CustomerData {

    public ArrayList<Customer> getListCustomer() {
        ArrayList<Customer> customers = new ArrayList<>();
        Customer cus;
        cus = new Customer("1", "Brooklyn", 30, "brooklyn@gmail.com", "England", "1234567890", 20);
        customers.add(cus);
        cus = new Customer("2", "Brooklyn", 30, "brooklyn@gmail.com", "England", "1234567890", 30);
        customers.add(cus);
        cus = new Customer("3", "Brooklyn", 30, "brooklyn@gmail.com", "England", "1234567890", 40);
        customers.add(cus);
        cus = new Customer("4", "Brooklyn", 30, "brooklyn@gmail.com", "England", "1234567890", 50);
        customers.add(cus);
        cus = new Customer("5", "Brooklyn", 30, "brooklyn@gmail.com", "England", "1234567890", 60);
        customers.add(cus);
        cus = new Customer("6", "Brooklyn", 30, "brooklyn@gmail.com", "England", "1234567890", 70);
        customers.add(cus);
        cus = new Customer("7", "Brooklyn", 30, "brooklyn@gmail.com", "England", "1234567890", 80);
        customers.add(cus);
        return customers;
    }
}
