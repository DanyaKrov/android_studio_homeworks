package com.example.rickastley;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rickastley.databinding.ActivityMainBinding;
import com.example.rickastley.databinding.PageMainBinding;
public class MainPage extends AppCompatActivity implements View.OnClickListener{
    PageMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PageMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        String email = getIntent().getStringExtra("email");
        binding.greetings.setText(email);
        binding.buttonTakePhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.i("Rick_Astley", "pressed photo button");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, MAKE_PHOTO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

}