package com.example.android.capstone.ProductDetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;
import android.content.Intent;
import com.example.android.capstone.Model.Product;
import com.example.android.capstone.R;
import android.view.View.OnClickListener;
import java.io.Serializable;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
//import com.squareup.picasso.Picasso;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;

public class ProductDetailsFragment extends Fragment {

    public static Product product;
    TextView productName;
    TextView productPrice;
    TextView productDescription;
    ImageView productImage;
    Button buyNow;
    Button favorites;
    public ProductDetailsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView=inflater.inflate(R.layout.fragment_product_details,container,false);
        product=(Product)getArguments().getParcelable("selected_product");

        productName=rootView.findViewById(R.id.tv_product_title);
        productPrice=rootView.findViewById(R.id.tv_price);
        productDescription=rootView.findViewById(R.id.tv_product_descripition);
        productImage=rootView.findViewById(R.id.iv_product_image);
        buyNow=rootView.findViewById(R.id.bv_buy_button);
        favorites=rootView.findViewById(R.id.bv_favourite_button);


        if(savedInstanceState!=null){
            product=savedInstanceState.getParcelable("selected_product");
        }
        if(product!=null) {
            productName.setText(product.getName());

            if ((product.getPrice_sign() != null)&& (product.getPrice() !=null) ) {
                productPrice.setText(product.getPrice()+ " "+product.getPrice_sign() );
            }else if(product.getPrice()!=null){

                productPrice.setText((product.getPrice()).toString());

            }
        if(product.getDescripition()!=null){
            productDescription.setText(product.getDescripition());
        }
        if(product.getImage_link()!=null){
            Picasso.get().load(product.getImage_link()).into(productImage);
        }

        }
        if(product.getProduct_link()!=null) {
            buyNow.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse(product.getProduct_link()));
                    startActivity(viewIntent);
                }
            });
        }

        //CurrentUser=intent.ge
        favorites.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                    Log.d("notadded ", String.valueOf(product.getName()));
                }else {
                    Firebase rootRef = new Firebase("https://makeup-catalog-76a8c.firebaseio.com/");
                    Firebase userRef = rootRef.child("users/" + rootRef.getAuth().getUid());
                    userRef.child("Product").setValue(product);
                    Log.d("added ", String.valueOf(product.getName()));
                }
            }
        });
        return rootView;

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("selected_product", product);


    }


}