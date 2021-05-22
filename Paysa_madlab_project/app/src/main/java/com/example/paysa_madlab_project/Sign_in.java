package com.example.paysa_madlab_project;

import android.content.Intent;
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

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import soup.neumorphism.NeumorphButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Sign_in#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Sign_in extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    NeumorphButton signup;
    Button login;
    EditText username,password;
    public Sign_in() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Sign_in.
     */
    // TODO: Rename and change types and number of parameters
    public static Sign_in newInstance(String param1, String param2) {
        Sign_in fragment = new Sign_in();
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
        View frag=inflater.inflate(R.layout.fragment_blank, container, false);
        signup=frag.findViewById(R.id.signup);
        login=frag.findViewById(R.id.login);
        username=frag.findViewById(R.id.username);
        password=frag.findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    // signin
                    /*
                        {
                            email: String
                            password: String
                        }
                    */
//                    String email = username.getText().toString();
                    JSONObject jo = new JSONObject();
                    String email=username.getText().toString();
                    String _password=password.getText().toString();
                    // add simple attributes like string or integer
                    jo.put("email", email);
                    jo.put("password", _password);
                    String jsonData = JSONValue.toJSONString(jo);

                    // setup to make JSON POST request
                    String url =getString(R.string.signin_url);
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
                            auth: Boolean
                            msg: String
                        }
                    */
                    Boolean err = (Boolean) jsonObject.get("err");
                    Boolean auth = (Boolean) jsonObject.get("auth");
                    String msg = (String) jsonObject.get("msg");


                    if (auth == true) {
                        //
                        Toast.makeText(getContext(),"Success",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(frag.getContext(),homepage.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {

                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Sign_up();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                System.out.print("In sign in activity before replacement");
                fragmentTransaction.replace(R.id.main_activity, fragment);
                System.out.print("In sign in activity after replacement");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                System.out.print("In sign in activity before commit");
            }
        });
        return frag;
    }
}

