package com.example.hoangduy.japanese4you.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HoangDuy on 18/01/2017.
 */
public class Time implements Parcelable {

    public static final Creator<Time> CREATOR = new Creator<Time>() {
        @Override
        public Time createFromParcel(Parcel in) {
            return new Time(in);
        }

        @Override
        public Time[] newArray(int size) {
            return new Time[size];
        }
    };
    private int id;
    private int hour;
    private int minute;
    private String dayofweek;
    private int flag;

    protected Time(Parcel in) {
        hour = in.readInt();
        minute = in.readInt();
        dayofweek = in.readString();
        flag = in.readInt();
        id = in.readInt();
    }

    public Time(int id,int hour, int minute, String dayofweek, int flag) {
        this.hour = hour;
        this.minute = minute;
        this.dayofweek = dayofweek;
        this.flag = flag;
        this.id=id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(hour);
        parcel.writeInt(minute);
        parcel.writeString(dayofweek);
        parcel.writeInt(flag);
        parcel.writeInt(id);
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

