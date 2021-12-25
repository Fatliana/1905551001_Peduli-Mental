package com.example.pedulimental.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pedulimental.EditActivity;
import com.example.pedulimental.R;
import com.example.pedulimental.room.database.MyDatabase;
import com.example.pedulimental.room.entity.PeduliMental;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    public List<PeduliMental> peduliMentallist;
    public Context context;
    public MyDatabase myDatabase;
    public OnDeleteDataAdapter onDeleteDataAdapter;
    public DataAdapter(List<PeduliMental> peduliMentallist, Context context) {
        this.peduliMentallist = peduliMentallist;
        this.context = context;
        this.myDatabase = MyDatabase.createDatabase(context);
        this.onDeleteDataAdapter = (OnDeleteDataAdapter) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_list_data_user,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PeduliMental peduliMental = this.peduliMentallist.get(position);

        StringBuilder stringBuilder = new StringBuilder();

        if(peduliMental.isSusahTidur()){
            stringBuilder.append("susah tidur, ");
        }
        if(peduliMental.isGelisah()){
            stringBuilder.append("gelisah, ");
        }
        if(peduliMental.isSuasanaHati()){
            stringBuilder.append("perubahan suasana hati, ");
        }
        if(peduliMental.isPerubahanTidur()){
            stringBuilder.append("perubahan tidur dan nafsu makan, ");
        }
        if(peduliMental.isIsolasi()){
            stringBuilder.append("suka mengisolasi diri, ");
        }

        if(stringBuilder.length() > 2){
            stringBuilder.deleteCharAt(stringBuilder.length()-2);
        }

        holder.tvName.setText(peduliMental.getUsername());
        holder.tvEmail.setText(peduliMental.getEmail());
        holder.tvJeniskelamin.setText(peduliMental.getJenisKelamin());
        String usia = String.valueOf(peduliMental.getUsia()+" Tahun");
        holder.tvUsia.setText(usia);
        holder.tvKeluhan.setText(stringBuilder.toString());
        holder.tvKeluhanTambahan.setText(peduliMental.getGejalanLainnya());

        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditActivity.class);
            intent.putExtra(EditActivity.SELECTED_ID,peduliMental.getId());
            context.startActivity(intent);
        });

        holder.btnDelete.setOnClickListener(v -> {
            myDatabase.daoPeduliMental().deletePedeliMentalByEntity(this.peduliMentallist.get(position));
            this.onDeleteDataAdapter.onDelete();
        });
    }

    @Override
    public int getItemCount() {
        return this.peduliMentallist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvEmail, tvJeniskelamin, tvUsia, tvKeluhan, tvKeluhanTambahan;
        public Button btnDelete, btnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvJeniskelamin = itemView.findViewById(R.id.tvJeniskelamin);
            tvUsia = itemView.findViewById(R.id.tvUsia);
            tvKeluhan = itemView.findViewById(R.id.tvKeluhan);
            tvKeluhanTambahan = itemView.findViewById(R.id.tvKeluhanTambahan);
        }
    }
}
