package com.example.myasm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.myasm.Adapter.MonHocAdapter;
import com.example.myasm.Adapter.ThongTinAdapter;
import com.example.myasm.DAO.DAOMonHoc;
import com.example.myasm.DAO.DAOThongTin;
import com.example.myasm.Model.MONHOC;
import com.example.myasm.Model.THONGTIN;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<MONHOC> list;
    DAOMonHoc daoMonHoc = new DAOMonHoc(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);


        Intent i = getIntent();
        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);

        int idNguoiDung = sharedPreferences.getInt("idNguoiDung",-1);
//        Bundle bundle = i.getExtras();
//        int idNguoiDung = bundle.getInt("idNguoiDung", 0);
//        int idNguoiDung = i.getIntExtra("idNguoiDung", 0);
        list = daoMonHoc.getMonHoc(idNguoiDung);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        MonHocAdapter monHocAdapter = new MonHocAdapter(this, list, idNguoiDung);
        recyclerView.setAdapter(monHocAdapter);

    }
}