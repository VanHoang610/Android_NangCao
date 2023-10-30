package com.example.myasm.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myasm.DAO.DAOMonHoc;
import com.example.myasm.Model.MONHOC;
import com.example.myasm.Model.NGUOIDUNG;
import com.example.myasm.R;

import java.util.ArrayList;

public class MonHocAdapter extends RecyclerView.Adapter<MonHocAdapter.ViewHolder>{
    Context context;
    ArrayList<MONHOC> list;
    int idNguoiDung;
    private AdapterView.OnItemClickListener itemClickListener;

    public MonHocAdapter(Context context, ArrayList<MONHOC> list, int idNguoiDung) {
        this.context = context;
        this.list = list;
        this.idNguoiDung = idNguoiDung;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(com.example.myasm.R.layout.item_monhoc, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMaMonHoc.setText(list.get(position).getCode());
        holder.tvTenMonHoc.setText(list.get(position).getTenMonHoc());
//        holder.tvGiangVien.setText(list.get(position).getGianVien());
        if(idNguoiDung == list.get(position).getIdMonHoc()){
            holder.btnMonHoc.setText("HỦY ĐĂNG KÝ");
            holder.btnMonHoc.setBackgroundResource(R.drawable.btn_huydangky);

        }else{
            holder.btnMonHoc.setText("ĐĂNG KÝ");
            holder.btnMonHoc.setBackgroundResource(R.drawable.btn_dangky);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaMonHoc, tvTenMonHoc, tvGiangVien;
        Button btnMonHoc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaMonHoc = itemView.findViewById(com.example.myasm.R.id.tvMaMonHoc);
            tvTenMonHoc = itemView.findViewById(com.example.myasm.R.id.tvTenMonHoc);
//            tvGiangVien = itemView.findViewById(com.example.myasm.R.id.tvGiangVien);
            btnMonHoc = itemView.findViewById(R.id.btnMonHoc);

        }
    }

}
