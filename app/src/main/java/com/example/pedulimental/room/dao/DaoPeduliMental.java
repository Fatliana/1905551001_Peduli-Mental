package com.example.pedulimental.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pedulimental.room.entity.PeduliMental;

import java.util.List;

@Dao
public interface DaoPeduliMental {

    @Insert
    void insertPeduliMentalForm(PeduliMental peduliMental);

    @Query("SELECT * FROM PeduliMental")
    List<PeduliMental> getAllPeduliMental();

    @Query("SELECT * FROM PeduliMental WHERE PeduliMental.id = :id")
    PeduliMental getPeduliMentalById(Integer id);

    @Update
    void updatePeduliMentalByEntity(PeduliMental peduliMental);

    @Delete
    void deletePedeliMentalByEntity(PeduliMental peduliMental);
}
