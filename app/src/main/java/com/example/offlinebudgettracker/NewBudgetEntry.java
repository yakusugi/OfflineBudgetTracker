package com.example.offlinebudgettracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.offlinebudgettracker.model.BudgetTrackerDto;
import com.example.offlinebudgettracker.model.BudgetTrackerViewModel;

public class NewBudgetEntry extends AppCompatActivity {

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

            if (!TextUtils.isEmpty(enterDate.getText())
                && !TextUtils.isEmpty(enterStoreName.getText())
                && !TextUtils.isEmpty(enterProductName.getText())
                && !TextUtils.isEmpty(enterProductType.getText())
                && !TextUtils.isEmpty(enterPrice.getText())) {
                    BudgetTrackerDto budgetTrackerDto = new BudgetTrackerDto(
                            enterDate.getText().toString(),
                            enterStoreName.getText().toString(),
                            enterProductName.getText().toString(),
                            enterProductType.getText().toString(),
                            Integer.parseInt(enterPrice.getText().toString()));
                    BudgetTrackerViewModel.insert(budgetTrackerDto);
            } else {
                Toast.makeText(this, R.string.empty, Toast.LENGTH_SHORT).show();
            }

        });
    }
}