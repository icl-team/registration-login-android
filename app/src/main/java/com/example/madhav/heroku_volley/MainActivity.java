package com.example.madhav.heroku_volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //register

    private EditText email_et,pw_et,pwc_et;
    private RequestQueue mQueue;
    String em,pw,pwc;

    //login
    private EditText email_log,pw_log;
    private RequestQueue nQueue;
    String em_l,pw_l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTextViewResult = findViewById(R.id.text_view_result);
        Button buttonregis = findViewById(R.id.button_regis);

        Button buttonlogin = findViewById(R.id.login);

        //register
        email_et = findViewById(R.id.email_et);
        pw_et = findViewById(R.id.pw_et);
        pwc_et = findViewById(R.id.pwc_et);


        //login
        email_log = findViewById(R.id.email_et_login);
        pw_log = findViewById(R.id.pw_et_login);




        mQueue = Volley.newRequestQueue(this);
        nQueue = Volley.newRequestQueue(this);


        //regis
        buttonregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    register();
                }

            }
            private boolean validate() {
                boolean temp=true;

                em = email_et.getText().toString();
                pw = pw_et.getText().toString();
                pwc =  pwc_et.getText().toString();
                if(!pw.equals(pwc)){
                    Toast.makeText(MainActivity.this,"Password Not matching",Toast.LENGTH_SHORT).show();
                    temp=false;
                }
                return temp;
            }
        });

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void register() {

        //String url = "https://api.myjson.com/bins/kp9wz";
        String url = "https://bms-yoga-dev.herokuapp.com/user/signup";
        em = email_et.getText().toString();
        pw = pw_et.getText().toString();

       /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("todos");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);

                                String firstName = employee.getString("text");
                               // int age = employee.getInt("age");
                                String mail = employee.getString("_id");

                                mTextViewResult.append(firstName + ", " + mail + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);*/

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        email_et.setText("");
                        pw_et.setText("");
                        pwc_et.setText("");
                        Toast.makeText(MainActivity.this, "User Created Successfully",
                                Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", String.valueOf(error));
                        email_et.setText("Error");
                        pw_et.setText("Error");
                        pwc_et.setText("Error");
                        Toast.makeText(MainActivity.this, "Error/User Already Exists",
                                Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", em);
                params.put("password", pw);

                return params;
            }
        };
        mQueue.add(postRequest);
    }

    private void login() {

        //String url = "https://api.myjson.com/bins/kp9wz";
        String url = "https://bms-yoga-dev.herokuapp.com/user/login";
        em_l = email_log.getText().toString();
        pw_l = pw_log.getText().toString();
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        email_log.setText("");
                        pw_log.setText("");

                        Toast.makeText(MainActivity.this, "Login Successful",
                                Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", String.valueOf(error));
                        Toast.makeText(MainActivity.this, "Invalid Credentails",
                                Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", em_l);
                params.put("password", pw_l);

                return params;
            }
        };
        mQueue.add(postRequest);
    }
}
