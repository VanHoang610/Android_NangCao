package com.example.myasm.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "QLMH", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE NGUOIDUNG(idNguoiDung integer primary key autoincrement, userName text, passWord text, hoTen text)");
        db.execSQL("CREATE TABLE MONHOC(code text, tenMonHoc text, gianVien text)");
        db.execSQL("CREATE TABLE THONGTIN(idThongTin integer primary key autoincrement,code text, ngay text, diaChi text, FOREIGN KEY(code) REFERENCES MONHOC(code))");
        db.execSQL("CREATE TABLE DANGKY(idNguoiDung integer, code text, FOREIGN KEY(idNguoiDung) REFERENCES NGUOIDUNG(idNguoiDung), FOREIGN KEY(code) REFERENCES MONHOC(code))");

        db.execSQL("INSERT INTO NGUOIDUNG VALUES(1,'tridinh','123456','Trí Định'),(2,'minhtri','123abc123','Minh Trí')");
        db.execSQL("INSERT INTO MONHOC VALUES('MOB201','Android Nâng Cao','Nguyễn Trí Định'),('MOB306','React Native','Trần Anh Hùng'),('MOB2041','Dự Án Mẫu','Nguyễn Trí Định')");
        db.execSQL("INSERT INTO THONGTIN VALUES(1, 'MOB201', 'Ca 2 - 19/09/2022', 'T1011'),(2, 'MOB201', 'Ca 2 - 21/09/2022', 'T1011'),(3, 'MOB201', 'Ca 2 - 23/09/2022', 'T1011'),(4, 'MOB306', 'Ca 5 - 20/09/2022', 'F204'),(5, 'MOB306', 'Ca 5 - 22/09/2022', 'F204'),(6, 'MOB2041', 'Ca 1 - 20/09/2022', 'Online - https://meet.google.com/rku-beuk-wqu')");
        db.execSQL("INSERT INTO DANGKY VALUES(1,'MOB201'),(1,'MOB306'),(2,'MOB306')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i != i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS MONHOC");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THONGTIN");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DANGKY");
            onCreate(sqLiteDatabase);
        }
    }
}
