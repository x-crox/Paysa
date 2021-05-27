package com.example.paysa_madlab_project;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Statement;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphFloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profile extends Fragment {

    ImageView im;
    NeumorphFloatingActionButton but;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profile.
     */
    // TODO: Rename and change types and number of parameters
    public static profile newInstance(String param1, String param2) {
        profile fragment = new profile();
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






        View frag = inflater.inflate(R.layout.fragment_profile, container, false);


        TextView user=frag.findViewById(R.id.paysaun);
        EditText newpswd=frag.findViewById(R.id.amountexp);
        EditText newpswd1=frag.findViewById(R.id.newpswd1);
        NeumorphButton confirm=frag.findViewById(R.id.save);

        user.setText(MainActivity.PaysaUsername);
        im = frag.findViewById(R.id.imageView1);
        but = frag.findViewById(R.id.profbut);
        System.out.println(MainActivity.PaysaUsername+" "+MainActivity.PaysaEmail);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = newpswd.getText().toString();
                String b = newpswd1.getText().toString();
                System.out.println(a + b + MainActivity.PaysaEmail);
                if (a.compareTo(b) != 0) {
                    Toast.makeText(getContext(),"Password doesn't match",Toast.LENGTH_SHORT).show();
                } else {
                    try{
                        Statement st = Server_configure.conn.createStatement();//MainActivity.conn.createStatement();
                        st.executeUpdate("UPDATE Users SET password = '" + a + "' WHERE email = '" + MainActivity.PaysaEmail + "'");
                        System.out.println("\n" + "UPDATE Users SET password = '" + a + "' WHERE email = '" + MainActivity.PaysaEmail + "'");
                        Toast.makeText(getContext(),"Success!",Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception sqlException){
                        sqlException.printStackTrace();
                    }
                }
            }
        });






        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                func();
            }});

        // Inflate the layout for this fragment
        return frag;
    }

    private void func() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

                String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE};

                requestPermissions(permissions,PERMISSION_CODE);





            } else {

                pickImagefromGallery();


            }


        } else {

            pickImagefromGallery();


        }
    }




    private void pickImagefromGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        switch (requestCode) {
            case PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {

                    pickImagefromGallery();

                } else {
                    Toast toast = Toast.makeText(getContext(), "permission denied", Toast.LENGTH_SHORT);
                    toast.show();
                }


        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== getActivity().RESULT_OK && requestCode==IMAGE_PICK_CODE){

            im.setImageURI(data.getData());

        }


    }
}