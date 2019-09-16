package com.example.restapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import entities.User;
import fragments.ListaFragment;

public class MainActivity extends AppCompatActivity {

    private TextView campo;
    private List<User> userList;
    private ProgressDialog pDialog;
    ListView lista;
    private ImageButton post,buscar,sair;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        post=(ImageButton) findViewById(R.id.post);
        buscar=(ImageButton) findViewById(R.id.btn_buscar);
        sair=(ImageButton) findViewById(R.id.sair);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navegar(view);
            }
        });
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { get();
            }
        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    public void get(){
        try {
            FragmentManager fm = getSupportFragmentManager();
            fragments.ListaFragment f = new ListaFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_frame, f, "ConsumerFragment");
            ft.addToBackStack(null);
            ft.commit();
        }catch (Exception e){
            System.out.println("Exceção :"+e.toString());
            e.printStackTrace();
        }
    }
    public void navegar(View view) {
        startActivity(new Intent(this, PostActivity.class));
    }
}
