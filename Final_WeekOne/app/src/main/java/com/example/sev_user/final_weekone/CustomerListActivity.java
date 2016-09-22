package com.example.sev_user.final_weekone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sev_user.final_weekone.adapter.CustomerAdapter;
import com.example.sev_user.final_weekone.data.CustomerData;
import com.example.sev_user.final_weekone.model.Customer;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import butterknife.OnTextChanged;

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
    @Bind(R.id.ed_search)
    EditText edSearch;

    ArrayList<Customer> mCustomer;
    CustomerAdapter adapterCustomer;


    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        ButterKnife.bind(this);
        addDrawerItemsRetailer();
        Log.d("dai.dv", "" + DataHolder.getCustomers().size());
        if (DataHolder.getCustomers().size() == 0) mCustomer = new CustomerData().getListCustomer();
        else mCustomer = DataHolder.getCustomers();

        adapterCustomer = new CustomerAdapter(this, R.layout.row_customer, mCustomer);
        lvCustomer.setAdapter(adapterCustomer);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateListview();
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

    private void updateListview() {
        mCustomer = DataHolder.getCustomers();
        adapterCustomer.notifyDataSetChanged();
    }

    @OnClick(R.id.cus_list_btn_neviga)
    public void showMenuRetailer() {
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    @OnItemClick(R.id.pro_list_lv)
    public void showEditCustomer(int pos) {
        Toast.makeText(this, "edit cus: " + mCustomer.get(pos).getCreditCustomer(), Toast.LENGTH_LONG).show();
        Customer customer1 = mCustomer.get(pos);
        Intent intentEdit = new Intent(getApplicationContext(), CustomerEditActivity.class);
        DataHolder.setCustomer(customer1);
        startActivity(intentEdit);
    }

    @OnItemLongClick(R.id.pro_list_lv)
    public boolean deleteCustomer(int pos) {
        Toast.makeText(this, "delete cus: " + mCustomer.get(pos).getCreditCustomer(), Toast.LENGTH_LONG).show();
        return true;
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

    @OnTextChanged(R.id.ed_search)
    public void searchCustomerName(CharSequence name) {
        adapterCustomer.filter(name.toString());
    }

    @OnClick(R.id.iv_search)
    public void hideTitleAndShowSearch() {
        rlTitle.setVisibility(View.GONE);
        rlSearch.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.tv_search_cancel)
    public void cancelSearch() {
        hideKeyBoard();
        rlTitle.setVisibility(View.VISIBLE);
        rlSearch.setVisibility(View.GONE);
        edSearch.setText("");
    }

    void hideKeyBoard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }
}
