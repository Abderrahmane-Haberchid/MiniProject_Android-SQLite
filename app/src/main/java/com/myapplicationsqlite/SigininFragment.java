package com.myapplicationsqlite;

import static androidx.core.app.ActivityCompat.recreate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SigininFragment extends Fragment {

    EditText etLogin, etPassword;
    Button bLogin, bSignUp;

    public static boolean isLoggedin = false;
    public SigininFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_signin, container, false);

        etLogin = rootView.findViewById(R.id.etLogin);
        etPassword = rootView.findViewById(R.id.etPassword);
        bLogin = rootView.findViewById(R.id.bLogin);
        bSignUp = rootView.findViewById(R.id.bSignUp);

        DatabaseHandler db = new DatabaseHandler(getActivity());

            bLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (etLogin.getText().toString().equals("") || etPassword.getText().toString().equals(""))
                        Toast.makeText(getActivity(), "Login and passwoord are required", Toast.LENGTH_LONG).show();
                    else {
                        if (db.login(etLogin.getText().toString(), etPassword.getText().toString())) {
                            isLoggedin = true;
                            startActivity(new Intent(getActivity(), MainActivity.class));
//                            getActivity().getSupportFragmentManager().beginTransaction()
//                                    .setReorderingAllowed(true)
//                                    .replace(R.id.fragment_container, AffichageFragment.class, null)
//                                    .commit();
                        }
                        else
                            Toast.makeText(getActivity(), "Login ou Mot de passe incorrect", Toast.LENGTH_LONG).show();
                    }
                }
            });

        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container, SignupFragment.class, null)
                        .commit();
            }
        });

        return rootView;
    }


    }
