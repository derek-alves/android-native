package com.derek.fitnesstracker;

public class MainItem {
    private int id;
    private int drawableId;
    private int textStringId;
    private int color;

    public MainItem(int id, int drawableId, int textStringId,int color) {
        this.id = id;
        this.textStringId= textStringId;
        this.drawableId = drawableId;
        this.color = color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTextStringId(int textStringId) {
        this.textStringId = textStringId;
    }

    public int getColor() {
        return color;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public int getTextStringId() {
        return textStringId;
    }

    public int getId() {
        return id;
    }
}
