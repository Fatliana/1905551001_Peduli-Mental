package com.example.pedulimental.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pedulimental.room.dao.DaoPeduliMental;
import com.example.pedulimental.room.entity.PeduliMental;

@Database(entities = {
        PeduliMental.class
},version = 3,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase INSTANCE;
    public abstract DaoPeduliMental daoPeduliMental();

    public static MyDatabase createDatabase(Context context){
        synchronized (MyDatabase.class){
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context,MyDatabase.class,"db_peduli_mental")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return INSTANCE;
    }
}