package com.example.myasm.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myasm.DAO.DAONguoiDung;
import com.example.myasm.Database.DbHelper;
import com.example.myasm.MainActivity;
import com.example.myasm.Model.NGUOIDUNG;
import com.example.myasm.Model.THONGTIN;

public class LoginService extends Service {
    DAONguoiDung daoNguoiDung = new DAONguoiDung(this);
    DbHelper helper = new DbHelper(this);
    boolean check;
    int idNguoiDung;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        String user = bundle.getString("user");
        String pass = bundle.getString("pass");

        for(NGUOIDUNG ND : daoNguoiDung.getNguoiDung()){
            if(user.equals(ND.getUserName()) &&  pass.equals(ND.getPassWord())){
                idNguoiDung = ND.getIdNguoiDung();
                check = true;
                break;
            }
        }

        Bundle bundle1 = new Bundle();
        bundle1.putBoolean("check", check);
        bundle1.putInt("idNguoiDung", idNguoiDung);
        Intent intent1 = new Intent();
        intent1.putExtras(bundle1);
        intent1.setAction("dangnhap");
        sendBroadcast(intent1);

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
