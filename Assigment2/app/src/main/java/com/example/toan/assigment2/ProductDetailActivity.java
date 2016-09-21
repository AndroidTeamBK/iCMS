package com.example.toan.assigment2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.toan.assigment2.adapter.ProductAdapter;
import com.example.toan.assigment2.data.ProductData;
import com.example.toan.assigment2.model.Product;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    }

    @OnClick(R.id.back)
    public void back() {
        finish();
    }

    @OnClick(R.id.iv_search)
    public void showFilter() {
        linearLayoutFilter.setVisibility(View.VISIBLE);
        relativeLayoutTitle.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_filter)
    public void filterAndShowTilte(){
        linearLayoutFilter.setVisibility(View.GONE);
        relativeLayoutTitle.setVisibility(View.VISIBLE);
    }
}
