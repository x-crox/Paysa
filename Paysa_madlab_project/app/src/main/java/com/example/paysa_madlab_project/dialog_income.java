package com.example.paysa_madlab_project;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class dialog_income extends AppCompatDialogFragment {

    private EditText amt;
    private EditText cat;
    private TextView edt;




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){


        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();

        View view=inflater.inflate(R.layout.layout_dialog_income,null);





        amt=view.findViewById(R.id.newpswd);
        cat=view.findViewById(R.id.category);
        edt=view.findViewById(R.id.date);





        Calendar calendar=Calendar.getInstance();

        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);


        edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(view.getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,null,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        DatePickerDialog.OnDateSetListener setListener =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                Calendar mCalender = Calendar.getInstance();
                mCalender.set(Calendar.YEAR, year);
                mCalender.set(Calendar.MONTH, month);
                mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String selectedDate =DateFormat.getDateInstance(DateFormat.FULL).format(mCalender.getTime());
                edt.setText(selectedDate);



                //System.out.println("entered print part");
                //month=month+1;
                //String date=dayOfMonth+"/"+month+"/"+year;
                //edt.setText(date);

            }
        };





        builder.setView(view);


        return builder.create();

    }



}
