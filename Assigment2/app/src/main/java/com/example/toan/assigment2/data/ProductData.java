package com.example.toan.assigment2.data;

import com.example.toan.assigment2.R;
import com.example.toan.assigment2.model.Product;

import java.util.ArrayList;

/**
 * Created by toan on 16-Sep-16.
 */
public class ProductData {


    public ArrayList<Product> getListProduct() {
        ArrayList<Product> mProduct = new ArrayList<>();
        Product p;
        p = new Product("1", "AD256", "Adidas Stan Smith Pharrell Blue Jacquard", "660", "Good", "WooCommerce", "Wrangler Fashion Store", 60000.0, 500.0, 5000.0, "M", R.drawable.color1, R.drawable.image);
        mProduct.add(p);
        p = new Product("2", "AD257", "Adidas Stan Smith Pharrell Blue Jacquard", "660", "Good", "WooCommerce", "Wrangler Fashion Store", 60000.0, 500.0, 5000.0, "M", R.drawable.color3, R.drawable.image);
        mProduct.add(p);
        p = new Product("3", "AD258", "Adidas Stan Smith Pharrell Blue Jacquard", "660", "Good", "WooCommerce", "Wrangler Fashion Store", 60000.0, 500.0, 5000.0, "M", R.drawable.color3, R.drawable.image);
        mProduct.add(p);
        return mProduct;
    }
}
