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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
//import com.squareup.picasso.Picasso;
import android.content.Context;
//import com.firebase.ui.auth.AuthUI.getApplicationContext;
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
    Button removeFavorites;
    String keyID;
    public FirebaseAuth mAuth;
    static HashMap<String,List<String>> favoritesList=new HashMap<>();

    public ProductDetailsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView=inflater.inflate(R.layout.fragment_product_details,container,false);
        product=(Product)getArguments().getParcelable(getString(R.string.selected_product));

        productName=rootView.findViewById(R.id.tv_product_title);
        productPrice=rootView.findViewById(R.id.tv_price);
        productDescription=rootView.findViewById(R.id.tv_product_descripition);
        productImage=rootView.findViewById(R.id.iv_product_image);
        buyNow=rootView.findViewById(R.id.bv_buy_button);
        favorites=rootView.findViewById(R.id.bv_favourite_button);
        removeFavorites=rootView.findViewById(R.id.bv_remove_favorite_button);

        mAuth =FirebaseAuth.getInstance();
        if(savedInstanceState!=null) {
            product = savedInstanceState.getParcelable(getString(R.string.selected_product));
            if (savedInstanceState.containsKey(getString(R.string.kiki))) {
                favoritesList = (HashMap<String, List<String>>) savedInstanceState.getSerializable("kiki");
            }
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

        favorites.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String User_Id = mAuth.getCurrentUser().getUid();
                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(User_Id).child("Products");

                Context context = getContext();
                int duration = Toast.LENGTH_SHORT;


                if (favoritesList.containsKey(product.getName())) {
                    CharSequence text = "Product already exists";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                } else {

                    CharSequence text="Your Product added Successfully";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                current_user_db.push().setValue(product, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError,
                                           DatabaseReference databaseReference) {
                        keyID = databaseReference.getKey();


                        if (favoritesList.get(product) == null) {
                            List<String> keysList = new ArrayList<>();
                            keysList.add(keyID);
                            favoritesList.put(product.getName(), keysList);
                        } else {

                            // favoritesList.get().add(products.get(i));

                        }

                        Log.d(" bybybybb11", String.valueOf(favoritesList));

                    }
                });
            }


            }
        });


        removeFavorites.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String User_Id=mAuth.getCurrentUser().getUid();
                DatabaseReference current_user_db=FirebaseDatabase.getInstance().getReference().child("Users").child(User_Id).child("Products");

                Context context = getContext();

                if(favoritesList.containsKey(product.getName())) {
                    CharSequence text = "Your Product removed Successfully";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    current_user_db.child(favoritesList.get(product.getName()).get(0)).removeValue();
                    favoritesList.remove(product.getName());


                }else{
                    CharSequence text = "Product is not on the list";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
        return rootView;

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(getString(R.string.selected_product), product);
        if(favoritesList!=null) {
            outState.putSerializable(getString(R.string.kiki), favoritesList);
        }
        super.onSaveInstanceState(outState);

    }


}