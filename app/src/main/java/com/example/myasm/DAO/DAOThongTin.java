package com.example.myasm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myasm.Database.DbHelper;
import com.example.myasm.Model.NGUOIDUNG;
import com.example.myasm.Model.THONGTIN;

import java.util.ArrayList;

public class DAOThongTin {
    DbHelper dbHelper;
    public DAOThongTin(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<THONGTIN> getThongTin(){
        ArrayList<THONGTIN> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THONGTIN", null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                THONGTIN thongtin = new THONGTIN(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                list.add(thongtin);
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themThongTin(THONGTIN thongtin){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("code", thongtin.getCode());
        values.put("ngay", thongtin.getNgay());
        values.put("diaChi", thongtin.getDiaChi());
        long check =  sqLiteDatabase.insert("THONGTIN", null, values);
        if(check == -1)
            return false;
        return true;
    }

    public boolean xoaThongTin(int idThongTin){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("THONGTIN", "idThongTin = ?", new String[]{String.valueOf(idThongTin)});
        if(check == -1)
            return false;
        return true;
    }

    public boolean suaThongTin(int idThongTin, THONGTIN thongtin){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("code", thongtin.getCode());
        values.put("ngay", thongtin.getNgay());
        values.put("diaChi", thongtin.getDiaChi());
        long check = sqLiteDatabase.update("THONGTIN", values, "idThongTin = ?", new String[]{String.valueOf(idThongTin)});
        if (check == -1)
            return false;
        return true;
    }
}
