package com.example.hoangduy.japanese4you.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HoangDuy on 15/01/2017.
 */
public class Example implements Parcelable{
    private String japsentence;
    private String romanjisentence;
    private String engsentence;

    protected Example(Parcel in) {
        japsentence = in.readString();
        romanjisentence = in.readString();
        engsentence = in.readString();
    }

    public static final Creator<Example> CREATOR = new Creator<Example>() {
        @Override
        public Example createFromParcel(Parcel in) {
            return new Example(in);
        }

        @Override
        public Example[] newArray(int size) {
            return new Example[size];
        }
    };

    public String getJapsentence() {
        return japsentence;
    }
    public void setJapsentence(String japsentence) {
        this.japsentence = japsentence;
    }
    public String getRomanjisentence() {
        return romanjisentence;
    }
    public void setRomanjisentence(String romanjisentence) {
        this.romanjisentence = romanjisentence;
    }
    public String getEngsentence() {
        return engsentence;
    }
    public void setEngsentence(String engsentence) {
        this.engsentence = engsentence;
    }
    public Example(String japsentence, String romanjisentence, String engsentence) {
        super();
        this.japsentence = japsentence;
        this.romanjisentence = romanjisentence;
        this.engsentence = engsentence;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(japsentence);
        dest.writeString(romanjisentence);
        dest.writeString(engsentence);
    }
}