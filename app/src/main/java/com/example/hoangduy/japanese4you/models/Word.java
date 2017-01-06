package com.example.hoangduy.japanese4you.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HoangDuy on 04/01/2017.
 */
public class Word implements Parcelable{
    private String kanji;
    private String romanji;
    private String meaning;

    public Word(String kanji, String romanji, String meaning) {
        this.kanji = kanji;
        this.romanji = romanji;
        this.meaning = meaning;
    }

    protected Word(Parcel in) {
        kanji = in.readString();
        romanji = in.readString();
        meaning = in.readString();
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getRomanji() {
        return romanji;
    }

    public void setRomanji(String romanji) {
        this.romanji = romanji;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kanji);
        dest.writeString(romanji);
        dest.writeString(meaning);
    }
}
