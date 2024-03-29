package com.myapplicationsqlite;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class ConvertFragment extends Fragment {

    TextInputLayout textInputLayoutFrom, textInputLayoutTo;
    MaterialAutoCompleteTextView from, to;
    EditText etValue;
    TextView tvResult;
    Button btnConvert;
    String fromValue, toValue;
    int degree, resultValue;

    public ConvertFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_convert, container, false);

        from = rootView.findViewById(R.id.itemFrom);
        to = rootView.findViewById(R.id.itemTo);
        textInputLayoutFrom = rootView.findViewById(R.id.inputLayoutFrom);
        textInputLayoutTo = rootView.findViewById(R.id.inputLayoutTo);
        etValue = rootView.findViewById(R.id.etValue);
        tvResult = rootView.findViewById(R.id.tvResult);
        btnConvert = rootView.findViewById(R.id.btnConvert);


        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                degree = Integer.parseInt(etValue.getText().toString());
                fromValue = from.getText().toString();
                toValue = to.getText().toString();

                if (fromValue.equals("Celsius")){
                    switch (toValue) {
                        case "Farenheit":
                            resultValue = (int) (degree * 1.8) + 32;
                            break;
                        case "Kelvin":
                            resultValue = (int) (degree + 273.15);
                            break;
                        case "Celsius":
                            resultValue = (int) degree;
                            break;
                    }
                }
                if (fromValue.equals("Farenheit")){
                    switch (toValue) {
                        case "Farenheit":
                            resultValue = (int) degree;
                            break;
                        case "Kelvin":
                            resultValue = (int) ((int) (degree - 32) * 5/9 + 273.15);
                            break;
                        case "Celsius":
                            resultValue = (int) ((degree-32)*5)/9;
                            break;
                    }
                }
                if (fromValue.equals("Kelvin")){
                    switch (toValue) {
                        case "Farenheit":
                            resultValue = (int) ((int) (degree - 273.15) * 1.8 + 32);
                            break;
                        case "Kelvin":
                            resultValue = (int) degree;
                            break;
                        case "Celsius":
                            resultValue = (int) ((int) degree-273.15);
                            break;
                    }
                }

                tvResult.setText(String.valueOf(resultValue));

                //Toast.makeText(getActivity(), toValue + "   " + fromValue, Toast.LENGTH_LONG).show();
            }
        });



        return rootView;
    }
}