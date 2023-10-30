package com.example.myasm;

import static com.example.myasm.Service.ServiceAPI.BASE_SERVICE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myasm.Adapter.NewsAPIAdapter;
import com.example.myasm.Model.Model_Channel;
import com.example.myasm.Model.Item;
import com.example.myasm.Service.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsAPI extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Item> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_api);
        recyclerView = findViewById(R.id.recyclerView);
        list = new ArrayList<>();
        demoCallAPI();
        loadData();
    }

    private void demoCallAPI() {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.getChannel()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(Model_Channel model_channel){
        list = model_channel.getChannel().getItem();
        loadData();
    }

    private void handleError(Throwable throwable){
        Toast.makeText(this, "Lỗi API", Toast.LENGTH_SHORT).show();
    }
    private void loadData(){
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        NewsAPIAdapter adapter = new NewsAPIAdapter(NewsAPI.this, list);
        recyclerView.setAdapter(adapter);
    }
}