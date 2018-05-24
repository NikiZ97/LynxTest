package com.sharonov.nikiz.lynxtestapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class NewsItem implements Parcelable{
    @SerializedName("title")
    private String title;
    @SerializedName("coefficient")
    private String coefficient;
    @SerializedName("time")
    private String time;
    @SerializedName("place")
    private String place;
    @SerializedName("preview")
    private String preview;
    @SerializedName("article")
    private String article;

    private NewsItem(Parcel in) {
        title = in.readString();
        coefficient = in.readString();
        time = in.readString();
        place = in.readString();
        preview = in.readString();
        article = in.readString();
    }

    public static final Creator<NewsItem> CREATOR = new Creator<NewsItem>() {
        @Override
        public NewsItem createFromParcel(Parcel in) {
            return new NewsItem(in);
        }

        @Override
        public NewsItem[] newArray(int size) {
            return new NewsItem[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(coefficient);
        parcel.writeString(time);
        parcel.writeString(place);
        parcel.writeString(preview);
        parcel.writeString(article);
    }
}
