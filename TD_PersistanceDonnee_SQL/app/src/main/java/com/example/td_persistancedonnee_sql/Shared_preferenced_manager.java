package com.example.td_persistancedonnee_sql;

import android.content.Context;
import android.content.SharedPreferences;

public class Shared_preferenced_manager {
    private static Shared_preferenced_manager instance;
    private static Context ctx;

    private static final String sharedPrefName="Session";
    private static final String keyUserName="name";
    private static final String keyUserEmail="mail";


    private Shared_preferenced_manager(Context context) {
        ctx = context;
    }

    public static synchronized Shared_preferenced_manager getInstance(Context context) {
        if (instance == null) {
            instance = new Shared_preferenced_manager(context);
        }
        return instance;
    }

    //À la connexion, on écrit dans les sharePreferences
    public boolean userLogin(String username, String email)
    {
        //Récupération de la sauvegarde précédente
        SharedPreferences MonSharedPreferences = ctx.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = MonSharedPreferences.edit();

        myEditor.putString(keyUserName,username);
        myEditor.putString(keyUserEmail,email);
        myEditor.apply();

        /*Finir la fonction*/
        return true;
    }

    public boolean isLoggedIn()
    {
        //Lire les sharedPreferences
        SharedPreferences MonSharedPreferences = ctx.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        if (MonSharedPreferences.getString(keyUserName,"")!="" && MonSharedPreferences.getString(keyUserEmail,"")!="")
        {
            return true;
        }

        return false;
    }

    public boolean logout()
    {
        SharedPreferences MonSharedPreferences = ctx.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = MonSharedPreferences.edit();

        myEditor.clear();
        myEditor.apply();

        return true;
    }

    //Utilisation par profile Activity
    public static String getUserName()
    {
        SharedPreferences MonSharedPreferences = ctx.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        return MonSharedPreferences.getString(keyUserName,"");
    }

    public static String getUserEmail()
    {
        SharedPreferences MonSharedPreferences = ctx.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        return MonSharedPreferences.getString(keyUserEmail,"");
    }
}
