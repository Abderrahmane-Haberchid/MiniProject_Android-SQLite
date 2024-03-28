package com.myapplicationsqlite;

import static com.myapplicationsqlite.SigininFragment.isLoggedin;

import android.media.Image;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SignupFragment extends Fragment {

    EditText etName2, etEmail2, etPassword2;
    TextView backBtn;
    Button bValider2;
    DatabaseHandler db;


    public SignupFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_signup, container, false);

        etName2 = rootView.findViewById(R.id.etName2);
        etEmail2 = rootView.findViewById(R.id.etEmail2);
        etPassword2 = rootView.findViewById(R.id.etPassword2);
        bValider2 = rootView.findViewById(R.id.bValider2);
        backBtn = rootView.findViewById(R.id.backToSignin);


        db = new DatabaseHandler(getActivity());

        if(isLoggedin){
            etName2.setText("Enter a name...");
            etEmail2.setText("Enter an email...");
            etPassword2.setText("Enter a password...");
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoggedin) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragment_container, AffichageFragment.class, null)
                            .commit();
                }
                else {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragment_container, SigininFragment.class, null)
                            .commit();
                }


            }
        });

        bValider2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etName2.getText().toString().equals("") &&
                    !etEmail2.getText().toString().equals("") &&
                    !etPassword2.getText().toString().equals(""))
                {
                    Client client = new Client(etName2.getText().toString(), etEmail2.getText().toString(), etPassword2.getText().toString());
                    db.addClient(client);
                    Toast.makeText(getActivity(), "User added successfuly", Toast.LENGTH_LONG).show();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragment_container, AffichageFragment.class, null)
                            .commit();
                }
                else
                    Toast.makeText(getActivity(), "All fields are required !", Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }
}