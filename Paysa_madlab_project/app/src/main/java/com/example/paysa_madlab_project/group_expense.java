package com.example.paysa_madlab_project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinnerListener;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.transform.Result;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphImageButton;

public class group_expense extends AppCompatDialogFragment{
    private static final String TAG ="spinner";
    NeumorphButton add_amount;
    Button cancel_add_amount;
    EditText amount_spent;
    //static TextView amt_in_debt;

     List<String> list= new ArrayList<String>();

     ArrayList<String> selected = new ArrayList<String>();
    List<KeyPairBoolData> listArray = new ArrayList<KeyPairBoolData>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.layout_group_expense, null);

        String current_group = Group_detail.getGroup_name(Group_page.prev_view);
        try{
            Statement st = MainActivity.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT email FROM GroupMembers WHERE group_name ='" + current_group + "'");

            while (rs.next()) {
                list.add(rs.getString("email"));
            }
        }

        catch (Exception sqlException){
            sqlException.printStackTrace();
        }

        for(int i=0; i<list.size(); i++) {
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(i+1);
            h.setName(list.get(i));
            h.setSelected(false);
            listArray.add(h);
        }




        /**
         * Search MultiSelection Spinner (With Search/Filter Functionality)
         *
         *  Using MultiSpinnerSearch class
         */
        MultiSpinnerSearch multiSelectSpinnerWithSearch = view.findViewById(R.id.multipleItemSelectionSpinner);

        // Pass true If you want searchView above the list. Otherwise false. default = true.
        multiSelectSpinnerWithSearch.setSearchEnabled(true);

        // A text that will display in search hint.
        multiSelectSpinnerWithSearch.setSearchHint("split among");

        // Set text that will display when search result not found...
        multiSelectSpinnerWithSearch.setEmptyTitle("Not Data Found!");

        // If you will set the limit, this button will not display automatically.
        multiSelectSpinnerWithSearch.setShowSelectAllButton(true);

        //A text that will display in clear text button
        multiSelectSpinnerWithSearch.setClearText("Close & Clear");

        // Removed second parameter, position. Its not required now..
        // If you want to pass preselected items, you can do it while making listArray,
        // pass true in setSelected of any item that you want to preselect
        multiSelectSpinnerWithSearch.setItems(listArray, new MultiSpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> items) {
                ArrayList<String> tmp = new ArrayList<String>();
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isSelected()) {
                        tmp.add(items.get(i).getName());
                    }
                }
                selected = tmp;
            }
        });






        add_amount=view.findViewById(R.id.add_amount);
        cancel_add_amount=view.findViewById(R.id.cancel_add_amount);
        amount_spent=view.findViewById(R.id.amount_spent);

        add_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount_spent.getText().toString().length()>0){
                    double amount = Double.parseDouble(amount_spent.getText().toString());
                    double equal_amount = 0.0;
                    int length = selected.size();
                    if (length > 0) {
                        equal_amount = amount / (length * 1.0);
                    }
                    String equ = String.format("%.2f", equal_amount);
                    try{
                        Statement st = MainActivity.conn.createStatement();
                        for (String x: selected) {
                            if (x.compareTo(MainActivity.PaysaEmail) != 0) {
                                System.out.println("INSERT INTO PaymentTracking VALUES('" + current_group + "', '"+ MainActivity.PaysaEmail +"','" + x +"', " + equ +")");
                                int count = st.executeUpdate("INSERT INTO PaymentTracking VALUES('" + current_group + "', '"+ MainActivity.PaysaEmail +"','" + x +"', " + equ +")");
                                if (count > 0) {
                                    ;
                                } else {
                                    Toast.makeText(getContext(), "Failed to make payment to " + x, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    } catch (Exception sqlException){
                        sqlException.printStackTrace();
                    }
                    Group_page.grp_expense.dismiss();
                }
            }
        });
        cancel_add_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Group_page.grp_expense.dismiss();
            }
        });

        builder.setView(view);
        return builder.create();
    }
}
