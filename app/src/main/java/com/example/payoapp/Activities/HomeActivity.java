package com.example.payoapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.payoapp.Adapters.RcAdapter;
import com.example.payoapp.Models.ApiResponse;
import com.example.payoapp.Models.DataItem;
import com.example.payoapp.Models.UserDetails;
import com.example.payoapp.R;
import com.example.payoapp.Retrofit.ApiClient;
import com.example.payoapp.Retrofit.GetDataService;
import com.example.payoapp.databinding.ActivityHomeBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    ProgressDialog progressDialog;
    RcAdapter adapter;
    private UserDetails user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setListener();
        getData();
        getIntentData();
    }

    private void setListener() {
        binding.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.main.openDrawer(Gravity.START);
            }
        });
        binding.myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(HomeActivity.this, ProfileActivity.class);
                i1.putExtra("data", user);
                startActivity(i1);
            }
        });
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(i1);
                finishAffinity();
            }
        });

    }

    private void getIntentData() {
        if (getIntent() != null) {
            user = (UserDetails) getIntent().getSerializableExtra("data");
            binding.tvName.setText(user.getFirstName() + " " + user.getLastName());


        }

    }


    private void getData() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading.....");
        progressDialog.show();
        GetDataService service = ApiClient.getRetrofitInstance().create(GetDataService.class);
        Call<ApiResponse> call = service.getDataList("1");
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                progressDialog.dismiss();
                generateDataList(response.body().getData());

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(HomeActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(ArrayList<DataItem> dataList) {
        adapter = new RcAdapter(this, dataList);
        binding.rcData.setAdapter(adapter);

    }
}
