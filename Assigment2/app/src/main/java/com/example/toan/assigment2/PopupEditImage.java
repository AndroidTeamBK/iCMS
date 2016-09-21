package com.example.toan.assigment2;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by toan on 17-Sep-16.
 */
public class PopupEditImage extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_view_pro_editimage_update, container);

        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.save)
    public void saveData() {
        // process data
        getDialog().dismiss();
    }
}
