package com.example.myasm.Model;

public class MONHOC {
    private String code;
    private String tenMonHoc;
    private String gianVien;
    private int idMonHoc;

    public MONHOC(String code, String tenMonHoc, String gianVien) {
        this.code = code;
        this.tenMonHoc = tenMonHoc;
        this.gianVien = gianVien;
    }

    public MONHOC(String code, String tenMonHoc, String gianVien, int idMonHoc) {
        this.code = code;
        this.tenMonHoc = tenMonHoc;
        this.gianVien = gianVien;
        this.idMonHoc = idMonHoc;
    }

    public int getIdMonHoc() {
        return idMonHoc;
    }

    public void setIdMonHoc(int idMonHoc) {
        this.idMonHoc = idMonHoc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getGianVien() {
        return gianVien;
    }

    public void setGianVien(String gianVien) {
        this.gianVien = gianVien;
    }
}
