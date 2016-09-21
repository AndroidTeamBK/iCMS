package com.example.toan.assigment2;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.toan.assigment2.adapter.ProductAdapter;
import com.example.toan.assigment2.data.ProductData;
import com.example.toan.assigment2.model.Product;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;

/**
 * Created by toan on 14-Sep-16.
 */
public class OutletViewProductActivity extends Activity {

    @Bind(R.id.hidden_panel_layout)
    LinearLayout hiddenPanel;

    @Bind(R.id.view_pro_btn_drop)
    ImageButton btnShowHide;
    @Bind(R.id.view_pro_lstProduct)
    ListView lvProduct;

    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.rl_search)
    RelativeLayout rlSearch;

    ArrayList<Product> mProduct;
    ProductAdapter adapterProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlet_view_product);
        ButterKnife.bind(this);
        // default is hide this info => button need to be show
        hideOutletInfo();

        mProduct = new ProductData().getListProduct();
        adapterProduct = new ProductAdapter(this, R.layout.row_customer, mProduct, false);
        lvProduct.setAdapter(adapterProduct);

    }

    @OnClick(R.id.view_pro_btn_drop)
    public void showHideOutletInfo() {
        // current is hide => visible
        if (hiddenPanel.getVisibility() == View.GONE) {
            showOutletInfo();
        } else {
            // current is visible => hide
            hideOutletInfo();
        }

    }

    @OnClick(R.id.back)
    public void back() {
        finish();
    }

    @OnClick(R.id.view_pro_btn_ddit)
    public void showPopupEditInfo() {
        FragmentManager fragmentManager = getFragmentManager();
        PopupEditInfoOutlet popupEditInfoOutlet = new PopupEditInfoOutlet();
        popupEditInfoOutlet.show(fragmentManager, "fragment_edit");
    }

    @OnItemClick(R.id.view_pro_lstProduct)
    public void showPopupEditProduct(int pos) {
        Toast.makeText(getApplicationContext(), "pos: " + pos, Toast.LENGTH_LONG).show();
        FragmentManager fragmentManager = getFragmentManager();
        PopupEditProduct popupEditProduct = new PopupEditProduct();
        popupEditProduct.show(fragmentManager, "edit_product");
    }

    void hideOutletInfo() {
        hiddenPanel.setVisibility(View.GONE);
        btnShowHide.setBackgroundResource(R.drawable.drop);
    }

    void showOutletInfo() {
        hiddenPanel.setVisibility(View.VISIBLE);
        btnShowHide.setBackgroundResource(R.drawable.up_icon);
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
