package com.example.td_persistancedonnee_sql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Profil_activity extends AppCompatActivity implements View.OnClickListener{
    private EditText username, password, email, local, ddn;
    private Button update, suppr, search;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_activity);
        username = findViewById(R.id.editTNom);
        password = findViewById(R.id.editTPass);
        email = findViewById(R.id.editTMail);
        local = findViewById(R.id.editTLocal);
        ddn = findViewById(R.id.editTDDn);
        update = findViewById(R.id.buttonUpdate);
        suppr = findViewById(R.id.buttonDelete);
        search = findViewById(R.id.buttonRecherche);

        progressDialog = new ProgressDialog(this);

        //username.setText(Shared_preferenced_manager.getUserName());
        //email.setText(Shared_preferenced_manager.getUserEmail());

        update.setOnClickListener(this);
        suppr.setOnClickListener(this);
        search.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.settings :
                //Shared_preferenced_manager.getInstance(getApplicationContext());
                Toast.makeText(Profil_activity.this, Shared_preferenced_manager.getUserName(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Profil_activity.this, Shared_preferenced_manager.getUserEmail(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout :
                finish();
                Intent monIntent = new Intent(Profil_activity.this,MainActivity.class);
                startActivity(monIntent);
                //finish();
                Shared_preferenced_manager.getInstance(this).logout();
                break;
            default:
                break;
        }
        //return super.onOptionsItemSelected(item);
        return true;
    }

    private void registerUpdate()
    {
        final String nomUser = username.getText().toString().trim();
        final String mdpUser = password.getText().toString().trim();
        final String mailUser = email.getText().toString().trim();
        final String locUser = local.getText().toString().trim();
        final String ddnUser = ddn.getText().toString().trim();

        progressDialog.setMessage("Update utilisateurs en cours...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constantes.url_update,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        progressDialog.dismiss();
                        Log.e("Resp", response);
                        try{
                            Log.e("Resp", response);
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(Profil_activity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Profil_activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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


    private void delete()
    {
        final String nomUser = username.getText().toString().trim();


        progressDialog.setMessage("Termination en cours...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constantes.url_delete,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        progressDialog.dismiss();
                        Log.e("Resp", response);
                        try{
                            Log.e("Resp", response);
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(Profil_activity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Profil_activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("tagB", error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Nom", nomUser);
                return params;
            }
        };
        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/    //V1 non optimisée
        RequestsHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v == update)
        {
            registerUpdate();
        }

        if(v == suppr)
        {
            delete();
        }

        if(v == search)
        {
            Intent monIntent = new Intent(Profil_activity.this,RechercheActivity.class);
            //Navigation
            startActivity(monIntent);
        }
    }
}