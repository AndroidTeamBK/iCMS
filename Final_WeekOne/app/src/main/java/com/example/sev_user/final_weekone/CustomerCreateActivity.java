package com.example.sev_user.final_weekone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sev_user.final_weekone.model.Customer;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by toan on 14-Sep-16.
 */
public class CustomerCreateActivity extends Activity {

    @Bind(R.id.create_cu_name)
    EditText ed_create_cu_name;
    @Bind(R.id.create_cu_age)
    EditText ed_create_cu_age;
    @Bind(R.id.create_cu_email)
    EditText ed_create_cu_email;
    @Bind(R.id.create_cu_address)
    EditText ed_create_cu_address;
    @Bind(R.id.create_cu_phone)
    EditText ed_create_cu_phone;
    @Bind(R.id.create_cu_credit)
    EditText ed_create_cu_credit;
    @Bind(R.id.create_cu_btn_create)
    Button btn_create_cu_create;


    Customer customer_fromCreate;
    ArrayList<Customer> customers_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_create);
        ButterKnife.bind(this);
        btn_create_cu_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_customerInfor();
            }
        });
    }
    public void edit_customerInfor(){
        //customers_create = DataHolder.getCustomers();
        String cu_name = ed_create_cu_name.getText().toString();
        String cu_age = ed_create_cu_age.getText().toString();
        //int cu_age_create = Integer.parseInt(cu_age);
        String cu_email = ed_create_cu_email.getText().toString();
        String cu_address = ed_create_cu_address.getText().toString();
        String cu_phone = ed_create_cu_phone.getText().toString();
        String cu_credit_cus = ed_create_cu_credit.getText().toString();
        //int cu_credit = Integer.parseInt(cu_credit_cus);

        if(cu_name.matches("")||cu_age.matches("")||cu_email.matches("")||cu_address.matches("")||cu_phone.matches("")
                ||cu_credit_cus.matches(""))
            Toast.makeText(this,"Please fill full information for creating customer",Toast.LENGTH_LONG).show();
        else {
            int cu_credit = Integer.parseInt(cu_credit_cus);
            int cu_age_create = Integer.parseInt(cu_age);
            int id_addCustomer = DataHolder.getCustomers().size()+1;
            customer_fromCreate = new Customer(id_addCustomer, cu_name, cu_age_create, cu_email, cu_address, cu_phone, cu_credit);
            DataHolder.addCustomer(customer_fromCreate);
            Intent i = new Intent(getApplicationContext(),CustomerListActivity.class);
            startActivity(i);
        }

    }

    /*@OnClick(R.id.create_cu_btn_create)
    public void setCustomer_fromCreate(){
        edit_customerInfor();
    }*/
    @OnClick(R.id.cre_cus_btn_back)
    public void back(){
        finish();
    }
}
