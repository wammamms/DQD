package com.example.dqd.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dqd.News;
import com.example.dqd.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private ArrayList<News> mNews ;
    private Context mContext;

    public NewsAdapter(ArrayList<News> newsList) {
        this.mNews = newsList;
    }

    public void setContext(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_test, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, final int position) {
        News news = mNews.get(position);
//        Bitmap pngBM = setImageURL(news.getURL().toString());
        //imageView.setImageBitmap(pngBM);

        holder.imageview.setImageResource(news.getImageId());
 //       holder.imageview.setImageBitmap(pngBM);
        holder.textview.setText(news.getName());

        //item的点击
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"第"+(position+1)+"个条目被点击了",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.setAction("SHOW_ARTICAL_ACTION");
                intent.addCategory("ARTICAL_CATEGORY");
                mContext.startActivity(intent);
            }
        });
    }

    //设置网络图片
    public Bitmap setImageURL(final String path) {
        final Bitmap[] bitmap = {null};
        //开启一个线程用于联网
        new Thread() {
            @Override
            public void run() {
                try {
                    //把传过来的路径转成URL
                    URL url = new URL(path);
                    //获取连接
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //使用GET方法访问网络
                    connection.setRequestMethod("GET");
                    //超时时间为10秒
                    connection.setConnectTimeout(10000);
                    //获取返回码
                    int code = connection.getResponseCode();
                    if (code == 200) {
                        InputStream inputStream = connection.getInputStream();
                        //使用工厂把网络的输入流生产Bitmap
                        bitmap[0] = BitmapFactory.decodeStream(inputStream);
                        //利用Message把图片发给Handler
                        inputStream.close();
                    }else {
                        //服务启发生错误
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    //网络连接错误
                }
            }
        }.start();
        return bitmap[0];
    }



    @Override
    public int getItemCount() {
        if (mNews != null) {
            return mNews.size();
        }
        return 0;
    }

    //InnerHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageview;
        TextView textview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageview = (ImageView) itemView.findViewById(R.id.tiv);
            textview = (TextView) itemView.findViewById(R.id.ttv);
        }
    }
}
