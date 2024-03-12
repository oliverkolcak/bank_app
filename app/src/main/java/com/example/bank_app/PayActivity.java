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

    // Assuming a fixed balance for demonstration purposes.
    // In a real application, you would store and retrieve this information securely.
    private double userBalance = 1000.00; // User's balance in the bank account

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        accountNumberEditText = findViewById(R.id.account_number);
        amountEditText = findViewById(R.id.amount);
        sendMoneyButton = findViewById(R.id.send_money_button);

        sendMoneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String accountNumber = accountNumberEditText.getText().toString();
                    double amountToSend = Double.parseDouble(amountEditText.getText().toString());

                    // Check if the user has enough money in the account
                    if (userBalance >= amountToSend) {
                        // Deduct the amount from the user's balance
                        userBalance -= amountToSend;

                        // For demonstration purposes only: show the remaining balance
                        Toast.makeText(PayActivity.this, "Payment successful! Remaining balance: $" + userBalance, Toast.LENGTH_LONG).show();

                        // TODO: Here you would implement the actual transaction logic to send the money
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
