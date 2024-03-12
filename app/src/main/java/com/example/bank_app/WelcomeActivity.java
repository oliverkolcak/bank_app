package com.example.bank_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        // Get the username from the intent
        String username = getIntent().getStringExtra("username");

        // Find the TextView and set the username
        TextView welcomeText = findViewById(R.id.welcome_text);
        welcomeText.setText("Welcome, " + username);

        // Set the account balance
        TextView view = findViewById(R.id.textView3);
        view.setText("$100.00");

        // Find the Pay button by its ID
        Button payButton = findViewById(R.id.id_pay);

        // Set an OnClickListener for the Pay button
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the PayActivity
                Intent intent = new Intent(WelcomeActivity.this, PayActivity.class);
                startActivity(intent);
            }
        });
    }
}