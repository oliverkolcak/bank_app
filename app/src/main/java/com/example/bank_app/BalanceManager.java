package com.example.bank_app;

import android.content.Context;
import android.content.SharedPreferences;

public class BalanceManager {

    private static final String PREFS_NAME = "BankAppPrefs";
    private static final String BALANCE_KEY = "balance";
    private SharedPreferences sharedPreferences;

    public BalanceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public double getBalance() {
        // Default balance if not found will be 100.00
        return Double.longBitsToDouble(sharedPreferences.getLong(BALANCE_KEY, Double.doubleToLongBits(100.00)));
    }

    public void setBalance(double balance) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(BALANCE_KEY, Double.doubleToRawLongBits(balance));
        editor.apply();
    }
}
