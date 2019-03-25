package com.example.android.capstone.ProductsList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.capstone.Model.Product;
import com.example.android.capstone.ProductDetails.ProductDetailsActivity;
import com.example.android.capstone.R;

import java.util.HashMap;
import java.util.List;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class ProductsActivity extends AppCompatActivity implements ProductsFragment.OnProductClickListener{

    public static String mProductType;
    public String mProductIndex;
    public static HashMap<String, List<Product>> Categories=new HashMap<>();
    public static Product selectedProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState !=null){
            mProductIndex=savedInstanceState.getString(getString(R.string.product_index_tag));
        }
        Intent intent=getIntent();
        if(intent.hasExtra(getString(R.string.Product_type))){
            mProductType= (String) intent.getSerializableExtra(getString(R.string.Product_type));
            Categories= (HashMap<String, List<Product>>) intent.getSerializableExtra(getString(R.string.Categories));
            Log.d("lalala", String.valueOf(mProductType));

        }
        //TODO WIDGET
        setContentView(R.layout.activity_products);
        FragmentManager fragmentManager=getSupportFragmentManager();

        if(savedInstanceState==null){
            ProductsFragment fragment= new ProductsFragment();
            if(fragment !=null){
                fragment.product_type=mProductType;
                fragmentManager.beginTransaction()
                        .replace(R.id.Products_List_Fragment,fragment)
                        .commit();
            }

            }
            setTitle(mProductType);

       /* FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        */
        }

    @Override
    public void onProductSelected(String ProductIndex) {

        this.mProductIndex=ProductIndex;

        Intent intent = new Intent(this, ProductDetailsActivity.class);
        selectedProduct=Categories.get(mProductType).get(Integer.parseInt(mProductIndex));

        intent.putExtra(getString(R.string.selected_product),selectedProduct);

        startActivity(intent);



    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putString(getString(R.string.Product_details_tag), mProductIndex);

    }


}
