package com.example.td_persistancedonnee_sql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RechercheActivity extends AppCompatActivity  implements View.OnClickListener{

    private static EditText editTextUsername = null;
    private static EditText editTextEmail = null;
    private static EditText editTextLocalite = null;

    private static Button button = null;
    private ProgressDialog progressDialog = null;

    private static RecyclerView monRecyclerView;
    private static RecyclerView.LayoutManager monLayoutManager;


    private void userSearch()
    {
        // Récupérer les valeurs des 3 champs et ôter les espaces avec .trim()
        final String username = (String) editTextUsername.getText().toString().trim();
        final String email = (String) editTextEmail.getText().toString().trim();
        final String localite = (String) editTextLocalite.getText().toString().trim();

        if (username.equals("") && email.equals("") && localite.equals(""))
        {
            Toast.makeText(RechercheActivity.this, "Renseignez les champs", Toast.LENGTH_LONG).show();
            return;
        }

        // Afficher la boîte de dialogue
        progressDialog.setMessage("Recherche utilisateur en cours...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Constantes.url_search+"?nom="+username+"&mail="+email+"&localite="+localite,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if (!jsonObject.getBoolean("error"))
                            {
                                JSONArray ResultArray = jsonObject.getJSONArray("Resultat");

                                ArrayList<User> userList = new ArrayList<User>();

                                for (int i = 0; i < ResultArray.length(); i++)
                                {
                                    JSONObject object = ResultArray.getJSONObject(i);
                                    User user = new User(object.getString("Username"), object.getString("Email"), object.getString("Localite"));
                                    userList.add(user);
                                }

                                RecyclerView.Adapter monAdapter = new RecAdapter(userList); //cf. rec_adapter.java, classe de l’adaptateur
                                monRecyclerView.setAdapter(monAdapter);
                            }

                            Toast.makeText(RechercheActivity.this, jsonObject.getString("Message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RechercheActivity.this, "erreur", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.hide();
                        NetworkResponse NetError = error.networkResponse;
                        if (NetError != null)
                        {
                            Log.e("NetworkError", "Error : "+String.valueOf(NetError.statusCode));
                        }

                        Toast.makeText(RechercheActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> params = new HashMap<>();
                params.put("nom", username);
                params.put("mail", email);
                params.put("localite", localite);
                return params;
            }
        };

        // Version 1 non optimisée
        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/

        // Version 2 optimisée avec "singleton" RequestQueue
        RequestsHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        init();

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if (v == button)
        {
            userSearch();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        // return super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case R.id.settings :
                Toast.makeText(RechercheActivity.this, Shared_preferenced_manager.getUserName(), Toast.LENGTH_SHORT).show();
                Toast.makeText(RechercheActivity.this, Shared_preferenced_manager.getUserEmail(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout :
                finish();
                Shared_preferenced_manager.getInstance(getApplicationContext()).logout();
                Intent monIntent = new Intent(RechercheActivity.this, MainActivity.class);
                startActivity(monIntent);
                break;
            default:
                break;
        }
        //return super.onOptionsItemSelected(item);
        return true;
    }

    private void init()
    {
        editTextUsername = (EditText) findViewById(R.id.editTextPersonNameSearch);
        editTextEmail = (EditText) findViewById(R.id.editTextEmailSearch);
        editTextLocalite = (EditText) findViewById(R.id.editTextLocaliteSearch);
        button = (Button) findViewById(R.id.buttonSearch);


        monRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_userlist);
        monRecyclerView.setHasFixedSize(true); //Taille fixe des éléments de la liste = optimisation du recyclerView

        monLayoutManager = new LinearLayoutManager(this);
        monRecyclerView.setLayoutManager(monLayoutManager);

        // definition des variables situés plus haut (editTextUsername, editTextPassword, etc.)
        progressDialog = new ProgressDialog(this);
    }
}