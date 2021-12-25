package com.example.pedulimental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.pedulimental.Adapter.DataAdapter;
import com.example.pedulimental.Adapter.OnDeleteDataAdapter;
import com.example.pedulimental.room.database.MyDatabase;
import com.example.pedulimental.room.entity.PeduliMental;

import java.util.List;

public class TampilDataActivity extends AppCompatActivity implements OnDeleteDataAdapter {
    public MyDatabase myDatabase;
    public DataAdapter dataAdapter;
    public RecyclerView recyclerView;

    public static final String NAME = "name";
    public static final String EMAIL = "EMAIL";
    public static final String PASSOWRD = "PASSWORD";
    public static final String WANITA = "WANITA";
    public static final String PRIA = "PRIA";
    public static final String USIA = "USIA";
    public static final String SUSAH_TIDUR = "SUSAH_TIDUR";
    public static final String GELISAH = "GELISAH";
    public static final String PERUBAHAN_SUASANA_HATI = "PERUBAHAN_SUASANA_HATI";
    public static final String PERUBAHAN_TIDUR = "PERUBAHAN_TIDUR";
    public static final String ISOLASI_DIRI = "ISOLASI_DIRI";
    public static final String KELUHAN_LAINNYA = "KELUHAN_LAINNYA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data2);

        init();
    }

    private void init() {
        myDatabase = MyDatabase.createDatabase(this);

        PeduliMental peduliMental = new PeduliMental();

        Bundle bundle = getIntent().getExtras();
        peduliMental.setUsername(bundle.getString(NAME,""));
        peduliMental.setEmail(bundle.getString(EMAIL,""));
        peduliMental.setPassword(bundle.getString(PASSOWRD,""));
        peduliMental.setJenisKelamin(bundle.getBoolean(WANITA) ? "Wanita" : "Pria");
        peduliMental.setUsia(bundle.getInt(USIA,0));
        peduliMental.setSusahTidur(bundle.getBoolean(SUSAH_TIDUR));
        peduliMental.setGelisah(bundle.getBoolean(GELISAH));
        peduliMental.setSuasanaHati(bundle.getBoolean(PERUBAHAN_SUASANA_HATI));
        peduliMental.setPerubahanTidur(bundle.getBoolean(PERUBAHAN_TIDUR));
        peduliMental.setIsolasi(bundle.getBoolean(ISOLASI_DIRI));
        peduliMental.setKeluhanLainnya(bundle.getString(KELUHAN_LAINNYA,""));

        myDatabase.daoPeduliMental().insertPeduliMentalForm(peduliMental);

        recyclerView = findViewById(R.id.rvUser);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void setRecyclerViewData(List<PeduliMental> peduliMentals){
        this.dataAdapter = new DataAdapter(peduliMentals,this);
        this.recyclerView.setAdapter(dataAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Welcome To Data User", Toast.LENGTH_SHORT).show();
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
    protected void onResume() {
        super.onResume();
        List<PeduliMental> peduliMentals = myDatabase.daoPeduliMental().getAllPeduliMental();
        setRecyclerViewData(peduliMentals);
        Toast.makeText(this, "Data User On Resume", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDelete() {
        List<PeduliMental> peduliMentals = myDatabase.daoPeduliMental().getAllPeduliMental();
        setRecyclerViewData(peduliMentals);
    }
}