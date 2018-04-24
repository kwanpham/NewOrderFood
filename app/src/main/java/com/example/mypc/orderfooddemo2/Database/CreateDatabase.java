package com.example.mypc.orderfooddemo2.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;


public class CreateDatabase extends SQLiteOpenHelper {

    public static final String TB_NHANVIEN = "NHANVIEN" ;
    public static final String TB_MONAN = "MONAN" ;
    public static final String TB_LOAIMONAN = "LOAIMONAN" ;
    public static final String TB_CHITIETGOIMON = "CHITIETGOIMON" ;
    public static final String TB_GOIMON = "GOIMON" ;
    public static final String TB_QUYEN = "QUYEN";

    public static final String TB_QUYEN_MAQUYEN = "MAQUYEN";
    public static final String TB_QUYEN_TENQUYEN = "TENQUYEN";

    public static String TB_NHANVIEN_MANV = "MANV";
    public static String TB_NHANVIEN_MAQUYEN = "MAQUYEN";
    public static String TB_NHANVIEN_TEDN = "TENDN";
    public static String TB_NHANVIEN_MATKHAU = "MATKHAU";
    public static String TB_NHANVIEN_GIOITINH = "GIOITINH";
    public static String TB_NHANVIEN_NGAYSINH = "NGAYSINH";
    public static String TB_NHANVIEN_CMND = "CMND";

    public static String TB_MONAN_MAMON = "MAMON";
    public static String TB_MONAN_TENMONAN = "TENMONAN";
    public static String TB_MONAN_GIATIEN = "GIATIEN";
    public static String TB_MONAN_MALOAI = "MALOAI";


    public static String TB_LOAIMONAN_MALOAI = "MALOAI";
    public static String TB_LOAIMONAN_TENLOAI = "TENLOAI";


    public static String TB_GOIMON_MAGOIMON = "MAGOIMON";
    public static String TB_GOIMON_MANV = "MANV";
    public static String TB_GOIMON_NGAYGOI = "NGAYGOI";
    public static String TB_GOIMON_TINHTRANG = "TINHTRANG";
    public static String TB_GOIMON_MABAN = "MABAN";
    public static String TB_GOIMON_SONGUOI ="SONGUOI";
    //public static String TB_GOIMON_TONGTIENBANAN ="SONGUOI";

    public static String TB_CHITIETGOIMON_MAGOIMON = "MAGOIMON";
    public static String TB_CHITIETGOIMON_MAMONAN = "MAMONAN";
    public static String TB_CHITIETGOIMON_SOLUONG = "SOLUONG";

    public static String TB_CHITIEN = "CHITIEN";
    public static String TB_CHITIEN_MACHITIEN = "MACHITIEN";
    public static String TB_CHITIEN_TIEUDE = "TIEUDECHI";
    public static String TB_CHITIEN_NGAYCHI = "NGAYCHI";
    public static String TB_CHITIEN_SOTIEN = "SOTIEN";

    public static String TB_THUTIEN = "THUTIEN";
    public static String TB_THUTIEN_MATHUTIEN = "MATHUTIEN";
    public static String TB_THUTIEN_TIEUDE = "TIEUDETHU";
    public static String TB_THUTIEN_NGAY = "NGAYTHU";
    public static String TB_THUTIEN_SOTIEN = "SOTIEN";






    public CreateDatabase(Context context) {
        super(context, "OrderFood", null, 1);
//        File dbFile = context.getApplicationContext().getDatabasePath("OrderFood");
//        if (dbFile.exists()) {
//            dbFile.delete();
//        }

    }

    public void xoaDataBase(Context context){
        File dbFile = context.getApplicationContext().getDatabasePath("OrderFood");
       if (dbFile.exists()) {
           dbFile.delete();
       }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbNHANVIEN = "CREATE TABLE " + TB_NHANVIEN + " ( " + TB_NHANVIEN_MANV + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_NHANVIEN_TEDN + " TEXT, " + TB_NHANVIEN_MATKHAU + " TEXT, " + TB_NHANVIEN_GIOITINH + " TEXT, "
                + TB_NHANVIEN_NGAYSINH + " TEXT, " + TB_NHANVIEN_CMND + " INTEGER , " + TB_NHANVIEN_MAQUYEN + " INTEGER )";


        String tbMONAN = "CREATE TABLE " + TB_MONAN + " ( " + TB_MONAN_MAMON + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_MONAN_TENMONAN + " TEXT, " + TB_MONAN_MALOAI + " INTEGER, " + TB_MONAN_GIATIEN + " TEXT ) ";


        String tbLOAIMON = "CREATE TABLE " + TB_LOAIMONAN + " ( " + TB_LOAIMONAN_MALOAI + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TB_LOAIMONAN_TENLOAI + " TEXT )";

        String tbQUYEN = "CREATE TABLE " + TB_QUYEN + " ( " + TB_QUYEN_MAQUYEN + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TB_QUYEN_TENQUYEN + " TEXT )";

        String tbGOIMON = "CREATE TABLE " + TB_GOIMON + " ( " + TB_GOIMON_MAGOIMON + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_GOIMON_MABAN + " INTEGER, " + TB_GOIMON_MANV + " INTEGER, " + TB_GOIMON_NGAYGOI + " TEXT, "
                + TB_GOIMON_SONGUOI + " INTEGER, "
                + TB_GOIMON_TINHTRANG + " TEXT )";

        String tbCHITIETGOIMON = "CREATE TABLE " + TB_CHITIETGOIMON + " ( " + TB_CHITIETGOIMON_MAGOIMON + " INTEGER, "
                + TB_CHITIETGOIMON_MAMONAN + " INTEGER, " + TB_CHITIETGOIMON_SOLUONG + " INTEGER, "
                + " PRIMARY KEY ( " + TB_CHITIETGOIMON_MAGOIMON + "," + TB_CHITIETGOIMON_MAMONAN + "))";

        String tbCHITIEN = "CREATE TABLE " + TB_CHITIEN + " ( " + TB_CHITIEN_MACHITIEN+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_GOIMON_MABAN + " INTEGER, " + TB_GOIMON_MANV + " INTEGER, " + TB_GOIMON_NGAYGOI + " TEXT, "
                + TB_GOIMON_SONGUOI + " INTEGER, "
                + TB_GOIMON_TINHTRANG + " TEXT )";



        db.execSQL(tbNHANVIEN);
        db.execSQL(tbMONAN);
        db.execSQL(tbLOAIMON);
        db.execSQL(tbGOIMON);
        db.execSQL(tbCHITIETGOIMON);
        db.execSQL(tbQUYEN);
        
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }
}
