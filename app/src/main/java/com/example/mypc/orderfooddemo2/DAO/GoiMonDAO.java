package com.example.mypc.orderfooddemo2.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mypc.orderfooddemo2.DTO.ChiTietGoiMonDTO;
import com.example.mypc.orderfooddemo2.DTO.GoiMonDTO;
import com.example.mypc.orderfooddemo2.DTO.ThanhToanDTO;
import com.example.mypc.orderfooddemo2.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 20/02/2018.
 */

public class GoiMonDAO {

    private SQLiteDatabase database;

    public GoiMonDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public long ThemGoiMon(GoiMonDTO goiMonDTO) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_GOIMON_MABAN, goiMonDTO.getMaBan());
        contentValues.put(CreateDatabase.TB_GOIMON_MANV, goiMonDTO.getMaNV());
        contentValues.put(CreateDatabase.TB_GOIMON_NGAYGOI, goiMonDTO.getNgayGoi());
        contentValues.put(CreateDatabase.TB_GOIMON_TINHTRANG, goiMonDTO.getTinhTrang());
        contentValues.put(CreateDatabase.TB_GOIMON_SONGUOI, goiMonDTO.getSoNguoi());


        long kiemtra = database.insert(CreateDatabase.TB_GOIMON, null, contentValues);

        return kiemtra;

    }


