package com.myapplicationsqlite;

import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteFragment extends Fragment {

    TextView name, email, backBtn;
    Button deleteBtn, searchBtn;
    EditText searchField;
    public DeleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_delete, container, false);

        DatabaseHandler db = new DatabaseHandler(getActivity());

        name = rootView.findViewById(R.id.tvName);
        email = rootView.findViewById(R.id.tvEmail);
        searchField = rootView.findViewById(R.id.tvSearch);
        searchBtn = rootView.findViewById(R.id.searchButton);
        deleteBtn = rootView.findViewById(R.id.deleteBtn);
        backBtn = rootView.findViewById(R.id.backToAffichage);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container, AffichageFragment.class, null)
                        .commit();
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!searchField.getText().toString().equals("")) {
                    Client client = db.findByEmail(searchField.getText().toString());
                    if(client != null) {
                        name.setText(client.getName());
                        email.setText(client.getEmail());
                    }
                    else
                        Toast.makeText(getActivity(), "User not found !", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getActivity(), "Please enter an email first", Toast.LENGTH_LONG).show();

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText() == "" || email.getText() == "")
                    Toast.makeText(getActivity(), "No user selected", Toast.LENGTH_LONG).show();
                else
                {
                    db.delete(searchField.getText().toString());
                    Toast.makeText(getActivity(), "User Deleted !", Toast.LENGTH_LONG).show();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragment_container, AffichageFragment.class, null)
                            .commit();
                }
            }
        });

        return rootView;
    }
}