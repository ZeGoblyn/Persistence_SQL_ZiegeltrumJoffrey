package com.example.td_persistancedonnee_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText username, password;
    private Button login;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.usernameEdit);
        password = findViewById(R.id.passwordEdit);
        login = findViewById(R.id.buttonLogin);
        login.setOnClickListener(this::onClick);
        progressDialog = new ProgressDialog(this);
    }


    private void loginUser() {
        final String nomUser = username.getText().toString().trim();
        final String mdpUser = password.getText().toString().trim();

        progressDialog.setMessage("Connexion en cours...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Constantes.url_login+"?Nom="+nomUser+"&Password="+mdpUser,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.e("Resp", response);
                        try {
                            //Log.e("Resp", response);
                            JSONObject jsonObject = new JSONObject(response);
                            if(!jsonObject.getBoolean("error"))
                            {
                                Shared_preferenced_manager.getInstance(getApplicationContext())
                                        .userLogin(jsonObject.getString("Name"),jsonObject.getString("Mail"));
                            }
                            /*Toast.makeText(Login.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            Toast.makeText(Login.this, jsonObject.getString("Name"), Toast.LENGTH_SHORT).show();
                            Toast.makeText(Login.this, jsonObject.getString("Mail"), Toast.LENGTH_SHORT).show();
                            Log.e("tagA", jsonObject.getString("message"));
                            if(jsonObject.getBoolean("status"))
                            {
                                Log.e("tagA", "E");
                            }else{
                                Log.e("tagA", "NOT E");
                            }*/
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Resp", response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(Login.this, "Erreur", Toast.LENGTH_SHORT).show();
                        ;
                        //Log.e("tagB", "Erreur reponse ");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Nom", nomUser);
                params.put("Password", mdpUser);
                //params.put("mail", mailUser);
                return params;
            }
        };
        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/
        RequestsHandler.getInstance(this).addToRequestQueue(stringRequest);


        /*@Override
        public void onClick (View v){
            if (v == login) {
                loginUser();
            }
        }*/
    }

    @Override
    public void onClick (View v){
        if (v == login) {
            loginUser();
            Intent monIntent = new Intent(Login.this,Profil_activity.class);
            //Navigation
            startActivity(monIntent);
        }
    }
}