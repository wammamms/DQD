package com.example.dqd;

import java.net.URL;

public class News {
    private String name;
    private int imageId;
    private URL url;

    public News(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public News(String name, URL url){
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public URL getURL() {
        return url;
    }
}
