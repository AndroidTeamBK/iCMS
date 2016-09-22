package com.example.sev_user.final_weekone.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by toan on 14-Sep-16.
 */
public class SpinnerAdapter extends ArrayAdapter<String> {
    Context context;

    public SpinnerAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    @Override
    public boolean isEnabled(int position) {
        if (position == 0)
            return false;
        else
            return true;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        TextView tv = (TextView) view;
        if(position == 0){
            // Set the hint text color gray
            tv.setTextColor(Color.GRAY);
        }
        else {
            tv.setTextColor(Color.BLACK);
        }
        return view;
    }
}
