package com.example.toan.assigment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.toan.assigment2.model.Login;
import com.example.toan.assigment2.model.Utils;
import com.example.toan.assigment2.myinterface.LoginInterface;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by toan on 14-Sep-16.
 */
public class LoginActivity extends Activity implements LoginInterface{
    @Bind(R.id.login_ed_name)
    EditText edName;

    @Bind(R.id.login_ed_pass)
    EditText edPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_btn_login)
    public void login() {
        Login login = new Login(this);
        login.isValidateAccount(edName.getText().toString(), edPass.getText().toString());
    }

    @Override
    public void loginAsAdmin() {
        Intent intentAdmin = new Intent(this, ProductListActivity.class);
        startActivity(intentAdmin);
        finish();
    }

    @Override
    public void loginAsRetailer() {
        Intent intentRetailer = new Intent(this, CustomerListActivity.class);
        startActivity(intentRetailer);
        finish();
    }

    @Override
    public void loginError() {
        Toast.makeText(getApplicationContext(), "Account is not valid", Toast.LENGTH_SHORT).show();
        edName.setText("");
        edPass.setText("");
    }
}
