package com.example.toan.assigment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toan.assigment2.adapter.CustomerAdapter;
import com.example.toan.assigment2.data.CustomerData;
import com.example.toan.assigment2.model.Customer;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;

/**
 * Created by toan on 14-Sep-16.
 */
public class CustomerListActivity extends Activity {

    boolean clickedBack1Time = false;

    @Bind(R.id.navList)
    ListView mDrawerList;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.avatar)
    ImageView imgAvatar;
    @Bind(R.id.userName)
    TextView nameLogin;
    @Bind(R.id.pro_list_lv)
    ListView lvCustomer;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.rl_search)
    RelativeLayout rlSearch;

    ArrayList<Customer> mCustomer;
    CustomerAdapter adapterCustomer;


    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        ButterKnife.bind(this);
        addDrawerItemsRetailer();

        mCustomer = new CustomerData().getListCustomer();
        adapterCustomer = new CustomerAdapter(this, R.layout.row_customer, mCustomer);
        lvCustomer.setAdapter(adapterCustomer);

    }

    private void addDrawerItemsRetailer() {
        final String[] osArray = {"Outlet management", "Customer management", "Log out"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
        nameLogin.setText("Retailer");
        imgAvatar.setImageResource(R.drawable.user);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intentOutlet = new Intent(getApplicationContext(), OutletViewProductActivity.class);
                        startActivity(intentOutlet);
                        break;
                    case 1:
                        mDrawerLayout.closeDrawers();
                        break;
                    case 2:
                        Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intentLogin);
                        finish();
                        break;
                }
            }
        });
    }

    @OnClick(R.id.cus_list_btn_neviga)
    public void showMenuRetailer() {
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    @OnItemClick(R.id.pro_list_lv)
    public void showEditCustomer(int pos) {

    }

    @OnClick(R.id.cus_list_btn_add)
    public void showAddCustomer() {
        Intent intent = new Intent(this, CustomerCreateActivity.class);
        startActivity(intent);
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

    @OnClick(R.id.iv_search)
    public void hideTitleAndShowSearch() {
        rlTitle.setVisibility(View.GONE);
        rlSearch.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.tv_search_cancel)
    public void cancelSearch() {
        rlTitle.setVisibility(View.VISIBLE);
        rlSearch.setVisibility(View.GONE);
    }
}
