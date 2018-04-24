package com.example.mypc.orderfooddemo2.DTO;

import java.io.Serializable;

/**
 * Created by MyPC on 21/02/2018.
 */

public class ThanhToanDTO implements Serializable{
    private int maMon;
    public int maGoiMon;



    String tenMonAn;
    int soLuong;
    int giaTien;
    int tongTien;
    public int getMaMon() {
        return maMon;
    }

    public void setMaMon(int maMon) {
        this.maMon = maMon;
    }
    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public int getTongTien() {
        return giaTien*soLuong;
    }

    public void setTongTien() {
        this.tongTien = giaTien*soLuong;
    }

    @Override
    public String toString() {
        return tenMonAn + "  " + soLuong + "  " + giaTien +  "  " + tongTien;
    }
}
