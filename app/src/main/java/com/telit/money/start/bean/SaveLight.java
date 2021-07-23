package com.telit.money.start.bean;

import org.litepal.crud.LitePalSupport;

public class SaveLight extends LitePalSupport {
    private int index;
    private String word;

    private String adress;

    public SaveLight() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
