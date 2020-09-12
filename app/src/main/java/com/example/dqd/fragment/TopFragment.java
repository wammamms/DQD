package com.example.dqd.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dqd.News;
import com.example.dqd.R;
import com.example.dqd.adapter.NewsAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TopFragment extends Fragment {
    private final String TAG =".TopFragment.TAG";
    private ArrayList<News> mNewsList = new ArrayList<>();
    private Context mContext;
    private RecyclerView recyclerView;
    private URL url;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top,container,false);

        startOKHttp();

//        for(int i=1;i<100;i++){
//            News new1 = new News("测试"+i,R.drawable.test);
//            mNewsList.add(new1);
//        }
        //RecyclerView
        recyclerView=  (RecyclerView)view.findViewById(R.id.top_rc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(mNewsList);

        mContext = getContext();
        adapter.setContext(mContext);

        recyclerView.setAdapter(adapter);

        return view;
    }

    private void startOKHttp() {
        String url = "https://api.dongqiudi.com/v3/useract/app/search/getIndex";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TAG", "onFailure: "+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();//response.body().string()中的 (.string)只能使用一次
                parseDiffJson(json);
                Log.d("TAG", "onResponse: " + json);
            }
        });
    }

    private void parseDiffJson(String j) {
        try {
            JSONObject jsonObject = new JSONObject(j);
            Log.e("Json-->", j);

//            //取出code,message
//            int code = jsonObject1.getInt("code");
//            Log.d("Json code", code + "");
//            String message = jsonObject1.getString("message");
//            Log.d("Json message ", message+"");
              //取出data
              JSONObject json= jsonObject.getJSONObject("data");
//            Log.d("Json data -->", json.toString());
            //取出hot数组
            JSONArray jsonArray2 = json.getJSONArray("hot");
            for(int i = 0 ; i < jsonArray2.length() ; i++){
                JSONObject newsjs = jsonArray2.getJSONObject(i);
                Log.d("Json  hot->", newsjs.toString());

                url = new URL(newsjs.getString("image"));
                News news = new News(newsjs.getString("name"),R.drawable.test);
                mNewsList.add(news);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
