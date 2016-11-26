package com.example.tuan_fpt.session92_loadimageurl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.tuan_fpt.session92_loadimageurl.adapters.FoodApdater;
import com.example.tuan_fpt.session92_loadimageurl.jsonmodels.FoodItem;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lv_foods)
    ListView lvFoods;
    private ArrayList<FoodItem> foodList;
    private FoodApdater foodApdater;
    String url = "https://a-server.herokuapp.com/api/food";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        foodList = new ArrayList<>();
        foodApdater = new FoodApdater(this,R.layout.list_item_food, foodList);
        sendGet();
        lvFoods.setAdapter(foodApdater);
    }
    private void sendGet() {
        // create client
        OkHttpClient client = new OkHttpClient();

        // create request

        final Request request = new Request.Builder()
                .url(url)
                .build();

        // send request ( Sync and Async)

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string(); // only call one time
                Gson gson = new Gson();
                FoodItem[] foodItems = gson.fromJson(body, FoodItem[].class);
                foodList.clear();
                foodList.addAll(Arrays.asList(foodItems));
                // come back to main thread
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        foodApdater.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
