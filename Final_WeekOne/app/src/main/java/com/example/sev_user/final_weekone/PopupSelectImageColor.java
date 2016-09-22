package com.example.sev_user.final_weekone;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sev_user on 9/22/2016.
 */
public class PopupSelectImageColor extends DialogFragment {
    @Bind(R.id.checkbox_color1)
    CheckBox color1;
    @Bind(R.id.checkbox_color2)
    CheckBox color2;
    @Bind(R.id.checkbox_color3)
    CheckBox color3;
    @Bind(R.id.checkbox_color4)
    CheckBox color4;

    int[] colorID = new int[4];
    int count;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_create_imagecolor_product, container);

        ButterKnife.bind(this, view);
        return view;
    }
    public void addColorProduct(){
        count = 0;
        if(color1.isChecked())
            colorID[count++] = R.drawable.color1;
            //colorID[0] = R.drawable.color1;
        if(color2.isChecked())
            colorID[count++] = R.drawable.color2;
        if(color3.isChecked())
            colorID[count++] = R.drawable.color3;
        if(color4.isChecked())
            colorID[count++] = R.drawable.color1;
    }


    @OnClick(R.id.saveImageColor)
    public void saveData() {
        // process data
        addColorProduct();
        EditDialogPopup activity = (EditDialogPopup) getActivity();
        activity.updateResult(colorID);
        getDialog().dismiss();
    }
    public interface EditDialogPopup{
        void updateResult(int[] input);
    }
}
