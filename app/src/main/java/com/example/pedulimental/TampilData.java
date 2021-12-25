package com.example.pedulimental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class TampilData extends AppCompatActivity {
    TextView TEmail, TUsename, TJK, TUsia, TCBhobi, THobi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data);

        TEmail = findViewById(R.id.tampilEmail);
        TUsename = findViewById(R.id.tampilUsername);
        TJK = findViewById(R.id.tampilJK);
        TUsia = findViewById(R.id.tampilUsia);
        TCBhobi = findViewById(R.id.tampilCBHobi);
        THobi = findViewById(R.id.tampilHobi);

        String Email= getIntent().getExtras().getString("Email");
        String Username = getIntent().getExtras().getString("Username");
        String JenisKelamin = getIntent().getExtras().getString("Jenis Kelamin");
        String Usia = getIntent().getExtras().getString("Usia");
        String CBhobi = getIntent().getExtras().getString("Hobi");
        String Hobi = getIntent().getExtras().getString("Hobi Lainnya");

        TEmail.setText("=  "+Email);
        TUsename.setText("=  "+Username);
        TJK.setText("=  "+JenisKelamin);
        TUsia.setText("=  "+Usia);
        TCBhobi.setText("=  "+CBhobi);
        THobi.setText("=  "+Hobi);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Welcome To Data User", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Data User On Resume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Aplication On Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Data User On Destroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_about,menu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id) {
            case R.id.idabout:
                Intent intent = new Intent(TampilData.this, com.example.pedulimental.MenuAbout.class);
                startActivity(intent);
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}