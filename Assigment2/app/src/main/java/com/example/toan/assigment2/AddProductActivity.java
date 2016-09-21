package com.example.toan.assigment2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by toan on 14-Sep-16.
 */
public class AddProductActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_create);
        ButterKnife.bind(this);
    }

    @OnClick (R.id.back)
    public void back(){
        finish();
    }
}
