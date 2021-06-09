package com.example.td_persistancedonnee_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username, password, email, local, ddn;
    private Button register, log;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.usernameEdit);
        password = findViewById(R.id.passwordEdit);
        email = findViewById(R.id.emailEdit);
        local = findViewById(R.id.editLocalite);
        ddn = findViewById(R.id.editDdn);
        register = findViewById(R.id.buttonRegistre);
        register.setOnClickListener(this);
        log = findViewById(R.id.buttonLogin);
        log.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        //Shared_preferenced_manager.getInstance(getApplicationContext());

    }

    private void registerUser()
    {
        final String nomUser = username.getText().toString().trim();
        final String mdpUser = password.getText().toString().trim();
        final String mailUser = email.getText().toString().trim();
        final String locUser = local.getText().toString().trim();
        final String ddnUser = ddn.getText().toString().trim();

        progressDialog.setMessage("Création utilisateurs en cours...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constantes.url_register,
                new Response.Listener<String>(){
        @Override
        public void onResponse(String response){
        progressDialog.dismiss();
            Log.e("Resp", response);
        try{
            Log.e("Resp", response);
            JSONObject jsonObject = new JSONObject(response);
            Toast.makeText(MainActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            Log.e("tagA", jsonObject.getString("message"));
        }catch(JSONException e){
            e.printStackTrace();
            Log.e("Resp", response);
            }
        }
        },
                new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    progressDialog.hide();
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("tagB", error.getMessage());
                }
                }
    ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Nom", nomUser);
                params.put("Password", mdpUser);
                params.put("mail", mailUser);
                params.put("local", locUser);
                params.put("ddn", ddnUser);
                return params;
            }
        };
        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/    //V1 non optimisée
        RequestsHandler.getInstance(this).addToRequestQueue(stringRequest);
    }





    @Override
    public void onClick(View v) {
        if(v == register)
        {
            registerUser();
        }

        if(v == log)
        {
            login();
        }
    }

   /* public void onClick(View v)
    {
        if(v == log)
        {
            login();
        }
    }*/

    private void login()
    {
        //Préparation de la navigation.
        Intent monIntent = new Intent(MainActivity.this,Login.class);
        //Navigation
        startActivity(monIntent);
    }


}