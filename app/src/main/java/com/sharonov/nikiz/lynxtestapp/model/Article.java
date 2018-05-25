package com.sharonov.nikiz.lynxtestapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Article implements Parcelable {
    @SerializedName("team1")
    private String team1;
    @SerializedName("team2")
    private String team2;
    @SerializedName("tournament")
    private String tournament;
    @SerializedName("place")
    private String place;
    @SerializedName("prediction")
    private String prediction;
    @SerializedName("article")
    private List<ArcticleItem> articleItems;

    private Article(Parcel in) {
        team1 = in.readString();
        team2 = in.readString();
        tournament = in.readString();
        place = in.readString();
        prediction = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public List<ArcticleItem> getArticleItems() {
        return articleItems;
    }

    public void setArticleItems(List<ArcticleItem> articleItems) {
        this.articleItems = articleItems;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(team1);
        parcel.writeString(team2);
        parcel.writeString(tournament);
        parcel.writeString(place);
        parcel.writeString(prediction);
    }
}
