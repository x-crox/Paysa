package com.example.paysa_madlab_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.*;
import java.util.*;
import java.net.*;
import org.json.simple.*;

import soup.neumorphism.NeumorphButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Sign_up#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Sign_up extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    NeumorphButton done,otp_send;
    EditText _email,_fullname,_password,_otp;
    Button cancel;
    public Sign_up() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Sign_up.
     */
    // TODO: Rename and change types and number of parameters
    public static Sign_up newInstance(String param1, String param2) {
        Sign_up fragment = new Sign_up();
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
        View frag = inflater.inflate(R.layout.fragment_sign_up, container, false);
        _email=frag.findViewById(R.id.new_username);
        otp_send=frag.findViewById(R.id.otp_send);
        _fullname=frag.findViewById(R.id.fullname);
        _password=frag.findViewById(R.id.new_password);
        _otp=frag.findViewById(R.id.otp);
        done=frag.findViewById(R.id.done);
        cancel=frag.findViewById(R.id.cancel);
        done.setOnClickListener(this::onClick);
        cancel.setOnClickListener(this::onClick);
        otp_send.setOnClickListener(this::onClick);
        return frag;
    }

    @Override
    public void onClick(View v){
        FragmentManager manager=getActivity().getSupportFragmentManager();

        String email = _email.getText().toString();
        String fullname = _fullname.getText().toString();
        String password = _password.getText().toString();
        switch(v.getId()){
            case R.id.otp_send:
                System.out.println("done");
                Toast.makeText(this.getContext(),"done clicked",Toast.LENGTH_SHORT).show();
                //add the data to the database
                try {
                    // signup
                    /*
                        {
                            email: String
                        }
                    */
//                    String email = username.getText().toString();
                    JSONObject jo = new JSONObject();

                    // add simple attributes like string or integer
                    jo.put("email", email);
                    String jsonData = JSONValue.toJSONString(jo);

                    // setup to make JSON POST request
                    String url = "http://15.207.249.112:8000/signup";
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                    // start sending
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                    con.setRequestProperty("Content-Type","application/json");
                    con.setDoOutput(true);
                    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                    wr.writeBytes(jsonData);
                    wr.flush();
                    wr.close();

                    // read the response body
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String output;
                    StringBuffer response = new StringBuffer();
                    while ((output = in.readLine()) != null) {
                        response.append(output);
                    }
                    in.close();

                    // parse JSON response sent by server
                    System.out.println(response.toString());
                    Object object = JSONValue.parse(response.toString());
                    JSONObject jsonObject = (JSONObject) object;

                    // refer the response received, the type should be infered from the response
                    /*

                        {
                            err: Boolean
                            msg: String
                        }
                    */
                    Boolean err = (Boolean) jsonObject.get("err");
                    String msg = (String) jsonObject.get("msg");


                    if (err == false) {
                        //
                        Toast.makeText(this.getContext(),"OTP sent to your email",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this.getContext(),msg,Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {

                }


                //Toast.makeText(this.getContext(),"Account created",Toast.LENGTH_SHORT).show();
                break;

            case R.id.done:
                try {
                    // verifyOTP
                    /*
                        {
                            email: String
                            fullname: String
                            password: String
                            otp: String
                        }
                    */
//                    String email = username.getText().toString();
                    JSONObject jo = new JSONObject();

                    // add simple attributes like string or integer
                    String otp = _otp.getText().toString();
                    jo.put("email", email);
                    jo.put("fullname", fullname);
                    jo.put("password", password);
                    jo.put("otp", otp);
                    String jsonData = JSONValue.toJSONString(jo);

                    // setup to make JSON POST request
                    String url = "http://15.207.249.112:8000/verifyOTP";
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                    // start sending
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                    con.setRequestProperty("Content-Type","application/json");
                    con.setDoOutput(true);
                    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                    wr.writeBytes(jsonData);
                    wr.flush();
                    wr.close();

                    // read the response body
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String output;
                    StringBuffer response = new StringBuffer();
                    while ((output = in.readLine()) != null) {
                        response.append(output);
                    }
                    in.close();

                    // parse JSON response sent by server
                    System.out.println(response.toString());
                    Object object = JSONValue.parse(response.toString());
                    JSONObject jsonObject = (JSONObject) object;

                    // refer the response received, the type should be infered from the response
                    /*

                        {
                            err: Boolean
                            msg: String
                        }
                    */
                    Boolean err = (Boolean) jsonObject.get("err");
                    String msg = (String) jsonObject.get("msg");


                    if (err == false) {
                        //
                        Toast.makeText(this.getContext(),"Success",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this.getContext(),msg,Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {

                }
                break;
        }

        if(v.getId()!=R.id.otp_send){
            manager.beginTransaction().replace(R.id.main_activity,new Sign_in()).commit();
        }

    }
}

/**/