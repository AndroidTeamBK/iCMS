package com.example.sev_user.final_weekone;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.sev_user.final_weekone.adapter.ProductAdapter;
import com.example.sev_user.final_weekone.data.ProductData;
import com.example.sev_user.final_weekone.model.Product;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemLongClick;
import butterknife.OnTextChanged;

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
    @Bind(R.id.ed_search)
    EditText edSearch;

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

    @OnItemLongClick(R.id.view_pro_lstProduct)
    public boolean showPopupEditProduct() {
        FragmentManager fragmentManager = getFragmentManager();
        PopupEditProduct popupEditProduct = new PopupEditProduct();
        popupEditProduct.show(fragmentManager, "edit_product");
        return true;
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
        hideKeyBoard();
        rlTitle.setVisibility(View.VISIBLE);
        rlSearch.setVisibility(View.GONE);
        edSearch.setText("");
    }

    @OnTextChanged(R.id.ed_search)
    public void searchSKUnumber(CharSequence skuNumber) {
        adapterProduct.filterSKU(skuNumber.toString());
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
