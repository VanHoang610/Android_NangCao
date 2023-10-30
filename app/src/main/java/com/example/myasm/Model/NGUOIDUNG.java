package com.example.myasm.Model;

public class NGUOIDUNG {
    private int idNguoiDung;
    private String userName;
    private String passWord;
    private String hoTen;

    public NGUOIDUNG(int idNguoiDung, String userName, String passWord, String hoTen) {
        this.idNguoiDung = idNguoiDung;
        this.userName = userName;
        this.passWord = passWord;
        this.hoTen = hoTen;
    }

    public NGUOIDUNG(String userName, String passWord, String hoTen) {
        this.userName = userName;
        this.passWord = passWord;
        this.hoTen = hoTen;
    }

    public int getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
}
