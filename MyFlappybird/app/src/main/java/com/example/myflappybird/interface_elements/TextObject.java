package com.example.myflappybird.interface_elements;

public abstract class TextObject {
    public float x, y;
    public TextObject(float x, float y){
        this.x = x;
        this.y = y;
    }
    public abstract void changeText();
    public abstract void setDefault();
}
