package com.example.hoangduy.japanese4you.models;

/**
 * Created by HoangDuy on 03/01/2017.
 */
public class Section {

    private int icon;
    private String content;

    public Section(int icon, String content) {
        this.icon = icon;
        this.content = content;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
