package com.example.myflappybird.interface_elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myflappybird.R;

public abstract class Button{
    public float x, y;
    public float width, height;
    protected Bitmap button_surface;

    public Button(Context context, float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public boolean isClicked(float pointX, float pointY){
        if (pointX >= x && pointX <= x + width && pointY >= y && pointY <= y + height)
            return true;
        return false;
    }

    public Bitmap getButton_surface() {
        return button_surface;
    }
}
