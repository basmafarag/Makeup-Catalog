package com.example.android.capstone.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.capstone.Adapters.ProductsAdapter;
import com.example.android.capstone.FavoritesWidget.FavoritesWidget;
import com.example.android.capstone.Model.Product;
import com.example.android.capstone.ProductDetails.ProductDetailsActivity;
import com.example.android.capstone.ProductsList.ProductsActivity;
import com.example.android.capstone.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FavoritesListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    public String mProductIndex;


    public FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "ViewDatabase";
    public final static List<Product> products = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_list);
        //FavoritesListActivity f = (ProductsActivity) getActivity();

        recyclerView=findViewById(R.id.rv_Favorite_List);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        mAuth=FirebaseAuth.getInstance();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        fetch();
        sendRecipeToWidget();


    }

    private void fetch() {
        Log.e("FavoritesListActivity", "uid " + mAuth.getCurrentUser().getUid());

        final ProductsAdapter productsAdapter = new ProductsAdapter(products);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FavoritesListActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(productsAdapter);
        recyclerView.setHasFixedSize(false);

        FirebaseDatabase.getInstance()
                .getReference()
                .child("Users").child(mAuth.getCurrentUser().getUid()).child("Products").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Product product = dataSnapshot.getValue(Product.class);

                Log.e("FavoritesListActivity", "listener  " +
                        product.getDescripition());
                products.add(product);

                productsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {


            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    /*@Override
    public void onFavoriteSelected(String ProductIndex) {
        this.mProductIndex=ProductIndex;

        Intent intent = new Intent(this, ProductDetailsActivity.class);
        selectedProduct=Categories.get(mProductType).get(Integer.parseInt(mProductIndex));

        intent.putExtra(getString(R.string.selected_product),selectedProduct);

        startActivity(intent);

    }

    public interface OnFavoriteClickListener {

        void onFavoriteSelected(String ProductIndex);
    }*/

   private void sendRecipeToWidget() {
        Intent intent = new Intent(this, FavoritesWidget.class);
       Log.d("widgettttFavorite", String.valueOf(products));
       intent.putExtra("myFavorites", (Serializable) products);
        intent.setAction(getString(R.string.widget_intent_action));
        sendBroadcast(intent);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

}

