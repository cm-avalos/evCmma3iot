package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.Reference;

public class programar extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Button button;
    private Button mBtnCrearDatos;
    private CheckBox c1,c2,c3,c4,c5,c6,c7;
    private DatabaseReference mDatabase;
    private FirebaseDatabase database;
    private Member member;
    int i =0;
    //editext
    private EditText mEditTextMensaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programar);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.aprogramar);


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
                        startActivity(new Intent(getApplicationContext(),estadist.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.aprogramar:

                        return true;

                    case R.id.ahistorial:
                        startActivity(new Intent(getApplicationContext(),historial.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        //CHECKBOX INTER

        mDatabase = FirebaseDatabase.getInstance().getReference().child("week");
        member = new Member();


        button=findViewById(R.id.btnRegistrarRegado);
        c1=findViewById(R.id.lunes);
        c2=findViewById(R.id.martes);
        c3=findViewById(R.id.miercoles);
        c4=findViewById(R.id.jueves);
        c5=findViewById(R.id.viernes);
        c6=findViewById(R.id.sabado);
        c7=findViewById(R.id.domingo);

       String d1="Lunes";
       String d2="Martes";
       String d3="Miercoles";
       String d4="Jueves";
       String d5="Viernes";
       String d6="Sabado";
       String d7="Domingo";
       mDatabase.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {

               if (snapshot.exists()){
                   i = (int)snapshot.getChildrenCount();
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (c1.isChecked()){
                    //toas
                    member.setDay(d1);
                    mDatabase.child(String.valueOf(i+1)).setValue(member);
                }
                else{
                    //
                }
                if (c2.isChecked()){
                    //toas
                    member.setDay(d2);
                    mDatabase.child(String.valueOf(i+1)).setValue(member);
                }
                else{
                    //
                }
                if (c3.isChecked()){
                    //toas
                    member.setDay(d3);
                    mDatabase.child(String.valueOf(i+1)).setValue(member);
                }
                else{
                    //
                }
                if (c4.isChecked()){
                    //toas
                    member.setDay(d4);
                    mDatabase.child(String.valueOf(i+1)).setValue(member);
                }
                else{
                    //
                }
                if (c5.isChecked()){
                    //toas
                    member.setDay(d5);
                    mDatabase.child(String.valueOf(i+1)).setValue(member);
                }
                else{
                    //
                }
                if (c6.isChecked()){
                    //toas
                    member.setDay(d6);
                    mDatabase.child(String.valueOf(i+1)).setValue(member);
                }
                else{
                    //
                }
                if (c7.isChecked()){
                    //toas
                    member.setDay(d7);
                    mDatabase.child(String.valueOf(i+1)).setValue(member);
                }
                else{
                    //
                }

            }
        });
        //

        //Base de datos conexion
        mEditTextMensaje=(EditText) findViewById(R.id.tNombreRegado);
        mBtnCrearDatos=(Button) findViewById(R.id.btnCrearDatos);

        mDatabase=FirebaseDatabase.getInstance().getReference();

        mBtnCrearDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mensaje= mEditTextMensaje.getText().toString();
                mDatabase.child("nombreRegado").setValue(mensaje);
            }
        });
    }
}