package com.example.a1_homework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.Math;

public class MainActivity extends AppCompatActivity {

    private EditText editTextA;
    private EditText editTextB;
    private EditText editTextC;
    private TextView textViewX1;
    private TextView textViewX2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextA = (EditText) findViewById(R.id.edit_text_a);
        editTextB = (EditText) findViewById(R.id.edit_text_b);
        editTextC = (EditText) findViewById(R.id.edit_text_c);
        textViewX1 = (TextView) findViewById(R.id.textview_x1);
        textViewX2 = (TextView) findViewById(R.id.textview_x2);


    }
    public void click(View view){
        try {
            int a, b, c;
            if (editTextA.getText().toString().trim().length() > 0) {
                a = Integer.parseInt(editTextA.getText().toString());
            }
            else{
                a = 0;
            }
            if (editTextB.getText().toString().trim().length() > 0) {
                b = Integer.parseInt(editTextB.getText().toString());
            }
            else{
                b = 0;
            }
            if (editTextC.getText().toString().trim().length() > 0) {
                c = Integer.parseInt(editTextC.getText().toString());
            }
            else{
                c = 0;
            }
            if (a != 0){
                double d = (b * b - 4 * a * c);
                if (d >= 0){
                    d = Math.sqrt(d);
                    double x1 = (b - d) / (2 * a);
                    double x2 = (b + d) / (2 * a);
                    textViewX1.setText(String.valueOf(x1));
                    if (d == 0)
                        textViewX2.setText("Дискриминант равен нулю");
                    else
                        textViewX2.setText(String.valueOf(x2));
                }
                else{
                    textViewX1.setText("Нет корней, дискриминант меньше нуля");
                    textViewX2.setText("");
                }
            }
            else{
                textViewX1.setText("Коэффицент a не может быть равен нулю");
                textViewX2.setText("");
            }
        }
        catch (Exception e){
            textViewX1.setText("Введите нормальные значения, пожалуйста");
            textViewX2.setText("");
        }

    }
}