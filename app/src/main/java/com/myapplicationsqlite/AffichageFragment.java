package com.myapplicationsqlite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AffichageFragment extends Fragment {

    ListView lvAffichage;
    TextView count;
    Button addUser, updateBtn, deleteBtn;

    public AffichageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_affichage, container, false);

        lvAffichage = rootView.findViewById(R.id.lvAffichage);
        addUser = rootView.findViewById(R.id.bAjouter);
        updateBtn = rootView.findViewById(R.id.bModifier);
        deleteBtn = rootView.findViewById(R.id.bSupprimer);
        count = rootView.findViewById(R.id.userCount);

        DatabaseHandler db = new DatabaseHandler(getActivity());


        ArrayList<Client> clientList = new ArrayList<>();
        clientList.addAll(db.getAllClients());

        ArrayAdapter<Client> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, clientList);

        lvAffichage.setAdapter(adapter);
        count.setText(clientList.stream().count() + " Clients");
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container, SignupFragment.class, null)
                        .commit();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getActivity().getSupportFragmentManager().beginTransaction()
                       .setReorderingAllowed(true)
                       .replace(R.id.fragment_container, UpdateFragment.class, null)
                       .commit();
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container, DeleteFragment.class, null)
                        .commit();
            }
        });


        return rootView;
    }
}