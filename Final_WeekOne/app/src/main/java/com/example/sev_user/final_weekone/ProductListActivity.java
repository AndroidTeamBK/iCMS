package com.example.sev_user.final_weekone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.sev_user.final_weekone.adapter.ProductAdapter;
import com.example.sev_user.final_weekone.data.ProductData;
import com.example.sev_user.final_weekone.model.Product;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnTextChanged;


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
    @Bind(R.id.ed_search)
    EditText edSearch;

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

        //mProduct = new ProductData().getListProduct();
        Bitmap temp_ImageProduct = BitmapFactory.decodeResource(getResources(),
                R.drawable.image);

        if (DataHolderProduct.getProducts().size() == 0) {
            mProduct = new ProductData().getListProduct();
            for (int i = 0; i < mProduct.size(); i++) {
                if (mProduct.get(i).getImageProduct() == null) {
                    mProduct.get(i).setImageProduct(temp_ImageProduct);
                    //DataHolderProduct.addProduct(mProduct.get(i));
                }
            }
        } else mProduct = DataHolderProduct.getProducts();
        adapterProduct = new ProductAdapter(this, R.layout.row_product, mProduct);
        lvProduct.setAdapter(adapterProduct);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateListview();
    }

    private void updateListview() {
        mProduct = DataHolderProduct.getProducts();
        adapterProduct.notifyDataSetChanged();
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
        Intent intentAdd = new Intent(this, ProductCreateActivity.class);
        startActivity(intentAdd);
    }

    @OnClick(R.id.pro_list_img_search)
    public void hideTitleAndShowSearch() {
        linearLayoutTitle.setVisibility(View.GONE);
        relativeLayoutSearch.setVisibility(View.VISIBLE);
    }

    @OnTextChanged(R.id.ed_search)
    public void searchSKUnumber(CharSequence skuNumber) {
        adapterProduct.filterSKU(skuNumber.toString());
    }

    @OnClick(R.id.tv_search_cancel)
    public void cancelSearch() {
        hideKeyBoard();
        linearLayoutTitle.setVisibility(View.VISIBLE);
        relativeLayoutSearch.setVisibility(View.GONE);
        edSearch.setText("");
    }

    @OnClick(R.id.pro_list_btn_transfer)
    public void showTransferProdcut() {
        Intent intentTransfer = new Intent(this, TransferProductActivity.class);
        startActivity(intentTransfer);
    }
    void hideKeyBoard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.pro_list_btn_neviga)
    public void showMenuNavigation() {
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    @OnItemClick(R.id.pro_list_lv)
    public void showProductDetail(int pos) {
        //Product product = mProduct.get(pos);
        Intent intentDetail = new Intent(this, ProductDetailActivity.class);
        //DataHolderProduct.setProduct(product);
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
