package com.example.hoangduy.japanese4you.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HoangDuy on 16/01/2017.
 */
public class Exercise implements Parcelable {
    private int id;
    private String name;
    private String category;
    private int group;
    private int testtaken;
    private String lastscore;

    public Exercise(int id, String name, String category, int group, int testtaken, String lastscore) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.group = group;
        this.testtaken = testtaken;
        this.lastscore = lastscore;
    }

    protected Exercise(Parcel in) {
        id = in.readInt();
        name = in.readString();
        category = in.readString();
        group = in.readInt();
        testtaken=in.readInt();
        lastscore=in.readString();
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getTesttaken() {
        return testtaken;
    }

    public void setTesttaken(int testtaken) {
        this.testtaken = testtaken;
    }

    public String getLastscore() {
        return lastscore;
    }

    public void setLastscore(String lastscore) {
        this.lastscore = lastscore;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(category);
        dest.writeInt(group);
        dest.writeInt(testtaken);
        dest.writeString(lastscore);
    }
}
