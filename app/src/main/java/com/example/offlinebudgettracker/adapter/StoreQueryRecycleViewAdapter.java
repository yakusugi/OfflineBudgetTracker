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
import com.example.offlinebudgettracker.data.BudgetTrackerDao;
import com.example.offlinebudgettracker.model.BudgetTrackerDto;

import java.util.List;
import java.util.Objects;

public class StoreQueryRecycleViewAdapter extends RecyclerView.Adapter<StoreQueryRecycleViewAdapter.StoreQueryViewHolder> {
    private List<BudgetTrackerDto> storeQueryBudgetTrackerList;
    private Context context;

    public StoreQueryRecycleViewAdapter(List<BudgetTrackerDto> storeQueryBudgetTrackerList, Context context) {
        this.storeQueryBudgetTrackerList = storeQueryBudgetTrackerList;
        this.context = context;
    }

    @NonNull
    @Override
    public StoreQueryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_query_budget_tracker_row, parent, false);
        return new StoreQueryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreQueryViewHolder holder, int position) {
        BudgetTrackerDto budgetTrackerDto = Objects.requireNonNull(storeQueryBudgetTrackerList.get(position));
        holder.date.setText(budgetTrackerDto.getDate());
        holder.storeName.setText(budgetTrackerDto.getStoreName());
        holder.productName.setText(budgetTrackerDto.getProductName());
        holder.productType.setText(budgetTrackerDto.getProductType());
        holder.price.setText(budgetTrackerDto.getPrice());
    }

    @Override
    public int getItemCount() {
        return Objects.requireNonNull(storeQueryBudgetTrackerList.size());
    }

    public class StoreQueryViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView storeName;
        public TextView productName;
        public TextView productType;
        public TextView price;

        public StoreQueryViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.store_query_row_date_textview);
            storeName = itemView.findViewById(R.id.store_query_row_store_name_textview);
            productName = itemView.findViewById(R.id.store_query_row_product_name_textview);
            productType = itemView.findViewById(R.id.store_query_row_product_type_textview);
            price = itemView.findViewById(R.id.store_query_row_price_textview);
        }
    }
}
