package com.myapplicationsqlite;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class CalculatorFragment extends Fragment implements View.OnClickListener {

    Button oneBtne, twoBtne, threeBtne, fourBtne, fiveBtne, sixBtne, sevenBtne, eightBtne, nineBtne, zeroBtne, plusBtne
            , minusBtne, equalBtne, divisionBtne, deleteBtne, foisBtne;
    TextView optV, resV, optList, headerTitle;

    String o = "";
    String render = "";
    String globalValue = "";
    String value = "", cleanValue = "";
    double val1, val2, result = 0, resultOpt = 0, newResultOpt = 0;
    ArrayList<String> listOpt = new ArrayList<String>();
    public CalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calculator, container, false);

        optV = rootView.findViewById(R.id.optV);
        resV = rootView.findViewById(R.id.resultV);
        optList = rootView.findViewById(R.id.textView);

        oneBtne = rootView.findViewById(R.id.oneBtn);
        twoBtne = rootView.findViewById(R.id.twoBtn);
        threeBtne = rootView.findViewById(R.id.threeBtn);
        fourBtne = rootView.findViewById(R.id.fourBtn);
        fiveBtne = rootView.findViewById(R.id.fiveBtn);
        sixBtne = rootView.findViewById(R.id.sixBtn);
        sevenBtne = rootView.findViewById(R.id.sevenBtn);
        eightBtne = rootView.findViewById(R.id.eightBtn);
        nineBtne = rootView.findViewById(R.id.nineBtn);
        zeroBtne = rootView.findViewById(R.id.zeroBtn);
        divisionBtne = rootView.findViewById(R.id.divisionBtn);
        minusBtne = rootView.findViewById(R.id.minusBtn);
        foisBtne = rootView.findViewById(R.id.foisBtn);
        plusBtne = rootView.findViewById(R.id.plusBtn);
        equalBtne = rootView.findViewById(R.id.equalBtn);
        deleteBtne = rootView.findViewById(R.id.deleteBtn);


        oneBtne.setOnClickListener(this);
        twoBtne.setOnClickListener(this);
        threeBtne.setOnClickListener(this);
        fourBtne.setOnClickListener(this);
        fiveBtne.setOnClickListener(this);
        sixBtne.setOnClickListener(this);
        sevenBtne.setOnClickListener(this);
        eightBtne.setOnClickListener(this);
        nineBtne.setOnClickListener(this);
        zeroBtne.setOnClickListener(this);
        divisionBtne.setOnClickListener(this);
        minusBtne.setOnClickListener(this);
        plusBtne.setOnClickListener(this);
        foisBtne.setOnClickListener(this);
        equalBtne.setOnClickListener(this);
        deleteBtne.setOnClickListener(this);


        return rootView;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {

        value = optV.getText() + v.getTag().toString();

        if (optV.getText().equals("0"))
            optV.setText(v.getTag().toString());
        else
            optV.setText(value);


        if (v.getId() == R.id.deleteBtn) {
            optV.setText("0");
            resV.setText("0");
            result = 0;
            val1 = 0;
            val2 = 0;
        }

        if (v.getId() == R.id.plusBtn) {
            o = "+";
            if (resV.getText().toString().equals("0")) {
                resV.setText(value);
                optV.setText("0");
            }
            else if(!resV.getText().toString().equals("0")) {

                if (result == 0) {

                    cleanValue = optV.getText().toString().replace(o, "");
                    cleanValue = cleanValue.replace("=", "");

                    globalValue = resV.getText().toString().replace(o, "");

                    val1 = Double.parseDouble(cleanValue);
                    val2 = Double.parseDouble(globalValue);


                    if (o.equals("+")) result = val2 + val1;
                    if (o.equals("-")) result = val2 - val1;
                    if (o.equals("*")) result = val2 * val1;
                    if (o.equals("/")) result = val2 / val1;

                    render = globalValue + " " + o + " " + cleanValue + " = " + result;
                    resV.setText(render);
                    optV.setText("");
                }

                if (result != 0) {

                    cleanValue = optV.getText().toString().replace(o, "");
                    cleanValue = cleanValue.replace("=", "");

                    //globalValue = resV.getText().toString().replace(o, "");

                    val1 = Double.parseDouble(cleanValue);
                    //val2 = Double.parseDouble(globalValue);


                    if (o.equals("+")) {
                        if (resultOpt == 0) {
                            resultOpt = result + val1;
                            render = result + " " + o + " " + cleanValue + " = " + resultOpt;
                        }
                        else {
                            newResultOpt = resultOpt + val1;
                            render = resultOpt + " " + o + " " + cleanValue + " = " + newResultOpt;
                        }
                    }
                    if (o.equals("-")){
                        if (resultOpt == 0) {
                            resultOpt = result - val1;
                            render = result + " " + o + " " + cleanValue + " = " + resultOpt;
                        }
                        else {
                            newResultOpt = resultOpt - val1;
                            render = resultOpt + " " + o + " " + cleanValue + " = " + newResultOpt;
                        }
                    }
                    if (o.equals("*")){
                        if (resultOpt == 0) {
                            resultOpt = result * val1;
                            render = result + " " + o + " " + cleanValue + " = " + resultOpt;
                        }
                        else {
                            newResultOpt = resultOpt * val1;
                            render = resultOpt + " " + o + " " + cleanValue + " = " + newResultOpt;
                        }
                    }
                    if (o.equals("/")){
                        if (resultOpt == 0) {
                            resultOpt = result / val1;
                            render = result + " " + o + " " + cleanValue + " = " + resultOpt;
                        }
                        else {
                            newResultOpt = resultOpt / val1;
                            render = resultOpt + " " + o + " " + cleanValue + " = " + newResultOpt;
                        }
                    }


                    resV.setText(render);
                    optV.setText("");

                    listOpt.add(render);
                }
                //------------------------------------
            }
        }
        if (v.getId() == R.id.divisionBtn) {
            o = "/";
            resV.setText(value);
            optV.setText("0");
        }
        if (v.getId() == R.id.foisBtn) {
            o = "*";
            resV.setText(value);
            optV.setText("0");
        }
        if (v.getId() == R.id.minusBtn) {
            o = "-";
            resV.setText(value);
            optV.setText("0");
        }

        if (v.getId() == R.id.equalBtn) {

            cleanValue = optV.getText().toString().replace(o, "");
            cleanValue = cleanValue.replace("=", "");

            globalValue = resV.getText().toString().replace(o, "");

            val1 = Double.parseDouble(cleanValue);
            val2 = Double.parseDouble(globalValue);


            if (o.equals("+"))
                result = val2 + val1;
            if (o.equals("-"))
                result = val2 - val1;
            if (o.equals("*"))
                result = val2 * val1;
            if (o.equals("/"))
                result = val2 / val1;

            render = globalValue + " " + o + " " + cleanValue + " = " + result;
            resV.setText(render);
            optV.setText("");

            listOpt.add("=> "+render + "\n");

            optList.setText(listOpt.toString());

        }



    }
}