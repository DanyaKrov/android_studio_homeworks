package com.example.homework_9;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homework_9.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final Problem problem = new Problem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        generateProblem();
        MyClickListener listener = new MyClickListener();
        binding.next.setOnClickListener(listener);
        binding.solution1.setOnClickListener(listener);
        binding.solution2.setOnClickListener(listener);
        binding.solution3.setOnClickListener(listener);
    }

    @SuppressLint("ResourceAsColor")
    private void generateProblem() {
        binding.problem.setText(problem.getProblem());
        int position = problem.getRandom(1, 4);
        switch (position) {
            case 1:
                binding.solution1.setText(String.valueOf(problem.getResult()));
                binding.solution2.setText(String.valueOf(problem.getNoiseResult()));
                binding.solution3.setText(String.valueOf(problem.getNoiseResult()));
                break;
            case 2:
                binding.solution1.setText(String.valueOf(problem.getNoiseResult()));
                binding.solution2.setText(String.valueOf(problem.getResult()));
                binding.solution3.setText(String.valueOf(problem.getNoiseResult()));
                break;
            case 3:
                binding.solution1.setText(String.valueOf(problem.getNoiseResult()));
                binding.solution2.setText(String.valueOf(problem.getNoiseResult()));
                binding.solution3.setText(String.valueOf(problem.getResult()));
                break;
        }
    }


    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.next:
                    generateProblem();
                    break;
                case R.id.solution_1:
                case R.id.solution_2:
                case R.id.solution_3:
                    String text = ((TextView) view).getText().toString();
                    if (text.equals(String.valueOf(problem.getResult()))) {
                        view.setBackgroundColor(getColor((R.color.correct)));
                        Toast.makeText(MainActivity.this, "Правильно!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        view.setBackgroundColor(getColor((R.color.wrong)));
                        Toast.makeText(MainActivity.this, "Неправильно!", Toast.LENGTH_SHORT).show();
                    }

            }
        }
    }
}

