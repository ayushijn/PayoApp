package com.example.payoapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.payoapp.Database.DatabaseHelper;
import com.example.payoapp.Models.UserDetails;
import com.example.payoapp.R;
import com.example.payoapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    String email, password;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        databaseHelper = new DatabaseHelper(this);
        setListener();
    }

    private void setListener() {

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = binding.email.getText().toString();
                password = binding.password.getText().toString();
                if (email.isEmpty()) {
                    binding.email.requestFocus();
                    binding.email.setError("Please Enter Email Id");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.email.requestFocus();
                    binding.email.setError("Please Enter Valid Email Id");
                } else if (password.isEmpty()) {
                    binding.password.requestFocus();
                    binding.password.setError("Please Enter Password");
                } else {
                    if (databaseHelper.checkUser(email, password)) {
                        getAllData();

                    } else {
                        // Snack Bar to show success message that record is wrong
                        Toast.makeText(LoginActivity.this, "User does'nt exists", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);

            }
        });
    }

    private void getAllData() {
        finish();
        UserDetails user = databaseHelper.getUser(email);
        Intent accountsIntent = new Intent(LoginActivity.this, HomeActivity.class);
        accountsIntent.putExtra("data", user);
        startActivity(accountsIntent);

    }
}
