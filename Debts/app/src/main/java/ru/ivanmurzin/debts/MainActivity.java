package ru.ivanmurzin.debts;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.ivanmurzin.debts.database.model.Debt;
import ru.ivanmurzin.debts.database.room.DebtDAO;
import ru.ivanmurzin.debts.database.room.DebtEntity;
import ru.ivanmurzin.debts.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface{
    ActivityMainBinding binding;
    MyDebtsAdapter myDebtsAdapter;
    List<DebtEntity> debts;
    Dialog dialog;

    DebtDAO debtDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new Dialog(MainActivity.this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        debtDAO = App.getDatabase().debtDAO();


        List<DebtEntity> data = new ArrayList<>();
        new Thread(() -> {
            debts = debtDAO.getAll();
            runOnUiThread(() -> {
                data.addAll(debts);
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                myDebtsAdapter = new MyDebtsAdapter(data, this);
                binding.recyclerView.setAdapter(myDebtsAdapter);
            });
        }).start();

        binding.add.setOnClickListener(view -> {
            DebtEntity debt = new DebtEntity("123", (int) (Math.random() * 1000));
            data.add(debt);
            new Thread(() -> {
                debtDAO.save(debt);
            }).start();
            binding.recyclerView.getAdapter().notifyItemChanged(data.size() - 1);
        });
    }

    @Override
    public void onCItemlick(int position) {
        dialog.setContentView(R.layout.alert_dialogue_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button confirm_button = dialog.findViewById(R.id.confirm_button);
        EditText new_number = dialog.findViewById(R.id.new_number);
        EditText new_name = dialog.findViewById(R.id.new_name);
        DebtEntity debt = debts.get(position);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                debt.setMoney(Integer.parseInt(new_number.getText().toString()));
                debt.setMoney(Integer.parseInt(new_name.getText().toString()));
                debts.set(position, debt);
                debtDAO.change(debt);
                dialog.cancel();
            }
        });
    }


    @Override
    public void onItemLongClick(int position) {
        Log.i("", String.valueOf(position));
        Log.i("", String.valueOf(debts.size()));
        DebtEntity debt = debts.get(position);
        debts.remove(position);
        myDebtsAdapter.notifyItemRemoved(position);
        new Thread(() -> {
            debtDAO.delete(debt);
        }).start();
    }
}