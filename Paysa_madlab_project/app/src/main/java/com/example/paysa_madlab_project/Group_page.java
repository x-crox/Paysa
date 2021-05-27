package com.example.paysa_madlab_project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphImageButton;

public class Group_page extends AppCompatActivity{
    NeumorphImageButton participant_add_button;
    static GridView participant_grid;
    static View prev_view;
    static dialog_participant_add dpa=null;
    static display_details disp_details=null;
    static group_expense grp_expense=null;
    TextView amt_in_debt;
    NeumorphImageButton add_group_expense;
    static HashMap<View,String> view_to_participant_detail=new HashMap<View,String>();

    ArrayList<String> demo=new ArrayList<String>();
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_page);



        participant_add_button=findViewById(R.id.participant_add_button);
        participant_grid=findViewById(R.id.participant_grid);
        amt_in_debt=findViewById(R.id.amt_in_debt);
        add_group_expense=findViewById(R.id.add_expense_button);

        String group_name=Group_detail.getGroup_name(prev_view);
        try{
            Statement st = MainActivity.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT email FROM GroupMembers WHERE group_name = '" + Group_detail.getGroup_name(prev_view) + "'");
            while (rs.next()) {
                demo.add(rs.getString("email"));
            }
        }
        catch (Exception sqlException){
            sqlException.printStackTrace();
        }
        Group_detail.view_to_participant.replace(prev_view,Group_detail.getParticipants(prev_view),demo);
        participant_grid.setAdapter(new participant_add_adapter(getApplicationContext(),Group_detail.getParticipants(prev_view)));

        participant_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();
            }
        });

        participant_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                display_details dd=new display_details();
                display_details.setDetail(view_to_participant_detail.get(view));
                disp_details=dd;
                dd.show(getSupportFragmentManager(),"participant_details");
            }
        });

        add_group_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                group_expense ge=new group_expense();
                grp_expense=ge;
                group_expense.amt_in_debt=amt_in_debt;
                ge.show(getSupportFragmentManager(),"launch group_expense dialog");
            }
        });
    }

    private void opendialog(){
        dialog_participant_add d_pa=new dialog_participant_add();
        dpa=d_pa;
        d_pa.show(getSupportFragmentManager(),"open_participant_add_dialog");
    }
}