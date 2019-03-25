package com.example.android.capstone.ProductDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.capstone.Model.Product;
import com.example.android.capstone.R;
import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

public class ProductDetailsActivity extends AppCompatActivity{
   public static Product mSelectedProduct;
    FirebaseAuth CurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);

        //TODO action bar isibiltiy
        Intent intent=getIntent();

        if(intent.hasExtra(getString(R.string.selected_product))){
            mSelectedProduct=intent.getParcelableExtra("selected_product");
            Log.d(" Intent", String.valueOf(intent.getData()));

        }else{
            Log.d(" SelectedProduct1", String.valueOf(mSelectedProduct.getName()));

        }
        //CurrentUser=intent.getParcelableExtra("Current_User");

        if(!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }

        ProductDetailsFragment productDetailsFragment=new ProductDetailsFragment();
        Bundle args=new Bundle();
        args.putParcelable("selected_product", mSelectedProduct);
        productDetailsFragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.product__fragment, productDetailsFragment)
                .commit();


        setContentView(R.layout.activity_product_details);
        //.setOnClickListener(new View.OnClickListener() {

    }

/* FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        */


    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(getString(R.string.selected_product), mSelectedProduct);
    }


}
