package com.example.denis.finansave;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by denis on 25/04/2016.
 */
public class PickerDialogs extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    EditText etdata;

    public PickerDialogs(View view){
        etdata = (EditText)view;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendario = Calendar.getInstance();
        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        //DatePickerDialog dialog;
        return new DatePickerDialog(getActivity(), this, ano, mes, dia);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + monthOfYear + "/" +year;
        etdata.setText(date);
    }

}
