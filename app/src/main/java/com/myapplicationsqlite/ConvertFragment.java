package com.myapplicationsqlite;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ConvertFragment extends Fragment {

    String[] items = {"Celsius", "Farhneit", "Kelvin"};

    ArrayAdapter<String> arrayAdapter;
    MultiAutoCompleteTextView from, to;
    public ConvertFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_convert, container, false);

        from = view.findViewById(R.id.itemFrom);
        to = view.findViewById(R.id.itemTo);

        arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.fragment_convert, items);

        from.setAdapter(arrayAdapter);
        to.setAdapter(arrayAdapter);
        from.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(), "Clicked item " +item, Toast.LENGTH_LONG).show();
            }
        });
        to.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(), "Clicked item " +item, Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }
}