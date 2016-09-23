package com.example.sev_user.final_weekone;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.sev_user.final_weekone.adapter.ProductAdapter;
import com.example.sev_user.final_weekone.data.ProductData;
import com.example.sev_user.final_weekone.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * Created by toan on 17-Sep-16.
 */
public class ProductDetailActivity extends Activity {

    @Bind(R.id.pro_de_lv)
    ListView lvProductDetail;
    @Bind(R.id.filter)
    LinearLayout linearLayoutFilter;
    @Bind(R.id.rl_title)
    RelativeLayout relativeLayoutTitle;

    @Bind(R.id.sp_supplier)
    Spinner spSupplier;
    @Bind(R.id.sp_balance)
    Spinner spBalance;
    @Bind(R.id.sp_color)
    Spinner spColor;
    @Bind(R.id.sp_size)
    Spinner spSize;

    ArrayList<Product> mProduct;
    ProductAdapter adapterProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);

        mProduct = new ProductData().getListProduct();
        adapterProduct = new ProductAdapter(this, R.layout.row_product, mProduct, false);
        lvProductDetail.setAdapter(adapterProduct);
        fillData2Spinner();
    }

    @OnClick(R.id.back)
    public void back() {
        finish();
    }

    @OnClick(R.id.img_search)
    public void showFilter() {
        linearLayoutFilter.setVisibility(View.VISIBLE);
        relativeLayoutTitle.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_filter)
    public void filterAndShowTilte() {
        linearLayoutFilter.setVisibility(View.GONE);
        relativeLayoutTitle.setVisibility(View.VISIBLE);
        int posSup = spSupplier.getSelectedItemPosition();
        if (posSup != 0)
            adapterProduct.filterSupplier(supppliers.get(posSup));

        int posBa = spBalance.getSelectedItemPosition();
        if (posBa != 0)
            adapterProduct.filterBalance(balances.get(posBa));

        int posSize = spSize.getSelectedItemPosition();
        if (posSize != 0)
            adapterProduct.filterSize(sizes.get(posSize));

        int posColor = spColor.getSelectedItemPosition();
        if (posColor != 0) {
            adapterProduct.filterColor(convertName2Color(colors.get(posColor)));
        }
    }

    int convertName2Color(String name) {
        switch (name) {
            case "Red":
                return R.drawable.color1;
            case "Blue":
                return R.drawable.color2;
            case "Yellow":
                return R.drawable.color3;
        }
        return R.drawable.color1;
    }

    void fillData2Spinner() {
        setSpinnerSupplier();
        setSpinnerBalance();
        setSpinnerColor();
        setSpinnerSize();
    }

    // detect supplier from original data in listview
    ArrayList<String> supppliers;

    void setSpinnerSupplier() {
        supppliers = new ArrayList<>();
        String supplier;
        for (Product product : mProduct) {
            supplier = product.getSupplier();
            if (!supppliers.contains(supplier))
                supppliers.add(supplier);
        }
        ArrayAdapter<String> adapterSupplier = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, supppliers);
        adapterSupplier.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Collections.sort(supppliers);
        supppliers.add(0, "Supplier");
        spSupplier.setAdapter(adapterSupplier);
    }

    // detect supplier from original data in listview
    ArrayList<String> balances;

    void setSpinnerBalance() {
        balances = new ArrayList<>();
        String balance;
        for (Product product : mProduct) {
            balance = product.getStockBalance();
            if (!balances.contains(balance))
                balances.add(balance);
        }
        ArrayAdapter<String> adapterBalance = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, balances);
        adapterBalance.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Collections.sort(balances);
        balances.add(0, "Balance");
        spBalance.setAdapter(adapterBalance);
    }

    // detect supplier from original data in listview
    ArrayList<String> sizes;

    void setSpinnerSize() {
        sizes = new ArrayList<>();
        for (Product product : mProduct) {
            String size = product.getSizeProduct();
            if (!sizes.contains(size))
                sizes.add(size);
        }
        ArrayAdapter<String> adapterSize = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sizes);
        adapterSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Collections.sort(sizes);
        sizes.add(0, "Size");
        spSize.setAdapter(adapterSize);
    }

    // detect supplier from original data in listview
    ArrayList<String> colors;

    void setSpinnerColor() {
        colors = new ArrayList<>();
        ArrayList<Integer> colorsInt = new ArrayList<>();
        for (Product product : mProduct) {
            int[] color = product.getColorProduct();
            for (int i = 0; i < color.length; ++i) {
                if (!colorsInt.contains(color[i]))
                    colorsInt.add(color[i]);
            }
        }
        for (int i = 0; i < colorsInt.size(); ++i) {
            switch (colorsInt.get(i)) {
                case R.drawable.color1:
                    colors.add("Red");
                    break;
                case R.drawable.color2:
                    colors.add("Blue");
                    break;
                case R.drawable.color3:
                    colors.add("Yellow");
                    break;
            }
        }
        ArrayAdapter<String> adapterSupplier = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colors);
        adapterSupplier.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Collections.sort(colors);
        colors.add(0, "Color");
        spColor.setAdapter(adapterSupplier);
    }

    @OnItemSelected(R.id.sp_supplier)
    public void filterSupplier(int pos) {
//        if (pos == 0)
//            adapterProduct.filterSupplier("");
//        else
//            adapterProduct.filterSupplier(supppliers.get(pos));
    }
}
