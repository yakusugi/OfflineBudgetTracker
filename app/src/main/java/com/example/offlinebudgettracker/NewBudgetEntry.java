package com.example.offlinebudgettracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.offlinebudgettracker.model.BudgetTrackerDto;
import com.example.offlinebudgettracker.model.BudgetTrackerViewModel;
import com.google.android.material.snackbar.Snackbar;

public class NewBudgetEntry extends AppCompatActivity {

    public static final String DATE_REPLY = "date_reply";
    public static final String STORE_NAME_REPLY = "store_name_reply";
    public static final String PRODUCT_NAME_REPLY = "product_name_reply";
    public static final String PRODUCT_TYPE_REPLY = "product_type_reply";
    public static final String PRICE_REPLY = "price_reply";
    private EditText enterDate;
    private EditText enterStoreName;
    private EditText enterProductName;
    private EditText enterProductType;
    private EditText enterPrice;
    private Button saveInfoButton;
    private int budgetItemId = 0;
    private Boolean isEdit = false;
    private Button updateButton;
    private Button deleteButton;

    private BudgetTrackerViewModel budgetTrackerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget_entry);
        enterDate = findViewById(R.id.enter_date);
        enterStoreName = findViewById(R.id.enter_store_name);
        enterProductName = findViewById(R.id.enter_product_name);
        enterProductType = findViewById(R.id.enter_product_type);
        enterPrice = findViewById(R.id.enter_price);
        saveInfoButton = findViewById(R.id.save_button);

        budgetTrackerViewModel = new ViewModelProvider.AndroidViewModelFactory(NewBudgetEntry.this
                .getApplication())
                .create(BudgetTrackerViewModel.class);


        if (getIntent().hasExtra(MainActivity.BUDGET_TRACKER_ID)) {
            budgetItemId = getIntent().getIntExtra(MainActivity.BUDGET_TRACKER_ID, 0);
            budgetTrackerViewModel.get(budgetItemId).observe(this, budgetTrackerDto -> {
                if (budgetTrackerDto != null) {
                    enterDate.setText(budgetTrackerDto.getDate());
                    enterStoreName.setText(budgetTrackerDto.getStoreName());
                    enterProductName.setText(budgetTrackerDto.getProductName());
                    enterProductType.setText(budgetTrackerDto.getProductType());
                    enterPrice.setText(String.valueOf(budgetTrackerDto.getPrice()));
                }
            });
            isEdit = true;
        }

        saveInfoButton.setOnClickListener(v -> {
            Intent replyIntent = new Intent();

            if (!TextUtils.isEmpty(enterDate.getText())
                && !TextUtils.isEmpty(enterStoreName.getText())
                && !TextUtils.isEmpty(enterProductName.getText())
                && !TextUtils.isEmpty(enterProductType.getText())
                && !TextUtils.isEmpty(enterPrice.getText())) {
                String date = enterDate.getText().toString();
                String storeName = enterStoreName.getText().toString();
                String productName = enterProductName.getText().toString();
                String productType = enterProductType.getText().toString();
                int price = Integer.parseInt(enterPrice.getText().toString());

                replyIntent.putExtra(DATE_REPLY, date);
                replyIntent.putExtra(STORE_NAME_REPLY, storeName);
                replyIntent.putExtra(PRODUCT_NAME_REPLY, productName);
                replyIntent.putExtra(PRODUCT_TYPE_REPLY, productType);
                replyIntent.putExtra(PRICE_REPLY, String.valueOf(price));
                setResult(RESULT_OK, replyIntent);
            } else {
                setResult(RESULT_CANCELED, replyIntent);
            }
            finish();

        });

        //Delete button
        deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(v -> {
            String date = enterDate.getText().toString().trim();
            String storeName = enterStoreName.getText().toString().trim();
            String productName = enterProductName.getText().toString().trim();
            String productType = enterProductType.getText().toString().trim();
            int price = Integer.parseInt(enterPrice.getText().toString().trim());

            if (TextUtils.isEmpty(date) ||
                    TextUtils.isEmpty(storeName) ||
                    TextUtils.isEmpty(productName) ||
                    TextUtils.isEmpty(productType) ||
                    TextUtils.isEmpty(String.valueOf(price))) {
                Snackbar.make(enterProductName, R.string.empty, Snackbar.LENGTH_SHORT).show();
            } else {
                BudgetTrackerDto budgetTrackerDto = new BudgetTrackerDto();
                budgetTrackerDto.setId(budgetItemId);
                budgetTrackerDto.setDate(date);
                budgetTrackerDto.setStoreName(storeName);
                budgetTrackerDto.setProductName(productName);
                budgetTrackerDto.setProductType(productType);
                budgetTrackerDto.setPrice(price);
                BudgetTrackerViewModel.delete(budgetTrackerDto);
                finish();
            }

        });

        //Update button
        updateButton = findViewById(R.id.update_button);
        updateButton.setOnClickListener(v -> {

            String date = enterDate.getText().toString().trim();
            String storeName = enterStoreName.getText().toString().trim();
            String productName = enterProductName.getText().toString().trim();
            String productType = enterProductType.getText().toString().trim();
            int price = Integer.parseInt(enterPrice.getText().toString().trim());

            if (TextUtils.isEmpty(date) ||
                    TextUtils.isEmpty(storeName) ||
                    TextUtils.isEmpty(productName) ||
                    TextUtils.isEmpty(productType) ||
                    TextUtils.isEmpty(String.valueOf(price))) {
                Snackbar.make(enterProductName, R.string.empty, Snackbar.LENGTH_SHORT).show();
            } else {
                BudgetTrackerDto budgetTrackerDto = new BudgetTrackerDto();
                budgetTrackerDto.setId(budgetItemId);
                budgetTrackerDto.setDate(date);
                budgetTrackerDto.setStoreName(storeName);
                budgetTrackerDto.setProductName(productName);
                budgetTrackerDto.setProductType(productType);
                budgetTrackerDto.setPrice(price);
                BudgetTrackerViewModel.update(budgetTrackerDto);
                finish();
            }
        });

        if (isEdit) {
            saveInfoButton.setVisibility(View.GONE);
        } else {
            updateButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
        }


    }
}