package com.example.payoapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.example.payoapp.Database.DatabaseHelper;
import com.example.payoapp.Models.UserDetails;
import com.example.payoapp.R;
import com.example.payoapp.databinding.ActivitySignupBinding;

public class SignUpActivity extends Activity {
    private ActivitySignupBinding binding;
    private DatabaseHelper databaseHelper;
    private UserDetails user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        databaseHelper = new DatabaseHelper(this);
        user = new UserDetails();
        setListener();
    }

    void setListener() {
        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateFields();
            }
        });

    }


    void validateFields() {
        if ((binding.name.getText().toString().isEmpty())) {
            binding.name.requestFocus();
            binding.name.setError("Enter Name");
        } else if (binding.lastname.getText().toString().isEmpty()) {
            binding.lastname.requestFocus();
            binding.lastname.setError("Enter last Name");
        } else if (binding.signEmail.getText().toString().trim().isEmpty()) {
            binding.signEmail.requestFocus();
            binding.signEmail.setError("Please Enter Email Id");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.signEmail.getText().toString().trim()).matches()) {
            binding.signEmail.requestFocus();
            binding.signEmail.setError("Please Enter Valid Email Id");
        } else if (binding.signpwd.getText().toString().isEmpty() || binding.signpwd.getText().toString().length() < 8) {
            binding.signpwd.requestFocus();
            binding.signpwd.setError("Enter valid Password with atleast one special Character");
        } else if (!(binding.signpwd.getText().toString().equals(binding.repeatPwd.getText().toString())) || binding.signpwd.getText().toString().isEmpty()) {
            binding.repeatPwd.requestFocus();
            binding.repeatPwd.setError("Password is different");
        } else if (binding.mobileno.getText().toString().length() < 10 || binding.signEmail.getText().toString().isEmpty()) {
            binding.mobileno.requestFocus();
            binding.mobileno.setError("Enter valid Mobile No");
        } else if (binding.address.getText().toString().isEmpty()) {
            binding.address.requestFocus();
            binding.address.setError("Enter Address");
        } else {
            saveData();
        }
    }

    private void saveData() {
        if (!databaseHelper.checkUser(binding.signEmail.getText().toString().trim())) {
            user = new UserDetails(binding.name.getText().toString().trim(),
                    binding.lastname.getText().toString().trim(),
                    binding.address.getText().toString().trim(),
                    binding.signEmail.getText().toString().trim(),
                    binding.repeatPwd.getText().toString().trim(),
                    binding.mobileno.getText().toString().trim());
            databaseHelper.addUser(user);

            Toast.makeText(this, "User registration successful!", Toast.LENGTH_LONG).show();
            finish();
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("data", user);
            startActivity(intent);

        } else {

            Toast.makeText(this, "Email already exists", Toast.LENGTH_LONG).show();
        }


    }


}

