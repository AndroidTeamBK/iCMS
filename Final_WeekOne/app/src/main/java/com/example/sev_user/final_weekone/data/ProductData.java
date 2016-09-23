package com.example.sev_user.final_weekone.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.example.sev_user.final_weekone.DataHolder;
import com.example.sev_user.final_weekone.DataHolderProduct;
import com.example.sev_user.final_weekone.R;
import com.example.sev_user.final_weekone.model.Product;

import java.util.ArrayList;

/**
 * Created by toan on 16-Sep-16.
 */
public class ProductData {

    //String[] brand_Cus1 = {"WooCommerce1,WooCommerce2,WooCommerce3"};
    // String[] supplier_Cus1 = {"Wrangler Fashion Store1","Wrangler Fashion Store2","Wrangler Fashion Store3"};
    Product p;
    int color[] = {R.drawable.color1, R.drawable.color2, R.drawable.color3};
    Bitmap mtestImageproduct = null;

    public ArrayList<Product> getListProduct() {
        ArrayList<Product> mProduct = new ArrayList<>();
        //Bitmap imagenAndroid = BitmapFactory.decodeResource(getResources(),R.drawable.image);

        p = new Product(1, "AD256", "Adidas Stan Smith Pharrell Blue Jacquard 1", "660", "Good", "WooCommerce1", "Wrangler Fashion Store2", 60000.0, 500.0, 5000.0, "M", color, mtestImageproduct);
        mProduct.add(p);
        DataHolderProduct.addProduct(p);
        p = new Product(2, "AD257", "Adidas Stan Smith Pharrell Blue Jacquard 2", "660", "Good", "WooCommerce2", "Wrangler Fashion Store1", 60000.0, 500.0, 5000.0, "X", color, mtestImageproduct);
        mProduct.add(p);
        DataHolderProduct.addProduct(p);
//        p = new Product(3, "AD258", "Adidas Stan Smith Pharrell Blue Jacquard", "660", "Good", "WooCommerce1", "Wrangler Fashion Store2", 60000.0, 500.0, 5000.0, "M", R.drawable.color3, R.drawable.image);
//        DataHolderProduct.addProduct(p);
        return DataHolderProduct.getProducts();
        //mProduct.add(p);
//         return mProduct;
    }
}
