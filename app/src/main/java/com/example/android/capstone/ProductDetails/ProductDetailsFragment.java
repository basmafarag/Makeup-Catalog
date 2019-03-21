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

import com.example.android.capstone.Model.Product;
import com.example.android.capstone.R;

import java.io.Serializable;

//import com.squareup.picasso.Picasso;

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
        product=(Product)getArguments().getSerializable("selected_product");

        productName=rootView.findViewById(R.id.tv_product_title);
        productPrice=rootView.findViewById(R.id.tv_price);
        productDescription=rootView.findViewById(R.id.tv_product_descripition);
        productImage=rootView.findViewById(R.id.iv_product_image);
        buyNow=rootView.findViewById(R.id.bv_buy_button);
        favorites=rootView.findViewById(R.id.bv_favourite_button);

        Log.d("heloo else ", String.valueOf(product));

        if(savedInstanceState!=null){
            product=(Product)savedInstanceState.getSerializable("selected_product");
        }
        if(product!=null) {
            productName.setText(product.getName());

            if ((product.getPrice_sign() != null)) {
                productPrice.setText(product.getPrice()+ " "+product.getPrice_sign() );
            }else{

                productPrice.setText((int) product.getPrice());

            }
        if(product.getDescripition()!=null){
            productDescription.setText(product.getDescripition());
        }
        if(product.getImage_link()!=null){
            //Picasso.get().load(product.getImage_link()).into(productImage);
        }

        }
        return rootView;

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("selected_product", (Serializable) product);


    }
}