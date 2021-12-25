package com.example.pedulimental;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    EditText EtEmail, EtUsername, Etpass, EtKeluhan;
    RadioGroup RadioBG;
    RadioButton RBwnita, RBpria;
    SeekBar idseekbar;
    TextView TvUsia1;
    CheckBox cbSusahTidur, cbGelisah, cbSuasanahati, cbPerubahantidur, cbIsolasi ;
    MaterialButton BtnKirimData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EtEmail = findViewById(R.id.EtEmail);
        EtUsername = findViewById(R.id.EtUsername);
        Etpass = findViewById(R.id.Etpass);
        RadioBG = findViewById(R.id.RadioBG);
        RBwnita = findViewById(R.id.RBwanita);
        RBpria = findViewById(R.id.RBpria);

        idseekbar = findViewById(R.id.idseekbar);
        TvUsia1 = findViewById(R.id.TvUsia1);
        idseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean formUser) {
                TvUsia1.setText(progress + " Tahun");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        EtKeluhan = findViewById(R.id.EtKeluhan);
        BtnKirimData = findViewById(R.id.btnn);
        cbSusahTidur = findViewById(R.id.CBsusahtidur);
        cbGelisah = findViewById(R.id.cbgelisah);
        cbSuasanahati = findViewById(R.id.cbsuasanahati);
        cbPerubahantidur = findViewById(R.id.cbPerubahantidur);
        cbIsolasi = findViewById(R.id.cbSukamengisolasidiri);

        BtnKirimData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EtEmail.getText().toString().length() == 0) {
                    EtEmail.setError("Harap isi Email anda!");
                } else if (EtUsername.getText().toString().length() == 0) {
                    EtUsername.setError("Harap isi Username anda!");
                } else if (Etpass.getText().toString().length() == 0) {
                    Etpass.setError("Harap isi Password anda!");
                } else if (TvUsia1.getText().toString().length() == 0) {
                    TvUsia1.setError("Harap isi Usia anda!");
                } else if (EtKeluhan.getText().toString().length() == 0) {
                    EtKeluhan.setError("-, jika tidak ada Keluhan lain");
                } else {
                Toast.makeText(getApplicationContext(), "Registration Successful!",
                        Toast.LENGTH_SHORT).show();

                Intent i1 = new Intent(getApplicationContext(), TampilDataActivity.class);

                i1.putExtra(TampilDataActivity.EMAIL, EtEmail.getText().toString());
                i1.putExtra(TampilDataActivity.NAME, EtUsername.getText().toString());
                i1.putExtra(TampilDataActivity.PASSOWRD, Etpass.getText().toString());
                i1.putExtra(TampilDataActivity.WANITA, RBwnita.isChecked());
                i1.putExtra(TampilDataActivity.PRIA, RBpria.isChecked());
                i1.putExtra(TampilDataActivity.USIA, idseekbar.getProgress());
                i1.putExtra(TampilDataActivity.SUSAH_TIDUR,cbSusahTidur.isChecked());
                i1.putExtra(TampilDataActivity.GELISAH,cbGelisah.isChecked());
                i1.putExtra(TampilDataActivity.PERUBAHAN_SUASANA_HATI,cbSuasanahati.isChecked());
                i1.putExtra(TampilDataActivity.PERUBAHAN_TIDUR,cbPerubahantidur.isChecked());
                i1.putExtra(TampilDataActivity.ISOLASI_DIRI,cbIsolasi.isChecked());
                i1.putExtra(TampilDataActivity.KELUHAN_LAINNYA, EtKeluhan.getText().toString());

                startActivity(i1);
            }
           }
        });


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
                Intent intent = new Intent(MainActivity.this, com.example.pedulimental.MenuAbout.class);
                startActivity(intent);
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.RBpria:
                if (checked)
                    Toast.makeText(MainActivity.this,"Pria",Toast.LENGTH_LONG).show();
                break;
            case R.id.RBwanita:
                if (checked)
                    Toast.makeText(MainActivity.this,"Wanita",Toast.LENGTH_LONG).show();
                break;
        }
    }
}