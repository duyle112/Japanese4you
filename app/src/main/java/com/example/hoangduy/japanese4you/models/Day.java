package com.example.hoangduy.japanese4you.models;

/**
 * Created by HoangDuy on 18/01/2017.
 */
public class Day {
    private String dayOfweek;
    private boolean isCheck;

    public Day(String dayOfweek, boolean isCheck) {
        this.dayOfweek = dayOfweek;
        this.isCheck = isCheck;
    }

    public String getDayOfweek() {
        return dayOfweek;
    }

    public void setDayOfweek(String dayOfweek) {
        this.dayOfweek = dayOfweek;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
