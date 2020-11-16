package com.example.payoapp.Models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ApiResponse{

	@SerializedName("per_page")
	private int perPage;

	@SerializedName("total")
	private int total;

	@SerializedName("ad")
	private Ad ad;

	@SerializedName("data")
	private ArrayList<DataItem> data;

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	public int getPerPage(){
		return perPage;
	}

	public int getTotal(){
		return total;
	}

	public Ad getAd(){
		return ad;
	}

	public ArrayList<DataItem> getData(){
		return data;
	}

	public int getPage(){
		return page;
	}

	public int getTotalPages(){
		return totalPages;
	}
}