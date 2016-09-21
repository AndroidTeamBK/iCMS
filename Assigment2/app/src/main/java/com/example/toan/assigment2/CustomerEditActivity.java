package com.example.toan.assigment2;

import android.app.Activity;
import android.os.Bundle;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by toan on 16-Sep-16.
 */
public class CustomerEditActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.back)
    public void back() {
        finish();
    }

}
