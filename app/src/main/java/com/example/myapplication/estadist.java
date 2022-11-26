package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class estadist extends AppCompatActivity {

     BottomNavigationView bottomNavigationView;



    //base de datos conexion
    private TextView etHmaxima;
    private TextView etHminima;
    private TextView etTmaxima;
    private TextView etTminima;
    private TextView etUltimo;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadist);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.aestadistica);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.aestadistica:

                        return true;

                    case R.id.aprogramar:
                        startActivity(new Intent(getApplicationContext(),programar.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.ahistorial:
                        startActivity(new Intent(getApplicationContext(),historial.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        // BASE DE DATOS CONEXION

        etHmaxima = (TextView)findViewById(R.id.humedadMaxima);
        etHminima = (TextView)findViewById(R.id.humedadMinima);
        etTmaxima = (TextView)findViewById(R.id.temperaturaMaxima);
        etTminima = (TextView)findViewById(R.id.temperaturaMinima);
        etUltimo = (TextView)findViewById(R.id.ultimoRiego);


        //referencias
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Resumen").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String temperaturaMaxima = snapshot.child("tMaxima").getValue().toString();
                    String temperaturaMinima = snapshot.child("tMinima").getValue().toString();

                    String humedadMaxima = snapshot.child("hMaxima").getValue().toString();
                    String humedadMinima = snapshot.child("hMinima").getValue().toString();

                    String ultimoRiego = snapshot.child("ultimoR").getValue().toString();


                    System.out.println(temperaturaMaxima);
                    etTmaxima.setText("temperatura Maxima: "+temperaturaMaxima+"°C");

                    System.out.println(temperaturaMinima);
                    etTminima.setText("temperatura Minima: "+temperaturaMinima+"°C");

                    System.out.println(humedadMaxima);
                    etHmaxima.setText("Humedad Maxima: "+humedadMaxima);

                    System.out.println(humedadMinima);
                    etHminima.setText("Humedad Minima: "+humedadMinima);

                    System.out.println(ultimoRiego);
                    etUltimo.setText("Ultimo Riego fue: "+ultimoRiego);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







    }
}