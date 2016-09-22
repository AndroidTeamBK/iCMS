package com.example.sev_user.final_weekone;

import com.example.sev_user.final_weekone.model.Product;

import java.util.ArrayList;

/**
 * Created by sev_user on 9/20/2016.
 */
public class DataHolderProduct {
    private static Product product;
    private static ArrayList<Product> products = new ArrayList<>();

    public static void clearDataProduct(){products = new ArrayList<>();}

    public static void addProduct(Product product){
        products.add(product);
    }

    public static ArrayList<Product> getProducts(){
        ArrayList<Product> selection = new ArrayList<>();
        for(int i = 0; i < products.size();++i){
            Product pro = products.get(i);
            selection.add(pro);
        }
        return selection;
    }

    public static void setProduct(Product pro){ DataHolderProduct.product = pro;}
    public static Product getProduct(){return DataHolderProduct.product; }

}
