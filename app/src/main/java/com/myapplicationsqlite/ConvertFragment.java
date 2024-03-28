package com.myapplicationsqlite;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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



        return rootView;
    }
}