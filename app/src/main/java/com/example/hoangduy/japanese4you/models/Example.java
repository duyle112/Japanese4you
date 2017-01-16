package com.example.hoangduy.japanese4you.models;

/**
 * Created by HoangDuy on 15/01/2017.
 */
public class Example {
    private String japsentence;
    private String romanjisentence;
    private String engsentence;
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
}