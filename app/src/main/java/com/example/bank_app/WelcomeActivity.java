package com.example.bank_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private BalanceManager balanceManager;
    private TextView balanceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        // Initialize BalanceManager
        balanceManager = new BalanceManager(this);

        // Get the username from the intent
        String username = getIntent().getStringExtra("username");

        // Find the TextViews and set the username and balance
        TextView welcomeText = findViewById(R.id.welcome_text);
        welcomeText.setText("Welcome, " + username);

        balanceView = findViewById(R.id.textView3);
        updateBalanceDisplay();

        // Find the Pay button by its ID
        Button payButton = findViewById(R.id.id_pay);

        // Set an OnClickListener for the Pay button
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass the current balance to the PayActivity
                Intent intent = new Intent(WelcomeActivity.this, PayActivity.class);
                intent.putExtra("balance", balanceManager.getBalance());
                startActivityForResult(intent, 1); // Start PayActivity with requestCode 1
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // When PayActivity finishes, update the balance display
        if (requestCode == 1) {
            updateBalanceDisplay();
        }
    }

    private void updateBalanceDisplay() {
        // Format the balance to two decimal places and update the TextView
        String formattedBalance = String.format("$%.2f", balanceManager.getBalance());
        balanceView.setText(formattedBalance);
    }
}