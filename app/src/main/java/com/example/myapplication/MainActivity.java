    package com.example.myapplication;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.TextView;

    import com.google.android.material.bottomnavigation.BottomNavigationView;
    import com.google.android.material.navigation.NavigationBarView;
    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.ValueEventListener;

    import java.util.HashMap;
    import java.util.Map;



    public class MainActivity extends AppCompatActivity {


        BottomNavigationView bottomNavigationView;
        //base de datos conexion

        private TextView etHumedad;
        private TextView etTemperatura;
        private DatabaseReference mDatabase;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            bottomNavigationView = findViewById(R.id.bottom_navigator);
            bottomNavigationView.setSelectedItemId(R.id.dashboard);


            bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId())
                    {
                        case R.id.dashboard:
                            return true;

                        case R.id.aestadistica:
                            startActivity(new Intent(getApplicationContext(),estadist.class));
                            overridePendingTransition(0,0);
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

            etTemperatura = (TextView)findViewById(R.id.temperatura);
            etHumedad = (TextView)findViewById(R.id.humedad);

            //referencias
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("Parametros").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.exists()){

                        String temperatura = snapshot.child("temperatura").getValue().toString();
                        String humedad = snapshot.child("humedad").getValue().toString();



                        System.out.println(temperatura);
                        etTemperatura.setText(temperatura + " pstaje. de temperatura");

                        System.out.println(humedad);
                        etHumedad.setText(humedad + " pstaje. de humedad");

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

















        }
    }