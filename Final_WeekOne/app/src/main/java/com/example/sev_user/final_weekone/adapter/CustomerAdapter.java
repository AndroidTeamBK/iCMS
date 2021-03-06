package com.example.sev_user.final_weekone.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sev_user.final_weekone.CustomerEditActivity;
import com.example.sev_user.final_weekone.DataHolder;
import com.example.sev_user.final_weekone.R;
import com.example.sev_user.final_weekone.model.Customer;

import java.util.ArrayList;

/**
 * Created by toan on 16-Sep-16.
 */
public class CustomerAdapter extends ArrayAdapter<Customer> {
    ArrayList<Customer> mCustomers;
    ArrayList<Customer> initCustomers;

    public CustomerAdapter(Context context, int resource, ArrayList<Customer> customers) {
        super(context, resource, customers);
        mCustomers = customers;
        initCustomers = new ArrayList<>(customers);
    }

    public void filter(String name) {
        mCustomers.clear();
        if (name.equals("")) {
            mCustomers.addAll(initCustomers);
        } else {
            name = name.toLowerCase();
            for (Customer customer : initCustomers) {
                if (customer.getNameCustomer().toLowerCase().contains(name)) {
                    mCustomers.add(customer);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CustomerViewHolder holder;
        View viewRow = convertView;
        if (viewRow == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            viewRow = layoutInflater.inflate(R.layout.row_customer, parent, false);
            holder = new CustomerViewHolder(viewRow);
            viewRow.setTag(holder);
        } else
            holder = (CustomerViewHolder) viewRow.getTag();
        final ImageView ivMenu = (ImageView) viewRow.findViewById(R.id.iv_meu);
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(ivMenu, position);
            }
        });
        Customer customer = mCustomers.get(position);
        if (customer != null) {
            holder.setViewCustomer(customer);
        } else
            holder.setViewCustomer();

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
                        Customer customers = mCustomers.get(pos);
                        DataHolder.setCustomer(customers);
                        Intent intentEdit = new Intent(getContext(), CustomerEditActivity.class);
                        getContext().startActivity(intentEdit);
                        break;
                    case R.id.delete:
                        Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                        mCustomers.remove(pos);
                        notifyDataSetChanged();
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }
}

class CustomerViewHolder {
    TextView idCustomer;
    TextView nameCustomer;
    TextView phoneCustomer;
    TextView emailCustomer;
    TextView creditCustomer;

    public CustomerViewHolder(View item) {
        idCustomer = (TextView) item.findViewById(R.id.item_detail_idCustomer);
        nameCustomer = (TextView) item.findViewById(R.id.txtName_Customer_detail);
        phoneCustomer = (TextView) item.findViewById(R.id.txtPhone_customer_detail);
        emailCustomer = (TextView) item.findViewById(R.id.txtEmail_customer_detail);
        creditCustomer = (TextView) item.findViewById(R.id.txtCredit_customer_detail);
    }

    public void setViewCustomer(Customer customer) {
        idCustomer.setText("" + customer.getIdCustomer());
        nameCustomer.setText(customer.getNameCustomer());
        phoneCustomer.setText(customer.getPhoneCustomer());
        emailCustomer.setText(customer.getEmailCustomer());
        creditCustomer.setText("" + customer.getCreditCustomer());
    }

    public void setViewCustomer() {
        idCustomer.setText("");
        nameCustomer.setText("");
        phoneCustomer.setText("");
        emailCustomer.setText("");
        creditCustomer.setText("");
    }
}

