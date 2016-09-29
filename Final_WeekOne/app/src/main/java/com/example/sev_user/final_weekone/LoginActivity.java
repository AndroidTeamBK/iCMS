package com.example.sev_user.final_weekone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sev_user.final_weekone.model.Login;
import com.example.sev_user.final_weekone.model.Utils;
import com.example.sev_user.final_weekone.myinterface.LoginInterface;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by toan on 14-Sep-16.
 */
public class LoginActivity extends Activity implements LoginInterface {
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
    public void loginAsAdmin(String name) {
        Log.e("toan.tv", "server return name: " + name);
        Intent intentAdmin = new Intent(this, ProductListActivity.class);
        startActivity(intentAdmin);
        finish();
    }

    @Override
    public void loginAsRetailer(String name) {
        Log.e("toan.tv", "server return name: " + name);
        Intent intentRetailer = new Intent(this, CustomerListActivity.class);
        startActivity(intentRetailer);
        finish();
    }

    @Override
    public void loginInvalidAccount() {
        Toast.makeText(getApplicationContext(), "Account is not valid", Toast.LENGTH_SHORT).show();
        edName.setText("");
        edPass.setText("");
    }

    @Override
    public void loginNetworkError() {
        Toast.makeText(getApplicationContext(), "Account is not valid", Toast.LENGTH_SHORT).show();
    }

}
