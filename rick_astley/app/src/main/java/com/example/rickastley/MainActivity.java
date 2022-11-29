package com.example.rickastley;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rickastley.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.i("Rick_Astley", "Sign button pressed");
        String email = binding.textViewEmail.getText().toString();
        String password = binding.textViewPassword.getText().toString();
        if (email.equals("rickroll@gmail.com") && password.equals("RickAstley")){
            Log.i("Rick_Astley", "User logged as " + email + " with password: " + password);
            Intent intent = new Intent(MainActivity.this, MainPage.class);
            intent.putExtra("email", email);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(this, "Вы ввели неверную почту или пароль",
                    Toast.LENGTH_SHORT).show();
            Log.e("Rick_Astley","try to input wrong email or password");
        }
        }
}