package com.example.mypc.orderfooddemo2.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mypc.orderfooddemo2.DTO.MonAnDTO;
import com.example.mypc.orderfooddemo2.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 17/02/2018.
 */

public class MonAnDAO {

    SQLiteDatabase database;

    public MonAnDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public boolean ThemMonAn(MonAnDTO monAnDTO) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_MONAN_TENMONAN, monAnDTO.getTenMonAn());
        contentValues.put(CreateDatabase.TB_MONAN_GIATIEN, monAnDTO.getGiaTien());
        contentValues.put(CreateDatabase.TB_MONAN_MALOAI, monAnDTO.getMaLoai());


        long kiemTra = database.insert(CreateDatabase.TB_MONAN, null, contentValues);



        if (kiemTra != 0) {
            return true;
        } else {
            return false;
        }

    }

    public List<MonAnDTO> LayDanhSachMonAnTheoLoai(int maLoai){
        List<MonAnDTO> monAnDTOList = new ArrayList<>();

        String truyvan = "SELECT * FROM " + CreateDatabase.TB_MONAN + " WHERE " + CreateDatabase.TB_MONAN_MALOAI + " = '" + maLoai + "' ";
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MonAnDTO monAnDTO = new MonAnDTO();

            monAnDTO.setTenMonAn(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_TENMONAN)));

            monAnDTO.setGiaTien(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_GIATIEN)));
            monAnDTO.setMaMonAn(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_MAMON)));
            monAnDTO.setMaLoai(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_MALOAI)));

            monAnDTOList.add(monAnDTO);

            cursor.moveToNext();
        }


        return monAnDTOList;
    }

    public void closeDataBase(){
        database.close();
    }


}
