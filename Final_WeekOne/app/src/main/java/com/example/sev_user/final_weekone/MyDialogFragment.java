package com.example.sev_user.final_weekone;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sev_user.final_weekone.adapter.SpinnerAdapter;

/**
 * Created by toan on 14-Sep-16.
 */
public class MyDialogFragment extends DialogFragment {

    Spinner spinnerSupplier, spinnerBalance, spinnerSize, spinnerColor;
    Button btnFilter;
    String[] years = {"Chon nam", "1996", "1997", "1998"};
    TextView textView;

    public interface EditDialogListener {
        void updateResult(Bundle data);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View dialogView = inflater.inflate(R.layout.filter, container, false);
        btnFilter = (Button) dialogView.findViewById(R.id.btn_filter);
        spinnerSupplier = (Spinner) dialogView.findViewById(R.id.sp_supplier);
        spinnerBalance = (Spinner) dialogView.findViewById(R.id.sp_balance);
        spinnerColor = (Spinner) dialogView.findViewById(R.id.sp_color);
        spinnerSize = (Spinner) dialogView.findViewById(R.id.sp_size);

        SpinnerAdapter adapter = new SpinnerAdapter(getActivity().getApplicationContext(), R.layout.spinner_layout);
        adapter.addAll(years);
        spinnerBalance.setAdapter(adapter);
        //spinnerBalance.setSelection(adapter.getCount());

        ArrayAdapter<String> foodadapter = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner_layout){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    Log.d("toan.tv", "here");
                    textView = (TextView)v.findViewById(R.id.spin_text);
                    textView.setText("this is hint text");
                }
                return v;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1;
            }
        };
        foodadapter.addAll(years);

        foodadapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spinnerSupplier.setAdapter(foodadapter);
        spinnerSupplier.setSelection(foodadapter.getCount()-1);

        //spinnerBalance.setAdapter(foodadapter);
        spinnerSize.setAdapter(foodadapter);
        spinnerColor.setAdapter(foodadapter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strSupplier = spinnerSupplier.getSelectedItem().toString();
                String strBalance = spinnerBalance.getSelectedItem().toString();
                String strSize = spinnerSize.getSelectedItem().toString();
                String strColor = spinnerColor.getSelectedItem().toString();

                Bundle data = new Bundle();
                data.putString("1", strBalance);
                EditDialogListener activity = (EditDialogListener) getActivity();
                activity.updateResult(data);
                dismiss();
            }
        });
        return dialogView;
    }

}

