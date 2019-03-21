package com.example.android.capstone.ProductDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.capstone.Model.Product;
import com.example.android.capstone.R;

public class ProductDetailsActivity extends AppCompatActivity {
   public static Product mSelectedProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(" basmaaaSlected", "bkj");

        //TODO action bar isibiltiy
        Intent intent=getIntent();

        if(intent.hasExtra(getString(R.string.selected_product))){
            mSelectedProduct=(Product)intent.getSerializableExtra("selected_product");
            Log.d(" Intent", String.valueOf(intent.getData()));

        }else{
            Log.d(" SelectedProduct1", String.valueOf(mSelectedProduct.getName()));

        }
//        Log.d(" basmaaaSlected2", String.valueOf(mSelectedProduct.getName()));

        ProductDetailsFragment productDetailsFragment=new ProductDetailsFragment();
        Bundle args=new Bundle();
        args.putString("selected_product2", String.valueOf(mSelectedProduct));
        productDetailsFragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();
        //recipeStepDetailsFragment.recipe = recipe;
        fragmentManager.beginTransaction()
                .replace(R.id.product__fragment, productDetailsFragment)
                .commit();


        setContentView(R.layout.activity_product_details);
    }


    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(getString(R.string.selected_product), String.valueOf(mSelectedProduct));
    }
}
