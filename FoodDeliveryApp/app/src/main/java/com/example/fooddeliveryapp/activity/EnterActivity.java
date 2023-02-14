package com.example.fooddeliveryapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.activity.adapters.EnterPagerAdapter;
import com.example.fooddeliveryapp.databinding.ActivityEnterBinding;
import com.example.fooddeliveryapp.databinding.ActivityMainBinding;

public class EnterActivity extends AppCompatActivity {
    ActivityEnterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEnterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                runOnUiThread(() -> {
                    binding.mainIcon.setVisibility(View.VISIBLE);
                });
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }).start();

        binding.pager.setAdapter(new EnterPagerAdapter(getSupportFragmentManager()));
        binding.tabs.setupWithViewPager(binding.pager);
    }

}