package com.example.payoapp.Activities;

import android.app.Activity;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.example.payoapp.Database.DatabaseHelper;
import com.example.payoapp.Models.UserDetails;
import com.example.payoapp.R;
import com.example.payoapp.databinding.ActivityProfileBinding;

public class ProfileActivity extends Activity {
    private ActivityProfileBinding binding;
    private DatabaseHelper databaseHelper;
    private UserDetails user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        databaseHelper = new DatabaseHelper(this);
        getIntentData();

    }

    private void getIntentData() {
        if (getIntent() != null) {
            user = (UserDetails) getIntent().getSerializableExtra("data");
            binding.mobileno.setText(user.getMobile());
            binding.name.setText(user.getFirstName());
            binding.lastname.setText(user.getLastName());
            binding.address.setText(user.getAddress());
            binding.signEmail.setText(user.getEmail());

        }

    }


}
