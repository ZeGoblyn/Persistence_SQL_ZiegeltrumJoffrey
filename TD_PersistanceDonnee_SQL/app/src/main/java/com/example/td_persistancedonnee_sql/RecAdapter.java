package com.example.td_persistancedonnee_sql;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {
    private ArrayList<User> userList;

    //Le viewHolder
        public static class ViewHolder extends RecyclerView.ViewHolder {
            //Y declarer les objets de la vue qui seront chargés (par le tableau de données)
            public TextView Username;
            public TextView Email;
            public TextView Localite;

            //Constructeur du holder : le viewHolder a une reference vers tous les objets de la liste
            public ViewHolder(View v) {
                super(v);
                Username= (TextView) v.findViewById(R.id.textview_List_Username);
                Email = (TextView) v.findViewById(R.id.textview_List_Email);
                Localite = (TextView) v.findViewById(R.id.textview_List_Localite);
            }
        }

        //Constructeur de l'adaptateur : initialisations de l’adapter et des données
    public RecAdapter(ArrayList<User> userList) {
            this.userList = userList;
        }

        //Chargement du layout et initialisation du viewHolder


    @NonNull
    @Override
    public RecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.canvas_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecAdapter.ViewHolder holder, int position) {
        final User users = userList.get(position);
        holder.Username.setText(users.getUsername());
        holder.Email.setText(users.getEmail());
        holder.Localite.setText(users.getLocalite());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

}
