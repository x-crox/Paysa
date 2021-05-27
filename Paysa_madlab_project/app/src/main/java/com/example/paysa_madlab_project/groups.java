package com.example.paysa_madlab_project;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import soup.neumorphism.NeumorphImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link groups#newInstance} factory method to
 * create an instance of this fragment.
 */
public class groups extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    static ArrayList<Group_detail> gd;
    static GridView group_grid;
    NeumorphImageButton add_group_plus;
    public groups() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment groups.
     */
    // TODO: Rename and change types and number of parameters
    public static groups newInstance(String param1, String param2) {
        groups fragment = new groups();
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
        View group_frag=inflater.inflate(R.layout.fragment_groups, container, false);


        try {
            /*if(Server_configure.conn==null){
                throw new Exception("Connection variable is null");
            }
            Server_configure.conn.createStatement();*/
            /*
            *   SQL statements to be inserted to to retrieve the group id and the corresponding
            *   image.
            *   The group id, images (more data to be inserted, will be notified later)
            *   are encapsulated into the Group_detail class.
            *
            * */
            add_group_plus=group_frag.findViewById(R.id.add_group_plus);
            add_group_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    open_group_dialog();
                }
            });
            /*For testing purposes*/
            group_grid=group_frag.findViewById(R.id.group_grid);
            gd=new ArrayList<Group_detail>();
            try{
                Statement st = MainActivity.conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT group_name FROM GroupMembers WHERE email = '" + MainActivity.PaysaEmail + "'");
//                ResultSetMetaData rsmd = rs.getMetaData();

                while (rs.next()) {
                    System.out.println(rs.getString("group_name"));
                    gd.add(new Group_detail(rs.getString("group_name")));
                }
            }
            catch (Exception sqlException){
                sqlException.printStackTrace();
            }
            GroupAdapter groupAdapter=new GroupAdapter(getContext(),gd);
            group_grid.setAdapter(groupAdapter);

            group_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent(getContext(),Group_page.class);
                    Group_page.prev_view=view;
                    startActivity(intent);
                }
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return group_frag;
    }

    public void open_group_dialog(){
        dialog_group_create grp_dialog=new dialog_group_create();
        MainActivity.dgc = grp_dialog;
        grp_dialog.show(getFragmentManager(),"income popup");
    }
}