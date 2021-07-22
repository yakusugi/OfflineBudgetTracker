package com.example.offlinebudgettracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.offlinebudgettracker.R;
import com.example.offlinebudgettracker.model.BudgetTrackerDto;

import java.util.List;
import java.util.Objects;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private List<BudgetTrackerDto> budgetTrackerList;
    private Context context;

    public RecycleViewAdapter(List<BudgetTrackerDto> budgetTrackerList, Context context) {
        this.budgetTrackerList = budgetTrackerList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.budget_tracker_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BudgetTrackerDto budgetTrackerDto = Objects.requireNonNull(budgetTrackerList.get(position));
        holder.date.setText(budgetTrackerDto.getDate());
        holder.storeName.setText(budgetTrackerDto.getStoreName());
        holder.productName.setText(budgetTrackerDto.getProductName());
        holder.productType.setText(budgetTrackerDto.getProductType());
        holder.price.setText(String.valueOf(budgetTrackerDto.getPrice()));
    }

    @Override
    public int getItemCount() {
        return Objects.requireNonNull(budgetTrackerList.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView storeName;
        public TextView productName;
        public TextView productType;
        public TextView price;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            date = itemView.findViewById(R.id.row_date_textview);
            storeName = itemView.findViewById(R.id.row_store_name_textview);
            productName = itemView.findViewById(R.id.row_product_name_textview);
            productType = itemView.findViewById(R.id.row_product_type_textview);
            price = itemView.findViewById(R.id.row_price_textview);
        }
    }

}
