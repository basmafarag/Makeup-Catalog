package com.example.android.capstone.Retrofit;
import com.example.android.capstone.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/api/v1/products.json?brand=nyx")
    Call<List<Product>>getproducts();


    //@GET("/api/v1/products.json?brand={brand_name}")
    //Call<Product> getBrand(@Path("brand_name") String brand_name);


}

