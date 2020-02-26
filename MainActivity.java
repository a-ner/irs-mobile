package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText txtUsername, txtPassword;
    TextView txtvRegister;
    Button btnLogin;
    Map<String, String> login = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

        btnLogin = findViewById(R.id.btnLogin);
        txtvRegister = findViewById(R.id.txtvRegister);

        //Predefining user accounts
        login.put("aner", "123456");
        login.put("admin", "123456");

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username, password;

                username = txtUsername.getText().toString();
                password = txtPassword.getText().toString();

                //This intent will retrieve the username and password from registration, and add it into the Hash map
                Intent intent = getIntent();
                login.put(intent.getStringExtra("userName"), intent.getStringExtra("password"));

                // BLANK USERNAME CHECK
                if(username.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Username Required", Toast.LENGTH_SHORT).show();
                }
                // BLANK PASSWORD CHECK
                else if (password.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Password Required", Toast.LENGTH_SHORT).show();
                }

                //If both username and password does not match any key=pair value in the Hash map
                else if(!login.containsKey(username) && !login.containsValue(password)){
                    Toast.makeText(MainActivity.this, "Incorrect Login", Toast.LENGTH_SHORT).show();
                    txtPassword.getText().clear();
                }

                //If both username and password does match a key-pair value in the Hash map
                else if(login.containsKey(username) && login.containsValue(password)){
                    // Auth - sends the username to the Welcome page and starts the activity
                    Intent toWelcome = new Intent(MainActivity.this, Welcome.class);
                    toWelcome.putExtra("userName", username);
                    Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    startActivity(toWelcome);
                }

                //If correct username, but incorrect password
                else if(login.containsKey(username) && !login.containsValue(password)){
                    Toast.makeText(MainActivity.this, "Incorrect password!", Toast.LENGTH_SHORT).show();
                }

                //Else for any unexpected errors
                else
                {
                    Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // Intent for Registration TextView
        txtvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, Registration.class);
                startActivity(i);
            }
        });

    }
}
