package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Welcome extends AppCompatActivity
{
    TextView welcomeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Creating an intent to get the user's username
        Intent intent = getIntent();
        String name = intent.getStringExtra("userName");

        // Printing the user's username in the welcome page
        welcomeUser = findViewById(R.id.welcomeUser);
        welcomeUser.setText(name);
    }
}
