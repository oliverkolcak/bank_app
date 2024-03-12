package com.example.bank_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PayActivity extends AppCompatActivity {

    private EditText accountNumberEditText;
    private EditText amountEditText;
    private Button sendMoneyButton;
    private BalanceManager balanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        // Initialize BalanceManager
        balanceManager = new BalanceManager(this);

        accountNumberEditText = findViewById(R.id.account_number);
        amountEditText = findViewById(R.id.amount);
        sendMoneyButton = findViewById(R.id.send_money_button);

        sendMoneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String accountNumber = accountNumberEditText.getText().toString();
                    double amountToSend = Double.parseDouble(amountEditText.getText().toString());
                    double userBalance = balanceManager.getBalance(); // Get the current balance

                    // Check if the user has enough money in the account
                    if (userBalance >= amountToSend) {
                        // Deduct the amount from the user's balance
                        balanceManager.setBalance(userBalance - amountToSend); // Save the new balance

                        // For demonstration purposes only: show the remaining balance
                        Toast.makeText(PayActivity.this, "Payment successful! Remaining balance: $" + balanceManager.getBalance(), Toast.LENGTH_LONG).show();

                        // Set the result as OK and finish the activity to return to WelcomeActivity
                        setResult(RESULT_OK);
                        finish();

                    } else {
                        Toast.makeText(PayActivity.this, "Insufficient funds.", Toast.LENGTH_LONG).show();
                    }
                } catch (NumberFormatException e) {
                    // Handle the case where the user does not enter a valid number
                    Toast.makeText(PayActivity.this, "Please enter a valid number.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}