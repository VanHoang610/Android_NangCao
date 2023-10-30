package com.example.myasm.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myasm.Model.MONHOC;
import com.example.myasm.Model.THONGTIN;
import com.example.myasm.R;

import java.util.ArrayList;

public class ThongTinAdapter extends RecyclerView.Adapter<ThongTinAdapter.ViewHolder> {
    Context context;
    ArrayList<THONGTIN> list;

    public ThongTinAdapter(Context context, ArrayList<THONGTIN> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thongtin, null, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCaHoc.setText(list.get(position).getNgay());
        holder.tvMonHoc.setText(list.get(position).getCode());
        holder.btnDangKy.setText("Đã đăng ký");
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMonHoc, tvCaHoc;
        Button btnDangKy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCaHoc = itemView.findViewById(R.id.tvCaHoc);
            tvMonHoc = itemView.findViewById(R.id.tvMonHoc);
            btnDangKy = itemView.findViewById(R.id.btnDangKy);
        }
    }
}
