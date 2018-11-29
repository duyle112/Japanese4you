package com.example.hoangduy.japanese4you.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HoangDuy on 04/01/2017.
 */
public class Word implements Parcelable{
    private String kana;
    private String kanji;
    private String romanji;
    private String type;
    private String meaning;
    private String category;
    private int favorite;
    private int idword;


    public Word(int idword,String kana, String kanji, String romanji, String type, String meaning, String category, int favorite) {
        super();
        this.kana = kana;
        this.kanji = kanji;
        this.romanji = romanji;
        this.type = type;
        this.meaning = meaning;
        this.category = category;
        this.favorite = favorite;
        this.idword=idword;
    }

    protected Word(Parcel in) {
        kana = in.readString();
        kanji = in.readString();
        romanji = in.readString();
        type = in.readString();
        meaning = in.readString();
        category = in.readString();
        favorite = in.readInt();
        idword=in.readInt();
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

    public String getKana() {
        return kana;
    }
    public void setKana(String kana) {
        this.kana = kana;
    }
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getMeaning() {
        return meaning;
    }
    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getFavorite() {
        return favorite;
    }
    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public int getIdword() {
        return idword;
    }

    public void setIdword(int idword) {
        this.idword = idword;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kana);
        dest.writeString(kanji);
        dest.writeString(romanji);
        dest.writeString(type);
        dest.writeString(meaning);
        dest.writeString(category);
        dest.writeInt(favorite);
        dest.writeInt(idword);
    }
}