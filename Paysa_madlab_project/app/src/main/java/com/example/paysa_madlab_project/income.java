
package com.example.paysa_madlab_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import soup.neumorphism.NeumorphFloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link income#newInstance} factory method to
 * create an instance of this fragment.
 */
public class income extends Fragment {

    NeumorphFloatingActionButton b;
    String months[] = {"", "Jan", "Feb", "Mar", "Apr","May","Jun","Jul","Aug","Sep","Oct","Dec"};



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public income() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment income.
     */
    // TODO: Rename and change types and number of parameters
    public static income newInstance(String param1, String param2) {
        income fragment = new income();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View frag= inflater.inflate(R.layout.fragment_income, container, false);

        b=frag.findViewById(R.id.addbut);

        final Handler handler = new Handler();
        final int delay = 1000; // 1000 milliseconds == 1 second

        handler.postDelayed(new Runnable() {
            public void run() {
                GraphView graphView=frag.findViewById(R.id.graph);
                DataPoint data[] = new DataPoint[12];
                int i = 0;
                try{
                    Statement st = MainActivity.conn.createStatement();
                    ResultSet rs = st.executeQuery("SELECT MONTH(income_date) AS Income_month, SUM(amount) AS Total\n" +
                            "FROM Income\n" +
                            "WHERE email = '" + MainActivity.PaysaEmail + "'\n" +
                            "GROUP BY MONTH(income_date)\n" +
                            "ORDER BY MONTH(income_date) ASC");
                    ResultSetMetaData rsmd = rs.getMetaData();

                    while (rs.next()) {
                        Integer month = Integer.parseInt(rs.getString("Income_month"));
                        Double amount = rs.getDouble("Total");
                        System.out.println(month + " " + amount);
                        data[i] = new DataPoint(month.doubleValue(), amount.doubleValue());
                        i++;
                    }
                }

                catch (Exception sqlException){
                    sqlException.printStackTrace();
                }

                LineGraphSeries<DataPoint>series=new LineGraphSeries<DataPoint>(data);
                graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                    @Override
                    public String formatLabel(double value, boolean isValueX) {
                        if (isValueX) {
                            // show normal x values
                            return months[(int) value];
                        } else {
                            // show currency for y values
                            return super.formatLabel(value, isValueX) + " $";
                        }
                    }
                });

                graphView.removeAllSeries();
                graphView.addSeries(series);

                handler.postDelayed(this, delay);
            }
        }, delay);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialog();
            }
        });

        return frag;

    }

    private void opendialog() {
        dialog_income page=new dialog_income();
        MainActivity.di = page;
        page.show(getFragmentManager(),"income popup");
    }
}