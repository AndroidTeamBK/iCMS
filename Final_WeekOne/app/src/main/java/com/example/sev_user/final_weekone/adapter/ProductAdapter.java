package com.example.sev_user.final_weekone.adapter;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.example.sev_user.final_weekone.DataHolderProduct;
import com.example.sev_user.final_weekone.EditProductActivity;
import com.example.sev_user.final_weekone.ProductCreateActivity;
import com.example.sev_user.final_weekone.R;
import com.example.sev_user.final_weekone.model.Customer;
import com.example.sev_user.final_weekone.model.Product;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by toan on 16-Sep-16.
 */
public class ProductAdapter extends ArrayAdapter<Product> {
    ArrayList<Product> mProducts;
    ArrayList<Product> initProducts;
    boolean needShowMenuIcon = true;

    public ProductAdapter(Context context, int resource, ArrayList<Product> products) {
        super(context, resource, products);
        mProducts = products;
        initProducts = new ArrayList<>(products);
        needShowMenuIcon = true;
    }

    public void filterSKU(String skuNumber) {
        mProducts.clear();
        if (skuNumber.equals("")) {
            mProducts.addAll(initProducts);
        } else {
            skuNumber = skuNumber.toLowerCase();
            for (Product product : initProducts) {
                if (product.getSkuNumber().toLowerCase().contains(skuNumber)) {
                    mProducts.add(product);
                }
            }
        }
        notifyDataSetChanged();
    }

    public ProductAdapter(Context context, int resource, ArrayList<Product> products, boolean showMenuIcon) {
        super(context, resource, products);
        mProducts = products;
        initProducts = new ArrayList<>(products);
        needShowMenuIcon = showMenuIcon;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ProductViewHolder holder;
        View viewRow = convertView;
        if (viewRow == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            viewRow = layoutInflater.inflate(R.layout.row_product, parent, false);
            holder = new ProductViewHolder(viewRow);
            viewRow.setTag(holder);
        } else
            holder = (ProductViewHolder) viewRow.getTag();

        final ImageView ivMenu = (ImageView) viewRow.findViewById(R.id.iv_meu);
        if (needShowMenuIcon)
            ivMenu.setVisibility(View.VISIBLE);
        else ivMenu.setVisibility(View.GONE);
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(ivMenu, position);
            }
        });

        Product product = mProducts.get(position);
        if (product != null) {
            holder.setViewfrom(product);
        } else
            holder.setViewfrom();

        return viewRow;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void showMenu(ImageView ivMenu, final int pos) {
        PopupMenu popupMenu = new PopupMenu(getContext(), ivMenu, Gravity.START);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.edit:
                        Toast.makeText(getContext(), "Edit", Toast.LENGTH_SHORT).show();
                        Product product = mProducts.get(pos);
                        DataHolderProduct.setProduct(product);
                        Intent intentAdd = new Intent(getContext(), EditProductActivity.class);
                        getContext().startActivity(intentAdd);
                        break;
                    case R.id.delete:
                        Toast.makeText(getContext(), "Delete", Toast.LENGTH_SHORT).show();
                        mProducts.remove(pos);
                        notifyDataSetChanged();
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }
}

class ProductViewHolder {

    TextView idProduct;
    TextView nameProduct;
    TextView suplierProduct;
    TextView brandProduct;
    TextView skuNumber;
    TextView size;
    TextView balance;
    ImageView imageProduct;
    ImageView[] colorProduct = new ImageView[4];


    public ProductViewHolder(View item) {
        idProduct = (TextView) item.findViewById(R.id.item_detail_idProduct);
        nameProduct = (TextView) item.findViewById(R.id.item_detail_name_pro);
        suplierProduct = (TextView) item.findViewById(R.id.item_detail_supplier);
        brandProduct = (TextView) item.findViewById(R.id.item_detail_brand);
        skuNumber = (TextView) item.findViewById(R.id.item_detail_skunumber);
        size = (TextView) item.findViewById(R.id.item_detail_size);
        balance = (TextView) item.findViewById(R.id.item_detail_balance);
        imageProduct = (ImageView) item.findViewById(R.id.item_detail_imageProduct);
        colorProduct[0] = (ImageView) item.findViewById(R.id.item_detail_color1);
        colorProduct[1] = (ImageView) item.findViewById(R.id.item_detail_color2);
        colorProduct[2] = (ImageView) item.findViewById(R.id.item_detail_color3);
        colorProduct[3] = (ImageView) item.findViewById(R.id.item_detail_color4);
    }

    public void setViewfrom(Product product) {
        idProduct.setText("" + product.getIdProduct());
        nameProduct.setText(product.getNameProduct());
        suplierProduct.setText(product.getSupplier());
        brandProduct.setText(product.getBrandProduct());
        skuNumber.setText(product.getSkuNumber());
        size.setText(product.getSizeProduct());
        balance.setText(product.getStockBalance());
        imageProduct.setImageBitmap(product.getImageProduct());

        for (int i = 0; i < product.getColorProduct().length; i++) {
            colorProduct[i].setImageResource(product.getColorProduct()[i]);
        }

//        colorProduct1.setImageResource(product.getColorProduct()[0]);
//        colorProduct2.setImageResource(product.getColorProduct()[1]);
    }

    public void setViewfrom() {
        idProduct.setText("");
        nameProduct.setText("");
        suplierProduct.setText("");
        brandProduct.setText("");
        skuNumber.setText("");
        size.setText("");
        balance.setText("");
        imageProduct.setImageBitmap(null);
        for (int i = 0; i < 4; i++)
            colorProduct[i].setImageBitmap(null);
        //colorProduct2.setImageBitmap(null);
    }
}
