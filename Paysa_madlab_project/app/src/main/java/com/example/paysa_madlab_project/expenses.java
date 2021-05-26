package com.example.paysa_madlab_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import soup.neumorphism.NeumorphFloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link expenses#newInstance} factory method to
 * create an instance of this fragment.
 */
public class expenses extends Fragment {


    NeumorphFloatingActionButton b;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment expenses.
     */
    // TODO: Rename and change types and number of parameters
    public static expenses newInstance(String param1, String param2) {
        expenses fragment = new expenses();
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
        View frag= inflater.inflate(R.layout.fragment_expenses, container, false);
        b=frag.findViewById(R.id.addebut);


        PieChart chart=frag.findViewById(R.id.chart1);

        final Handler handler = new Handler();
        final int delay = 1000; // 1000 milliseconds == 1 second

        handler.postDelayed(new Runnable() {
            public void run() {
                List<PieEntry> entries = new ArrayList<>();
                List<Integer> colors = new ArrayList<>();

                float total = 0f;
                HashMap<String, Double> cat = new HashMap<String, Double>();
                try{
                    Statement st = MainActivity.conn.createStatement();
                    ResultSet rs = st.executeQuery("SELECT category, SUM(amount) AS amount\n" +
                            "FROM Expense\n" +
                            "WHERE email = '" + MainActivity.PaysaEmail + "'\n" +
                            "GROUP BY category;");
                    ResultSetMetaData rsmd = rs.getMetaData();

                    while (rs.next()) {
                        cat.put(rs.getString("category"), rs.getDouble("amount"));
                        total += rs.getDouble("amount");
                    }
                } catch (Exception sqlException){
                    sqlException.printStackTrace();
                }

                colors.add(new Integer(R.color.actual_dark_green));
                colors.add(new Integer(R.color.actual_green));
                colors.add(new Integer(R.color.colorAccent));
                colors.add(new Integer(R.color.design_dark_default_color_shadow_dark));
                colors.add(new Integer(R.color.design_default_color_error));
                for (String key: cat.keySet()) {
                    entries.add(new PieEntry((cat.get(key).floatValue() / total) * 100.0f, key));
                }
                PieDataSet set = new PieDataSet(entries, "Expense");
                PieData data = new PieData(set);
                set.setColors(colors);
                chart.setData(data);
                chart.invalidate();

                Legend legend= chart.getLegend();
                legend.setEnabled(false);

                Description des = chart.getDescription();
                des.setEnabled(false);

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

        dialog_expense page=new dialog_expense();
        MainActivity.de = page;
        page.show(getFragmentManager(),"income popup");
}}