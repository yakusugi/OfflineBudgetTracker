package com.example.offlinebudgettracker;

import androidx.appcompat.app.AppCompatActivity;
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
    private Button saveIntoButton;

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
        saveIntoButton = findViewById(R.id.save_button);

        budgetTrackerViewModel = new ViewModelProvider.AndroidViewModelFactory(NewBudgetEntry.this
                .getApplication())
                .create(BudgetTrackerViewModel.class);

        saveIntoButton.setOnClickListener(v -> {
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
    }
}