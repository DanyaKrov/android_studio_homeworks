package com.example.myflappybird.interface_elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myflappybird.R;

public class RestartButton extends Button {
    public RestartButton(Context context, float x, float y, float width, float height) {
        super(context, x, y, width, height);
        button_surface = BitmapFactory.decodeResource(context.getResources(), R.drawable.button_restart);
        button_surface = Bitmap.createScaledBitmap(button_surface, (int) width, (int) height, false);
    }
}
