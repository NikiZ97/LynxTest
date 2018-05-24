package com.sharonov.nikiz.lynxtestapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsResponse {

    @SerializedName("events")
    private ArrayList<NewsItem> events;

    public ArrayList<NewsItem> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<NewsItem> events) {
        this.events = events;
    }
}
