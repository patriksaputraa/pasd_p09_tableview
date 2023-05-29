package com.javafx;

public class BahanKimia {
    private String id_bahan_kimia, nama, tanggal_terima, volume, sifat_zat, bentuk;
    
    public BahanKimia(final String id_bahan_kimia, final String nama, final String volume, final String bentuk, final String sifat_zat, final String tanggal_terima) {
        this.id_bahan_kimia = id_bahan_kimia;
        this.nama = nama;
        this.sifat_zat = sifat_zat;
        this.tanggal_terima = tanggal_terima;
        this.volume = volume;
        this.bentuk = bentuk;
    }

    public String getBahanKimia(){
        return getId_bahan_kimia()+","+getNama()+","+getVolume()+","+getBentuk()+","+getSifat_zat()+","+getTanggal_terima();
    }

    public String getId_bahan_kimia() {
        return id_bahan_kimia;
    }

    public void setId_bahan_kimia(String id_bahan_kimia) {
        this.id_bahan_kimia = id_bahan_kimia;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal_terima() {
        return tanggal_terima;
    }

    public void setTanggal_terima(String tanggal_terima) {
        this.tanggal_terima = tanggal_terima;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getSifat_zat() {
        return sifat_zat;
    }

    public void setSifat_zat(String sifat_zat) {
        this.sifat_zat = sifat_zat;
    }

    public String getBentuk() {
        return bentuk;
    }

    public void setBentuk(String bentuk) {
        this.bentuk = bentuk;
    }


}
