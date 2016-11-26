package com.example.tuan_fpt.session93_serializedname;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.tuan_fpt.session93_serializedname.adapters.RepoAdapter;
import com.example.tuan_fpt.session93_serializedname.jsonmodels.Repository;
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

    @BindView(R.id.lv_repository)
    ListView lvRepository;
    private ArrayList<Repository> repoList;
    private RepoAdapter repoAdapter;
    String url = "https://api.github.com/repositories";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        repoList = new ArrayList<>();
        repoAdapter = new RepoAdapter(this,R.layout.list_item_repository, repoList);
        sendGet();
        lvRepository.setAdapter(repoAdapter);
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
                Repository[] repoItems = gson.fromJson(body, Repository[].class);
//                for(Repository repository : repoItems){
//                    repository.getOwner().
//                }
                repoList.clear();
                repoList.addAll(Arrays.asList(repoItems));
                // come back to main thread
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        repoAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
