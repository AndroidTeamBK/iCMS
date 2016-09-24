package com.example.sev_user.final_weekone;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.sev_user.final_weekone.data.ProductData;
import com.example.sev_user.final_weekone.model.Product;

import java.util.ArrayList;

/**
 * Created by sev_user on 9/20/2016.
 */
public class DataHolderProduct {
    private static Product product;
    private static ArrayList<Product> products;
    private static Resources resources;

    public static void setResources(Resources resources) {
        DataHolderProduct.resources = resources;
    }

    public static Resources getResources() {

        return resources;
    }

    private static ArrayList<Product> getinitProduct() {
        ArrayList<Product> mProduct = new ArrayList<>();
        Bitmap mtestImageproduct = BitmapFactory.decodeResource(resources, R.drawable.image);
        int color[] = {R.drawable.color1, R.drawable.color2, R.drawable.color3};
        Product p;
        p = new Product(1, "AD256", "Adidas Stan Smith Pharrell Blue Jacquard 1", "660", "Good", "WooCommerce1", "Wrangler Fashion Store2", 60000.0, 500.0, 5000.0, "M", color, mtestImageproduct);
        mProduct.add(p);
        p = new Product(2, "AD256", "Adidas Stan Smith Pharrell Blue Jacquard 2", "660", "Good", "WooCommerce2", "Wrangler Fashion Store1", 60000.0, 500.0, 5000.0, "X", color, mtestImageproduct);
        mProduct.add(p);
        p = new Product(3, "AD257", "Adidas Stan Smith Pharrell Blue Jacquard 3", "660", "Good", "WooCommerce2", "Wrangler Fashion Store1", 60000.0, 500.0, 5000.0, "X", color, mtestImageproduct);
        mProduct.add(p);
        p = new Product(4, "AD257", "Adidas Stan Smith Pharrell Blue Jacquard 3", "660", "Good", "WooCommerce2", "Wrangler Fashion Store1", 60000.0, 500.0, 5000.0, "X", color, mtestImageproduct);
        mProduct.add(p);
        return mProduct;
    }

    public static void clearDataProduct() {
        products = new ArrayList<>();
    }

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static void deleteProduct(int pos) {
        products.remove(pos);
    }

    public static ArrayList<Product> getProducts() {
        // for the first time, products will be null
        if (products == null)
            products = getinitProduct();
        return new ArrayList<>(products);
    }

    public static void setProduct(Product pro) {
        DataHolderProduct.product = pro;
    }

    public static Product getProduct() {
        return DataHolderProduct.product;
    }

}
