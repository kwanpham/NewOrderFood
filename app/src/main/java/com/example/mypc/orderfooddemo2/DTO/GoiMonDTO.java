package com.example.mypc.orderfooddemo2.DTO;

/**
 * Created by MyPC on 20/02/2018.
 */

public class GoiMonDTO {

    int MaGoiMon , MaBan , MaNV , SoNguoi;
    String TinhTrang , NgayGoi;

    public long getTongTienBanAn() {
        return tongTienBanAn;
    }

    public void setTongTienBanAn(long tongTienBanAn) {
        this.tongTienBanAn = tongTienBanAn;
    }

    long tongTienBanAn;


    public int getMaGoiMon() {
        return MaGoiMon;
    }

    public void setMaGoiMon(int maGoiMon) {
        MaGoiMon = maGoiMon;
    }

    public int getMaBan() {
        return MaBan;
    }

    public void setMaBan(int maBan) {
        MaBan = maBan;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int maNV) {
        MaNV = maNV;
    }

    public int getSoNguoi() {
        return SoNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        SoNguoi = soNguoi;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public String getNgayGoi() {
        return NgayGoi;
    }

    public void setNgayGoi(String ngayGoi) {
        NgayGoi = ngayGoi;
    }
}
