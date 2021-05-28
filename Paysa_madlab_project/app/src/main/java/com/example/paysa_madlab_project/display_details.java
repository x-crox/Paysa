package com.example.paysa_madlab_project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.sql.ResultSet;
import java.sql.Statement;

public class display_details  extends AppCompatDialogFragment {
    static String details=null;
    TextView text_id, status_id;
    ImageView owe_icon;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();

        View view=inflater.inflate(R.layout.layout_display_details,null);
        text_id=view.findViewById(R.id.participant_id);
        String name = null;
        String current_group = Group_detail.getGroup_name(Group_page.prev_view);
        try{
            Statement st = MainActivity.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT fullname FROM Users WHERE email = '" + details + "'");

            while (rs.next()) {
                name = rs.getString("fullname");
                text_id.setText(name);
            }
        } catch (Exception sqlException){
            sqlException.printStackTrace();
        }
        status_id = view.findViewById(R.id.status_id);
        owe_icon=view.findViewById(R.id.owe_icon);
        double X = 0.0; // how much you owe the person (you paid other guy)
        double Y = 0.0; // how much the other guy owes you (other guy paid you)

        try{
            Statement st = MainActivity.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(amount) AS amount\n" +
                    "FROM PaymentTracking\n" +
                    "WHERE payor = '" + details + "' AND payee = '" + MainActivity.PaysaEmail + "' AND group_name = '" + current_group + "'");

            while (rs.next()) {
                X = rs.getDouble("amount");
            }
        } catch (Exception sqlException){
            sqlException.printStackTrace();
        }

        try{
            Statement st = MainActivity.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(amount) AS amount\n" +
                    "FROM PaymentTracking\n" +
                    "WHERE payor = '" + MainActivity.PaysaEmail + "' AND payee = '" + details + "' AND group_name = '" + current_group + "'");

            while (rs.next()) {
                Y = rs.getDouble("amount");
            }
        } catch (Exception sqlException){
            sqlException.printStackTrace();
        }

        if (X == Y) {
            if (MainActivity.PaysaEmail.compareTo(details) != 0) {
                status_id.setText("All squared off!");
                owe_icon.setImageResource(R.drawable.ic_square_off);
            }
        } else if (X < Y) {
            status_id.setText("Rs. "+Math.abs(Y - X));//name + " owes me " + Math.abs(Y - X));
            owe_icon.setImageResource(R.drawable.ic_you_owe);
        } else {
            status_id.setText("Rs."+Math.abs(Y - X));//"You owe " + name + " " + Math.abs(Y - X));
            owe_icon.setImageResource(R.drawable.ic_i_owe);
        }

        builder.setView(view);
        return builder.create();
    }

    public static void setDetail(String strdetails){
        details=strdetails;
    }
}
