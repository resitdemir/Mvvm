package com.example.mybabiesapp.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "baby_table")
public class Baby implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String ad;
    private String dogumTarihi;
    private String cinsiet;
    private String gebelikYasi;
    private String agirlik;
    private String uzunluk;
    private String kafaUzunluk;
    public String iliskiler;
    byte[] resimUrl;

    public Baby(String ad, String dogumTarihi, String cinsiet, String gebelikYasi, String agirlik, String uzunluk, String kafaUzunluk, String iliskiler,byte[] resimUrl) {
        this.ad = ad;
        this.dogumTarihi = dogumTarihi;
        this.cinsiet = cinsiet;
        this.gebelikYasi = gebelikYasi;
        this.agirlik = agirlik;
        this.uzunluk = uzunluk;
        this.kafaUzunluk = kafaUzunluk;
        this.iliskiler = iliskiler;
        this.resimUrl = resimUrl;
    }

    public byte[] getResimUrl() {
        return resimUrl;
    }

    public void setResimUrl(byte[] resimUrl) {
        this.resimUrl = resimUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public String getDogumTarihi() {
        return dogumTarihi;
    }

    public String getCinsiet() {
        return cinsiet;
    }

    public String getGebelikYasi() {
        return gebelikYasi;
    }

    public String getAgirlik() { return agirlik; }

    public String getUzunluk() {
        return uzunluk;
    }

    public String getKafaUzunluk() {
        return kafaUzunluk;
    }

    public String getIliskiler() {
        return iliskiler;
    }

    public int getId() {
        return id;
    }


}
