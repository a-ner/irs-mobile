package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    EditText txtUsername, txtPassword, txtPasswordConfirmation,txtEmail, txtPhoneNumber;
    Button btnRegisterAccount;
    Map<String, String> preRegs = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtPasswordConfirmation = findViewById(R.id.txtPasswordConfirmation);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhoneNumber = findViewById(R.id.txtPhoneNumber);

        btnRegisterAccount = findViewById(R.id.btnRegisterAccount);

        //Predefining accounts
        preRegs.put("henry", "123456");
        preRegs.put("aner", "123456");
        preRegs.put("admin", "123456");

        btnRegisterAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username, password, password_confirmation, email, phonenumber;

                username = txtUsername.getText().toString();
                password = txtPassword.getText().toString();
                password_confirmation = txtPasswordConfirmation.getText().toString();
                email = txtEmail.getText().toString();
                phonenumber = txtPhoneNumber.getText().toString();

                // BLANK USERNAME CHECK
                if(username.equals(""))
                {
                    Toast.makeText(Registration.this, "Username Required", Toast.LENGTH_SHORT).show();
                }
                // EXISTING USERNAME CHECK
                else if (preRegs.containsKey(username))
                {
                    Toast.makeText(Registration.this, "Username has been taken", Toast.LENGTH_SHORT).show();
                }
                // BLANK PASSWORD CHECK
                else if (password.equals(""))
                {
                    Toast.makeText(Registration.this, "Password Required", Toast.LENGTH_SHORT).show();
                }
                // BLANK PASSWORD CONFIRMATION CHECK
                else if (password_confirmation.equals(""))
                {
                    Toast.makeText(Registration.this, "Password Confirmation Required", Toast.LENGTH_SHORT).show();
                }
                // PASSWORD MATCH CHECK
                else if (!password_confirmation.equals(password))
                {
                    Toast.makeText(Registration.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                }
                // BLANK EMAIL CHECK
                else if (email.equals(""))
                {
                    Toast.makeText(Registration.this, "Email Required", Toast.LENGTH_SHORT).show();
                }
                // EMAIL VALIDATION THROUGH PATTERNS
                else if (validEmail(email) == false){
                    Toast.makeText(Registration.this, "Incorrect email format!", Toast.LENGTH_SHORT).show();
                }
                // BLANK PHONE NUMBER CHECK
                else if (phonenumber.equals(""))
                {
                    Toast.makeText(Registration.this, "Phone Number Required", Toast.LENGTH_SHORT).show();
                }
                // PHONE VALIDATION THROUGH PATTERNS
                else if (validNumber(phonenumber) == false){
                    Toast.makeText(Registration.this, "Incorrect phone number format!", Toast.LENGTH_SHORT).show();
                }
                // AUTHENTICATION
                else
                {
                    // Retrieves the username and password input and sends them to Main activity, and finishes this activity
                    Intent regReturn = new Intent(Registration.this, MainActivity.class);
                    regReturn.putExtra("userName", username);
                    regReturn.putExtra("password", password);
                    startActivity(regReturn);
                    finish();
                }
            }
        });
    }

    //Regex, using the Pattern library to see whether the input is a valid email or not
    public boolean validEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    //Regex, using the Pattern library to see whether the input is a valid number or not, valid when a minimum of 3 numbers has been inputted
    public boolean validNumber(String number){
        return Patterns.PHONE.matcher(number).matches();
    }
}
