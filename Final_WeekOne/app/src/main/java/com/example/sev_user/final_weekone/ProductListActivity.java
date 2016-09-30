package com.example.sev_user.final_weekone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
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
import com.example.sev_user.final_weekone.model.Logout;
import com.example.sev_user.final_weekone.model.Product;
import com.example.sev_user.final_weekone.myinterface.AdapterCallActivity;
import com.example.sev_user.final_weekone.myinterface.LogOutInterface;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnTextChanged;


/**
 * Created by toan on 14-Sep-16.
 */
public class ProductListActivity extends Activity implements AdapterCallActivity, LogOutInterface {

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

    ProductListActivity productListActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);
        DataHolderProduct.setResources(getResources());
        addDrawerItemsAdimin();
        fillData2ListView();
    }

    void fillData2ListView() {
        mProduct = DataHolderProduct.getProducts();
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
                    Logout logout = new Logout(productListActivity);
                    logout.exceute();

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
        Intent intentDetail = new Intent(this, ProductDetailActivity.class);
        intentDetail.putExtra("sku", mProduct.get(pos).getSkuNumber());
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

    @Override
    public void deleteIteam(int pos) {
        mProduct.remove(pos);
        adapterProduct.notifyDataSetChanged();
    }

    @Override
    public void editItem(int pos) {

    }

    @Override
    public void logoutSuccess() {
        Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intentLogin);
        finish();
    }

    @Override
    public void logoutError() {
        Toast.makeText(getApplicationContext(), "There are problem when logout, try it again!", Toast.LENGTH_LONG).show();
    }
}
