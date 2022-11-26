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

public class historial extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    //base de datos conexion
    private TextView etH1;
    private TextView etH2;
    private TextView eth3;
    private TextView eth4;
    private TextView eth5;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.ahistorial);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.aestadistica:
                        startActivity(new Intent(getApplicationContext(), estadist.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.aprogramar:
                        startActivity(new Intent(getApplicationContext(), programar.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.ahistorial:

                        return true;
                }
                return false;
            }
        });
        ///////////////////////////base de datos cone
        // BASE DE DATOS CONEXION

        etH1 = (TextView) findViewById(R.id.textoh1);
        etH2 = (TextView) findViewById(R.id.textoh2);
        eth3 = (TextView) findViewById(R.id.textoh3);
        eth4 = (TextView) findViewById(R.id.textoh4);
        eth5 = (TextView) findViewById(R.id.textoh5);


        //referencias
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Historial").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String historia1 = snapshot.child("1").getValue().toString();
                    String historia2 = snapshot.child("2").getValue().toString();
                    String historia3 = snapshot.child("3").getValue().toString();
                    String historia4 = snapshot.child("4").getValue().toString();
                    String historia5 = snapshot.child("5").getValue().toString();


                    System.out.println(historia1);
                    etH1.setText("fue: "+historia1);

                    System.out.println(historia2);
                    etH2.setText("fue: "+historia2);

                    System.out.println(historia3);
                    eth3.setText("fue: "+historia3);

                    System.out.println(historia4);
                    eth4.setText("fue: "+historia4);

                    System.out.println(historia5);
                    eth5.setText("fue: "+historia5);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}