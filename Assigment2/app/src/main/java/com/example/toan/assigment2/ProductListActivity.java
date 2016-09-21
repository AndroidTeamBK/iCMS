package com.example.toan.assigment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toan.assigment2.adapter.ProductAdapter;
import com.example.toan.assigment2.data.ProductData;
import com.example.toan.assigment2.model.Product;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;


/**
 * Created by toan on 14-Sep-16.
 */
public class ProductListActivity extends Activity {

    @Bind(R.id.navList)
    ListView mDrawerList;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.pro_list_lv)
    ListView lvProduct;
    @Bind(R.id.lin_title)
    LinearLayout linearLayoutTitle;
    @Bind(R.id.rl_search)
    RelativeLayout relativeLayoutSearch;

    boolean clickedBack1Time = false;
    private ArrayAdapter<String> mAdapter;

    ArrayList<Product> mProduct;
    ProductAdapter adapterProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);
        addDrawerItemsAdimin();

        mProduct = new ProductData().getListProduct();
        adapterProduct = new ProductAdapter(this, R.layout.row_product, mProduct);
        lvProduct.setAdapter(adapterProduct);
    }

    private void addDrawerItemsAdimin() {
        final String[] osArray = {"Product management", "Log out"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) mDrawerList.getItemAtPosition(position);
                if (itemValue == osArray[1]) {
                    Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intentLogin);
                    finish();
                }
                if (itemValue == osArray[0]) {
                    mDrawerLayout.closeDrawers();
                }
            }
        });
    }

    // event process
    @OnClick(R.id.pro_list_btn_add)
    public void showAddProduct() {
        Intent intentAdd = new Intent(this, AddProductActivity.class);
        startActivity(intentAdd);
    }

    @OnClick(R.id.pro_list_img_search)
    public void hideTitleAndShowSearch() {
        linearLayoutTitle.setVisibility(View.GONE);
        relativeLayoutSearch.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.tv_search_cancel)
    public void cancelSearch() {
        linearLayoutTitle.setVisibility(View.VISIBLE);
        relativeLayoutSearch.setVisibility(View.GONE);
    }

    @OnClick(R.id.pro_list_btn_transfer)
    public void showTransferProdcut() {
        Intent intentTransfer = new Intent(this, TransferProductActivity.class);
        startActivity(intentTransfer);
    }

    @OnClick(R.id.pro_list_btn_neviga)
    public void showMenuNavigation() {
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    @OnItemClick(R.id.pro_list_lv)
    public void showProductDetail(int pos) {
        Intent intentDetail = new Intent(this, ProductDetailActivity.class);
        startActivity(intentDetail);
    }

    @Override
    public void onBackPressed() {
        if (clickedBack1Time) {
            super.onBackPressed();
            return;
        }
        clickedBack1Time = true;
        Toast.makeText(this, "Click BACK again to exit", Toast.LENGTH_SHORT).show();
        // reset after 1 second
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                clickedBack1Time = false;
            }
        }, 1000);
    }
}
