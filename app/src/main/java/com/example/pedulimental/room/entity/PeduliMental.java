package com.example.pedulimental.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PeduliMental {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String username;
    public String email;
    public String password;
    public String jenisKelamin;
    public int usia;
    public boolean susahTidur;
    public boolean gelisah;
    public boolean suasanaHati;
    public boolean perubahanTidur;
    public boolean isolasi;
    public String gejalanLainnya;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }

    public void setGejalanLainnya(String gejalanLainnya) {
        this.gejalanLainnya = gejalanLainnya;
    }

    public boolean isSusahTidur() {
        return susahTidur;
    }

    public void setSusahTidur(boolean susahTidur) {
        this.susahTidur = susahTidur;
    }

    public boolean isGelisah() {
        return gelisah;
    }

    public void setGelisah(boolean gelisah) {
        this.gelisah = gelisah;
    }

    public boolean isSuasanaHati() {
        return suasanaHati;
    }

    public void setSuasanaHati(boolean suasanaHati) {
        this.suasanaHati = suasanaHati;
    }

    public boolean isPerubahanTidur() {
        return perubahanTidur;
    }

    public void setPerubahanTidur(boolean perubahanTidur) {
        this.perubahanTidur = perubahanTidur;
    }

    public boolean isIsolasi() {
        return isolasi;
    }

    public void setIsolasi(boolean isolasi) {
        this.isolasi = isolasi;
    }

    public String getGejalanLainnya() {
        return gejalanLainnya;
    }

    public void setKeluhanLainnya(String gejalanLainnya) {
        this.gejalanLainnya = gejalanLainnya;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
