package com.example.myasm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myasm.Database.DbHelper;
import com.example.myasm.Model.NGUOIDUNG;

import java.util.ArrayList;

public class DAONguoiDung {
    DbHelper dbHelper;
    public DAONguoiDung(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<NGUOIDUNG> getNguoiDung(){
        ArrayList<NGUOIDUNG> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG", null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                NGUOIDUNG nguoidung = new NGUOIDUNG(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                list.add(nguoidung);
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themNguoiDung(NGUOIDUNG nguoidung){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("passWord", nguoidung.getPassWord());
        values.put("userName", nguoidung.getUserName());
        values.put("hoTen", nguoidung.getHoTen());
        long check =  sqLiteDatabase.insert("NGUOIDUNG", null, values);
        if(check == -1)
            return false;
        return true;
    }

    public boolean xoaNguoiDung(int idNguoiDung){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("NGUOIDUNG", "idNguoiDung = ?", new String[]{String.valueOf(idNguoiDung)});
        if(check == -1)
            return false;
        return true;
    }

    public boolean suaNguoiDung(int idNguoiDung, NGUOIDUNG nguoidung){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("passWord", nguoidung.getPassWord());
        values.put("userName", nguoidung.getUserName());
        values.put("hoTen", nguoidung.getHoTen());
        long check = sqLiteDatabase.update("NGUOIDUNG", values, "idNguoiDung = ?", new String[]{String.valueOf(idNguoiDung)});
        if (check == -1)
            return false;
        return true;
    }
}
