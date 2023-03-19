package ru.ivanmurzin.debts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.ivanmurzin.debts.database.room.DebtEntity;
import ru.ivanmurzin.debts.databinding.ItemDebtBinding;

public class MyDebtsAdapter extends RecyclerView.Adapter<MyDebtsAdapter.MyHolder> {

    List<DebtEntity> data;
    private final RecyclerViewInterface recyclerViewInterface;

    public MyDebtsAdapter(List<DebtEntity> data, RecyclerViewInterface recyclerViewInterface) {
        this.data = data;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_debt, parent, false);
        ItemDebtBinding binding = ItemDebtBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyHolder(binding.getRoot(), recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.name.setText(data.get(position).name);
        holder.money.setText(String.valueOf(data.get(position).money));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView money;

        public MyHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            money = itemView.findViewById(R.id.money);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onCItemlick(position);
                        }
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (recyclerViewInterface != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemLongClick(position);
                        }
                    }
                    return true;
                }
            });
        }
    }
}