//    public long LayMaGoiMonTheoMaBan(int maban,String tinhtrang){
//        String truyvan = "SELECT * FROM " + CreateDatabase.TB_GOIMON + " WHERE " + CreateDatabase.TB_GOIMON_MABAN + " = '" + maban + "' AND "
//                + CreateDatabase.TB_GOIMON_TINHTRANG + " = '" + tinhtrang + "'";
//
//        long magoimon = 0;
//        Cursor cursor = database.rawQuery(truyvan,null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()){
//            magoimon = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_GOIMON_MAGOIMON));
//
//            cursor.moveToNext();
//        }
//
//        return magoimon;
//    }

    public boolean KiemTraMonAnDaTonTai(int magoimon, int mamonan) {
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_CHITIETGOIMON + " WHERE " + CreateDatabase.TB_CHITIETGOIMON_MAMONAN
                + " = " + mamonan + " AND " + CreateDatabase.TB_CHITIETGOIMON_MAGOIMON + " = " + magoimon;

        Cursor cursor = database.rawQuery(truyvan, null);
        return (cursor.getCount() != 0);
    }

    public int LaySoLuongMonAnTheoMaGoiMon(int magoimon, int mamonan) {
        int soluong = 0;
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_CHITIETGOIMON + " WHERE " + CreateDatabase.TB_CHITIETGOIMON_MAMONAN
                + " = " + mamonan + " AND " + CreateDatabase.TB_CHITIETGOIMON_MAGOIMON + " = " + magoimon;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            soluong = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CHITIETGOIMON_SOLUONG));

            cursor.moveToNext();
        }

        cursor.close();

        return soluong;
    }

    public int layMaGoiMonCuoiCung(){
        int ma = 0;
        String truyvan = "Select  " + CreateDatabase.TB_GOIMON_MAGOIMON + " from " + CreateDatabase.TB_GOIMON + " order by " + CreateDatabase.TB_GOIMON_MAGOIMON + " desc limit 1" ;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        try {
            ma = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_GOIMON_MAGOIMON));
        }catch (Exception ex){
            ex.printStackTrace();
        }

        cursor.close();
        return ma;
    }

    public boolean CapNhatSoLuong(ChiTietGoiMonDTO chiTietGoiMonDTO) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_CHITIETGOIMON_SOLUONG, chiTietGoiMonDTO.getSoLuong());

        long kiemtra = database.update(CreateDatabase.TB_CHITIETGOIMON, contentValues, CreateDatabase.TB_CHITIETGOIMON_MAGOIMON + " = " + chiTietGoiMonDTO.getMaGoiMon()
                + " AND " + CreateDatabase.TB_CHITIETGOIMON_MAMONAN + " = " + chiTietGoiMonDTO.getMaMonAn(), null);

        return (kiemtra != 0);
    }

    public boolean ThemChiTietGoiMon(ChiTietGoiMonDTO chiTietGoiMonDTO) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_CHITIETGOIMON_SOLUONG, chiTietGoiMonDTO.getSoLuong());
        contentValues.put(CreateDatabase.TB_CHITIETGOIMON_MAGOIMON, chiTietGoiMonDTO.getMaGoiMon());
        contentValues.put(CreateDatabase.TB_CHITIETGOIMON_MAMONAN, chiTietGoiMonDTO.getMaMonAn());

        long kiemtra = database.insert(CreateDatabase.TB_CHITIETGOIMON, null, contentValues);

        return (kiemtra != 0);
    }



    public List<ThanhToanDTO> LayDanhSachMonAnTheoMaGoiMon(int magoimon) {
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_CHITIETGOIMON + " ct," + CreateDatabase.TB_MONAN + " ma WHERE "
                + "ct." + CreateDatabase.TB_CHITIETGOIMON_MAMONAN + " = ma." + CreateDatabase.TB_MONAN_MAMON
                + " AND " + CreateDatabase.TB_CHITIETGOIMON_MAGOIMON + " = '" + magoimon + "'";

        List<ThanhToanDTO> thanhToanDTOs = new ArrayList<>();
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ThanhToanDTO thanhToanDTO = new ThanhToanDTO();
            thanhToanDTO.maGoiMon = magoimon;
            thanhToanDTO.setSoLuong(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CHITIETGOIMON_SOLUONG)));
            thanhToanDTO.setGiaTien(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_GIATIEN)));
            thanhToanDTO.setTenMonAn(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_TENMONAN)));
            thanhToanDTO.setMaMon(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_MAMON)));
            thanhToanDTOs.add(thanhToanDTO);

            cursor.moveToNext();
        }

        cursor.close();

        return thanhToanDTOs;
    }

    public boolean CapNhatTrangThaiGoiMonTheoMaGoiMon(int magoimon, String tinhtrang) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_GOIMON_TINHTRANG, tinhtrang);

        long kiemtra = database.update(CreateDatabase.TB_GOIMON, contentValues, CreateDatabase.TB_GOIMON_MAGOIMON + " = '" + magoimon + "'", null);

        return (kiemtra != 0);
    }


    public boolean XoaGoiMon(int maGoiMon) {
        int kiemtra = database.delete(CreateDatabase.TB_GOIMON, CreateDatabase.TB_GOIMON_MAGOIMON + " = " + maGoiMon, null);
        int kiemtra2= database.delete(CreateDatabase.TB_CHITIETGOIMON , CreateDatabase.TB_GOIMON_MAGOIMON + " = " + maGoiMon, null);
        return (kiemtra != 0 && kiemtra2 !=0);
    }

    public List<GoiMonDTO> LayDanhSachGoiMonChuaTraTien() {
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_GOIMON + " where " + CreateDatabase.TB_GOIMON_TINHTRANG + " = 'false' ";

        List<GoiMonDTO> goiMonDTOList = new ArrayList<>();
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            GoiMonDTO temp = new GoiMonDTO();
            temp.setMaBan(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_GOIMON_MABAN)));
            temp.setSoNguoi(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_GOIMON_SONGUOI)));
//            temp.setTongTienBanAn(cursor.getLong(cursor.getColumnIndex(CreateDatabase.TB_GOIMON_TONGTIENBANAN)));
            temp.setMaGoiMon(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_GOIMON_MAGOIMON)));

            goiMonDTOList.add(temp);

            cursor.moveToNext();
        }

        cursor.close();

        return goiMonDTOList;
    }

    public void closeDatabase(){
        database.close();
    }
}





