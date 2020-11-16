package com.example.payoapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payoapp.Models.DataItem;
import com.example.payoapp.R;
import com.example.payoapp.databinding.MyRowBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RcAdapter extends RecyclerView.Adapter<RcAdapter.CustomViewHolder> {

    private ArrayList<DataItem> dataList;
    private Context context;

    public RcAdapter(Context context, ArrayList<DataItem> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        MyRowBinding binding;

        CustomViewHolder(MyRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MyRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.my_row, parent, false);
        return new CustomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.binding.title.setText(dataList.get(position).getFirstName() + " " + dataList.get(position).getLastName());
        holder.binding.description.setText(dataList.get(position).getEmail());
        Picasso.with(context).load(dataList.get(position).getAvatar()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.binding.image);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
