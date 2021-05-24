package com.example.paysa_madlab_project;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.sql.SQLException;
import java.util.ArrayList;

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
    ArrayList<Group_detail> gd;
    GridView group_grid;
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

            /*For testing purposes*/
            group_grid=group_frag.findViewById(R.id.group_grid);
            gd=new ArrayList<Group_detail>();
            gd.add(new Group_detail("1234",R.drawable.ic_paysa_logo));
            gd.add(new Group_detail("2134",R.drawable.ic_group_icon));
            gd.add(new Group_detail("1235",R.drawable.ic_participant_add_icon));
            GroupAdapter groupAdapter=new GroupAdapter(getContext(),gd);
            group_grid.setAdapter(groupAdapter);
        }
        /*catch(SQLException sqlException){
            sqlException.printStackTrace();
        }*/
        catch(Exception e){
            e.printStackTrace();
        }

        return group_frag;
    }
}