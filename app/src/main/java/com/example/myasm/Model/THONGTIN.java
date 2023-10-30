package com.example.myasm.Model;

public class THONGTIN {
    private int idThongTin;
    private String code;
    private String ngay;
    private String diaChi;

    public THONGTIN(int idThongTin, String code, String ngay, String diaChi) {
        this.idThongTin = idThongTin;
        this.code = code;
        this.ngay = ngay;
        this.diaChi = diaChi;
    }

    public THONGTIN(String code, String ngay, String diaChi) {
        this.code = code;
        this.ngay = ngay;
        this.diaChi = diaChi;
    }

    public int getIdThongTin() {
        return idThongTin;
    }

    public void setIdThongTin(int idThongTin) {
        this.idThongTin = idThongTin;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
