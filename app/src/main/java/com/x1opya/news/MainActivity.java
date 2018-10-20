package com.x1opya.news;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.StackAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CardStackView.ItemExpendListener{



    Retrofit retrofit;
    Api api;

    String key;

    ExpandableListView lv;
    CardStackView stackView;
    MyStackAdapter adapter;
    private LinearLayout mActionButtonContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActionButtonContainer = findViewById(R.id.button_container);
        initRetrofit();
        initListView();
        getLastNews();
    }

    private void initListView(){
        //lv = findViewById(R.id.expnd_lv);

        stackView = findViewById(R.id.stack_view);
        adapter = new MyStackAdapter(this);
        stackView.setAdapter(adapter);
        stackView.setItemExpendListener(this);
        //stackView.set
    }

    private void initRetrofit(){
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://newsapi.org/")
                .build();
        api = retrofit.create(Api.class);
        Map<String,String> options = new HashMap<>();
        options.put("country","ru");
        options.put("apiKey", "76f2d901b6d14c45a2cc0356f0c56847");
        key = getResources().getString(R.string.key);
    }

    public void getLastNews() {
        Map<String,String> options = new HashMap<>();
        options.put("country","ru");
        options.put("apiKey", key);
        api.getNews(options).enqueue(new Callback<NewRespons>() {
            @Override
            public void onResponse(Call<NewRespons> call, Response<NewRespons> response) {
                String body = response.body().toString();
                String head = response.headers().toString();
                NewRespons news = response.body();
                adapter.setData(news.articles);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewRespons> call, Throwable t) {

            }
        });

    }

    @Override
    public void onItemExpend(boolean expend) {
        mActionButtonContainer.setVisibility(expend ? View.VISIBLE : View.GONE);
    }

    public void onPreClick(View view) {
        stackView.pre();
    }

    public void onNextClick(View view) {
        stackView.next();
    }
}
