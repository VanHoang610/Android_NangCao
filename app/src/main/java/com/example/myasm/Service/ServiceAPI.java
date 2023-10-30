package com.example.myasm.Service;

import com.example.myasm.Model.ListJSON2;
import com.example.myasm.Model.Model_Channel;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
public interface ServiceAPI {
    String BASE_SERVICE = "https://apis.dinhnt.com/";

    @GET("sample2.json")
    Observable<ArrayList<ListJSON2>> getListJON2();

    @GET("edu.json")
    Observable<Model_Channel> getChannel();



}
