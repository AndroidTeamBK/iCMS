package com.example.sev_user.final_weekone;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sev_user.final_weekone.data.CustomerData;
import com.example.sev_user.final_weekone.model.Customer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by toan on 16-Sep-16.
 */
public class CustomerEditActivity extends Activity {
    @Bind(R.id.create_cu_name)
    EditText edit_Create_cu_name;
    @Bind(R.id.create_cu_age)
    EditText edit_Create_cu_age;
    @Bind(R.id.create_cu_email)
    EditText edit_Create_cu_email;
    @Bind(R.id.create_cu_address)
    EditText edit_Create_cu_address;
    @Bind(R.id.create_cu_phone)
    EditText edit_create_cu_phone;
    @Bind(R.id.create_cu_credit)
    EditText edit_Create_cu_credit;


    Customer customer_FromLst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit);
        ButterKnife.bind(this);
        showCustomerFromListview();
    }
    public void showCustomerFromListview(){
        customer_FromLst = DataHolder.getCustomer();
        edit_Create_cu_name.setText(customer_FromLst.getNameCustomer());
        edit_Create_cu_age.setText(""+customer_FromLst.getAgeCustomer());
        edit_Create_cu_email.setText(customer_FromLst.getEmailCustomer());
        edit_Create_cu_address.setText(customer_FromLst.getAddressCustomer());
        edit_create_cu_phone.setText(customer_FromLst.getPhoneCustomer());
        edit_Create_cu_credit.setText(""+customer_FromLst.getCreditCustomer());
    }
    public void edit_customerInfor(){
        String cu_name = edit_Create_cu_name.getText().toString();
        customer_FromLst.setNameCustomer(cu_name);
        String cu_age = edit_Create_cu_age.getText().toString();
        customer_FromLst.setAgeCustomer(Integer.parseInt(cu_age));
        String cu_email = edit_Create_cu_email.getText().toString();
        customer_FromLst.setEmailCustomer(cu_email);
        String cu_address = edit_Create_cu_address.getText().toString();
        customer_FromLst.setAddressCustomer(cu_address);
        String cu_phone = edit_create_cu_phone.getText().toString();
        customer_FromLst.setPhoneCustomer(cu_phone);
        String cu_credit = edit_Create_cu_credit.getText().toString();
        customer_FromLst.setCreditCustomer(Integer.parseInt(cu_credit));
    }

    @OnClick(R.id.ed_cus_btn)
    public void saveInfor(){
        edit_customerInfor();
        DataHolder.setCustomer(customer_FromLst);
        finish();
    }

    @OnClick(R.id.ed_cus_btn_back)
    public void back() {
        finish();
    }

}
