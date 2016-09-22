package com.example.sev_user.final_weekone;

import com.example.sev_user.final_weekone.model.Customer;
import com.example.sev_user.final_weekone.model.Product;

import java.util.ArrayList;

/**
 * Created by admin on 9/18/2016.
 */
public class DataHolder {
    private static Customer customer;
    private static ArrayList<Customer> customers = new ArrayList<>();

    public static void clearDataCustomer(){customers = new ArrayList<>();}

    public static void addCustomer(Customer customer){
        customers.add(customer);
    }

    public static ArrayList<Customer> getCustomers(){
        ArrayList<Customer> selection = new ArrayList<>();
        for(int i = 0; i < customers.size();++i){
            Customer cus = customers.get(i);
            selection.add(cus);
        }
        return selection;
    }

    public static void setCustomer(Customer cus){ DataHolder.customer = cus;}
    public static Customer getCustomer(){return DataHolder.customer; }


    /*private static Product product;
    private static ArrayList<Product> products = new ArrayList<>();

    public static void clearDataProduct(){products = new ArrayList<>();}

    public static void addProduct(Product customer){
        products.add(product);
    }

    public static ArrayList<Product> getProducts(){
        ArrayList<Product> selection = new ArrayList<>();
        for(int i = 0; i < products.size();++i){
            Product cus = products.get(i);
            selection.add(cus);
        }
        return selection;
    }

    public static void setProduct(Product cus){ DataHolder.product = cus;}
    public static Product getProduct(){return DataHolder.product; }*/
}
