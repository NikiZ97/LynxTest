package com.sharonov.nikiz.lynxtestapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ArcticleItem implements Parcelable{
    private String header;
    private String text;

    public ArcticleItem(String header, String text) {
        this.header = header;
        this.text = text;
    }

    private ArcticleItem(Parcel in) {
        header = in.readString();
        text = in.readString();
    }

    public static final Creator<ArcticleItem> CREATOR = new Creator<ArcticleItem>() {
        @Override
        public ArcticleItem createFromParcel(Parcel in) {
            return new ArcticleItem(in);
        }

        @Override
        public ArcticleItem[] newArray(int size) {
            return new ArcticleItem[size];
        }
    };

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(header);
        parcel.writeString(text);
    }
}
