package com.example.myasm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myasm.Database.DbHelper;
import com.example.myasm.Model.MONHOC;

import java.util.ArrayList;

public class DAOMonHoc {
    DbHelper dbHelper;
    public DAOMonHoc(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<MONHOC> getMonHoc(int idNguoiDung){
        ArrayList<MONHOC> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT mh.code, mh.tenMonHoc, mh.gianVien, dk.idNguoiDung FROM MONHOC mh left join DANGKY dk on mh.code = dk.code and dk.idNguoiDung = ?", new String[]{String.valueOf(idNguoiDung)}, null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                MONHOC monhoc = new MONHOC(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
                list.add(monhoc);
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themMonHoc(MONHOC monhoc){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenMonHoc", monhoc.getTenMonHoc());
        values.put("gianVien", monhoc.getGianVien());
        long check =  sqLiteDatabase.insert("MONHOC", null, values);
        if(check == -1)
            return false;
        return true;
    }

    public boolean xoaMonHoc(int idMonHoc){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("MONHOC", "idMonHoc = ?", new String[]{String.valueOf(idMonHoc)});
        if(check == -1)
            return false;
        return true;
    }

    public boolean suaMonHoc(int idMonHoc, MONHOC monhoc){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenMonHoc", monhoc.getTenMonHoc());
        values.put("gianVien", monhoc.getGianVien());
        long check = sqLiteDatabase.update("MONHOC", values, "idMonHoc = ?", new String[]{String.valueOf(idMonHoc)});
        if (check == -1)
            return false;
        return true;
    }
}
