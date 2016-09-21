package com.example.toan.assigment2.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.example.toan.assigment2.AddProductActivity;
import com.example.toan.assigment2.R;
import com.example.toan.assigment2.model.Product;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by toan on 16-Sep-16.
 */
public class ProductAdapter extends ArrayAdapter<Product> {
    ArrayList<Product> mProducts;
    boolean needShowMenuIcon = true;


    public ProductAdapter(Context context, int resource, ArrayList<Product> products) {
        super(context, resource, products);
        mProducts = products;
        needShowMenuIcon = true;
    }

    public ProductAdapter(Context context, int resource, ArrayList<Product> products, boolean showMenuIcon) {
        super(context, resource, products);
        mProducts = products;
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

    public void showMenu(ImageView ivMenu, final int pos) {
        PopupMenu popupMenu = new PopupMenu(getContext(), ivMenu, Gravity.START);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.edit:
                        Toast.makeText(getContext(), "Edit", Toast.LENGTH_SHORT).show();
                        Intent intentAdd = new Intent(getContext(), AddProductActivity.class);
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
    ImageView colorProduct1;
    ImageView colorProduct2;

    public ProductViewHolder(View item) {
        idProduct = (TextView) item.findViewById(R.id.item_detail_idProduct);
        nameProduct = (TextView) item.findViewById(R.id.item_detail_name_pro);
        suplierProduct = (TextView) item.findViewById(R.id.item_detail_supplier);
        brandProduct = (TextView) item.findViewById(R.id.item_detail_brand);
        skuNumber = (TextView) item.findViewById(R.id.item_detail_skunumber);
        size = (TextView) item.findViewById(R.id.item_detail_size);
        balance = (TextView) item.findViewById(R.id.item_detail_balance);
        imageProduct = (ImageView) item.findViewById(R.id.item_detail_imageProduct);
        colorProduct1 = (ImageView) item.findViewById(R.id.item_detail_color1);
        colorProduct2 = (ImageView) item.findViewById(R.id.item_detail_color2);
    }

    public void setViewfrom(Product product) {
        idProduct.setText(product.getIdProduct());
        nameProduct.setText(product.getNameProduct());
        suplierProduct.setText(product.getSupplier());
        brandProduct.setText(product.getBrandProduct());
        skuNumber.setText(product.getSkuNumber());
        size.setText(product.getSizeProduct());
        balance.setText(product.getStockBalance());
        imageProduct.setImageResource(product.getImageProduct());
        colorProduct1.setImageResource(product.getColorProduct());
        colorProduct2.setImageResource(product.getColorProduct());

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
        colorProduct1.setImageBitmap(null);
        colorProduct2.setImageBitmap(null);
    }
}
