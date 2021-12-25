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

import com.example.pedulimental.room.database.MyDatabase;
import com.example.pedulimental.room.entity.PeduliMental;
import com.google.android.material.button.MaterialButton;

public class EditActivity extends AppCompatActivity {
    EditText EtEmail, EtUsername, Etpass, EtKeluhan;
    RadioGroup RadioBG;
    RadioButton RBwnita, RBpria;
    SeekBar idseekbar;
    TextView TvUsia1;
    CheckBox cbSusahTidur, cbGelisah, cbSuasanahati, cbPerubahantidur, cbIsolasi ;
    MaterialButton BtnKirimData;
    public MyDatabase myDatabase;

    public Integer selectedId = 0;

    public static final String SELECTED_ID = "SELECTED_ID";
    private boolean validation;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvJudul = findViewById(R.id.TvJudul);
        tvJudul.setText("FORM EDIT");

        this.myDatabase = MyDatabase.createDatabase(this);
        this.selectedId = getIntent().getIntExtra(SELECTED_ID,0);
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
                    Toast.makeText(getApplicationContext(), "Pendaftaran Berhasil!",
                            Toast.LENGTH_SHORT).show();
                    PeduliMental peduliMental = myDatabase.daoPeduliMental().getPeduliMentalById(selectedId);

                    peduliMental.setEmail(EtEmail.getText().toString());
                    peduliMental.setUsername(EtUsername.getText().toString());
                    peduliMental.setPassword(Etpass.getText().toString());
                    peduliMental.setJenisKelamin(RBwnita.isChecked() ? "Wanita" : "Pria");
                    peduliMental.setUsia(idseekbar.getProgress());
                    peduliMental.setSusahTidur(cbSusahTidur.isChecked());
                    peduliMental.setGelisah(cbGelisah.isChecked());
                    peduliMental.setSuasanaHati(cbSuasanahati.isChecked());
                    peduliMental.setPerubahanTidur(cbPerubahantidur.isChecked());
                    peduliMental.setIsolasi(cbIsolasi.isChecked());
                    peduliMental.setKeluhanLainnya(EtKeluhan.getText().toString());

                   myDatabase.daoPeduliMental().updatePeduliMentalByEntity(peduliMental);

                   finish();

                }
            }
        });

        setDataFiller();

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
                Intent intent = new Intent(EditActivity.this, MenuAbout.class);
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
                    Toast.makeText(EditActivity.this,"Pria",Toast.LENGTH_LONG).show();
                break;
            case R.id.RBwanita:
                if (checked)
                    Toast.makeText(EditActivity.this,"Wanita",Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void setDataFiller(){
        PeduliMental peduliMental = myDatabase.daoPeduliMental().getPeduliMentalById(this.selectedId);

        EtEmail.setText(peduliMental.getEmail());
        EtUsername.setText(peduliMental.getUsername());
        Etpass.setText(peduliMental.getPassword());
        RBwnita.setChecked(peduliMental.getJenisKelamin().matches("Wanita"));
        RBpria.setChecked(peduliMental.getJenisKelamin().matches("Pria"));

        EtKeluhan.setText(peduliMental.getGejalanLainnya());
        cbSusahTidur.setChecked(peduliMental.isSusahTidur());
        cbGelisah.setChecked(peduliMental.isGelisah());
        cbSuasanahati.setChecked(peduliMental.isSuasanaHati());
        cbPerubahantidur.setChecked(peduliMental.isPerubahanTidur());
        cbIsolasi.setChecked(peduliMental.isIsolasi());
        idseekbar.setProgress(peduliMental.getUsia());
        String usia = peduliMental.getUsia()+" Tahun";
        TvUsia1.setText(usia);
    }

}