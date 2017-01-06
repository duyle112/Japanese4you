package com.example.hoangduy.japanese4you.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HoangDuy on 04/01/2017.
 */
public class Sentences implements Parcelable{
    private String japaneseSen;
    private String romanjiSen;
    private String englishSen;

    public Sentences(String japaneseSen, String romanjiSen, String englishSen) {
        this.japaneseSen = japaneseSen;
        this.romanjiSen = romanjiSen;
        this.englishSen = englishSen;
    }

    protected Sentences(Parcel in) {
        japaneseSen = in.readString();
        romanjiSen = in.readString();
        englishSen = in.readString();
    }

    public static final Creator<Sentences> CREATOR = new Creator<Sentences>() {
        @Override
        public Sentences createFromParcel(Parcel in) {
            return new Sentences(in);
        }

        @Override
        public Sentences[] newArray(int size) {
            return new Sentences[size];
        }
    };

    public String getJapaneseSen() {
        return japaneseSen;
    }

    public void setJapaneseSen(String japaneseSen) {
        this.japaneseSen = japaneseSen;
    }

    public String getRomanjiSen() {
        return romanjiSen;
    }

    public void setRomanjiSen(String romanjiSen) {
        this.romanjiSen = romanjiSen;
    }

    public String getEnglishSen() {
        return englishSen;
    }

    public void setEnglishSen(String englishSen) {
        this.englishSen = englishSen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(japaneseSen);
        dest.writeString(romanjiSen);
        dest.writeString(englishSen);
    }
}
