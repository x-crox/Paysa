package com.example.paysa_madlab_project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;



import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import soup.neumorphism.NeumorphButton;

public class dialog_income extends AppCompatDialogFragment {

    private EditText amt;
    String date = null,month = null;
    String findate;
    String amount=null;  //private EditText cat;
   // private TextView edt;




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){


        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();

        View view=inflater.inflate(R.layout.layout_dialog_income,null);


        amt=view.findViewById(R.id.amountinc);
       CalendarView calendarView=view.findViewById(R.id.simpleCalendarView);
        NeumorphButton save=view.findViewById(R.id.save);
        Button cancel=view.findViewById(R.id.cancelpop);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                i1=i1+1;
                if(i2<=9)
                {
                    date="0"+i2;
                } else {
                    date = new Integer(i2).toString();
                }

                if(i1<=9)
                {
                    month="0"+i1;
                } else {
                    month = new Integer(i1).toString();
                }


                findate=i+"-"+month+"-"+date;
            }
        });









        save.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                amount = amt.getText().toString();
                try{
                    Statement st = MainActivity.conn.createStatement();
                    System.out.println("INSERT INTO Income VALUES('" + MainActivity.PaysaEmail + "', " + amount + ", '" +findate + "')");
                    int count = st.executeUpdate("INSERT INTO Income VALUES('" + MainActivity.PaysaEmail + "', '" + amount + "', '" + findate + "')");
                    if (count > 0)
                    {  Toast.makeText(getContext(), "Income added!",Toast.LENGTH_SHORT).show();

//
//                        FragmentTransaction fr = getFragmentManager().beginTransaction();
//                      fr.replace(R.id.incometab, new income());
//                        fr.commit();
                        MainActivity.di.dismiss();
                    }
                    else
                        Toast.makeText(getContext(), "Something went wrong!",Toast.LENGTH_SHORT).show();
                }

                catch (Exception sqlException){
                    sqlException.printStackTrace();
                }
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.di.dismiss();
            }
        });







        builder.setView(view);


        return builder.create();

    }



}
