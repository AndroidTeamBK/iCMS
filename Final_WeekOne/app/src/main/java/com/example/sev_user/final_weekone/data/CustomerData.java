package com.example.sev_user.final_weekone.data;

import com.example.sev_user.final_weekone.DataHolder;
import com.example.sev_user.final_weekone.model.Customer;

import java.util.ArrayList;

/**
 * Created by toan on 16-Sep-16.
 */
public class CustomerData {
    Customer cus;

    public ArrayList<Customer> getListCustomer() {
        //DataHolder.clearDataCustomer();
        cus = new Customer(1,"Brooklyn",30,"brooklyn@gmail.com","England","1234567890",20);
        //customers.add(cus);
        DataHolder.addCustomer(cus);
        cus = new Customer(2,"Lazar",30,"brooklyn@gmail.com","England","1234567890",30);
        DataHolder.addCustomer(cus);
        cus = new Customer(3,"Fuck",30,"brooklyn@gmail.com","England","1234567890",40);
        DataHolder.addCustomer(cus);
        cus = new Customer(4,"Bach",30,"brooklyn@gmail.com","England","1234567890",50);
        DataHolder.addCustomer(cus);
        cus = new Customer(5,"Brooklyn",30,"brooklyn@gmail.com","England","1234567890",60);
        DataHolder.addCustomer(cus);;
        cus = new Customer(6,"Brooklyn",30,"brooklyn@gmail.com","England","1234567890",70);
        DataHolder.addCustomer(cus);
        cus = new Customer(7,"Brooklyn",30,"brooklyn@gmail.com","England","1234567890",80);
        DataHolder.addCustomer(cus);
        return DataHolder.getCustomers();
    }

    public ArrayList<Customer> replace_cus(int idCustomer){
        ArrayList<Customer> customers = DataHolder.getCustomers();
        if(cus.getIdCustomer() == idCustomer){
            customers.remove(cus.getIdCustomer());
            customers.add(cus);
        }
        return customers;
    }
}
