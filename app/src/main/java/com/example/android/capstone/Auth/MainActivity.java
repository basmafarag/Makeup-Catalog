package com.example.android.capstone.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.capstone.Adapters.CategoriesAdapter;
import com.example.android.capstone.Model.Product;
import com.example.android.capstone.ProductsList.ProductsActivity;
import com.example.android.capstone.R;
import com.example.android.capstone.Retrofit.ApiService;
import com.example.android.capstone.Retrofit.RetroClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, CategoriesAdapter.CategoriesOnClickHandler {

    authActivity authActivity=new authActivity();
    private List<Product> products;
    HashMap<String, List<Product>> Categories=new HashMap<>();
    private SwipeRefreshLayout swipeContainer;

    RecyclerView mRecyclerView ;
    CategoriesAdapter mCategoryAdapter;
    ProgressBar mProgressBar;
    private RecyclerView.LayoutManager mLayoutManager;


    private  List<Product> Product_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView= findViewById(R.id.category_list);
        mProgressBar= findViewById(R.id.pb_loading_indicator);


        //----------------initRecyclerView-----------------------
        mCategoryAdapter = new CategoriesAdapter(this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mCategoryAdapter);
        mRecyclerView.setHasFixedSize(true);
        //----------------initRecyclerView-----------------------


        if (savedInstanceState == null || !savedInstanceState.containsKey(getString(R.string.Categories_tag))) {
            mProgressBar.setVisibility(View.VISIBLE);
            getproducts();
        }else{
            Categories = (HashMap<String, List<Product>>) savedInstanceState.getSerializable(getString(R.string.Categories_tag));
        }

        //}
        findViewById(R.id.signOutButton).setOnClickListener(this);


    }

    private void initSwipeContainerRefreshListener() {
        swipeContainer=findViewById(R.id.swipe_refresh_layout);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getproducts();
            }
        });
    }

    private void getproducts() {

        ApiService apiService = RetroClient.getApiService();
        Call<List<Product>> call = apiService.getproducts();
        call.enqueue(new Callback<List<Product>>() {

            @Override

            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {

                products = response.body();


                for(int i=0;i<products.size();i++){
                    if(Categories.get(products.get(i).getProduct_type())==null){
                        List<Product> producsList=new ArrayList<>();
                        producsList.add(products.get(i));
                        Categories.put(products.get(i).getProduct_type(),producsList);
                    }else{

                        Categories.get(products.get(i).getProduct_type()).add(products.get(i));
                    }

                }

                List<String> Categories_names = new ArrayList<String>();
                       Categories_names.addAll(Categories.keySet());


                CategoriesAdapter.Categories_names= Categories_names;
               // Log.d("recipeeee", String.valueOf(Categories.get("eyeshadow")));

               // ProductsAdapter.Products=Categories;

                updateCategoryViewsOnSuccess();
                updateProductsTypeViewsOnSuccess();
              }

            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
               // Log.d("recipeeee", t.getLocalizedMessage());

                Log.e(this.getClass().getSimpleName(), t.toString());
                //updateCategoriesViewsOnFailure();
            }
        });
    }



    public void onClick(View v) {
        int i = v.getId();
       if (i == R.id.signOutButton) {

           FirebaseAuth.getInstance().signOut();
           Intent intent=new Intent(MainActivity.this,authActivity.getClass());
           startActivity(intent);
           finish();

       }
    }

    private void updateCategoryViewsOnSuccess() {

        mCategoryAdapter.notifyDataSetChanged();
        //mProgressBar.setVisibility(View.INVISIBLE);
      // swipeContainer.setRefreshing(false);


    }

    private void updateProductsTypeViewsOnSuccess() {

        mCategoryAdapter.notifyDataSetChanged();
        //mProgressBar.setVisibility(View.INVISIBLE);
        // swipeContainer.setRefreshing(false);


    }

    private void updateCategoriesViewsOnFailure() {

        mProgressBar.setVisibility(View.INVISIBLE);
        //swipeContainer.setRefreshing(false);


    }


    @Override
    public void onClickM(String product) {
        Log.d("ProductType", product);
        Intent intent = new Intent(MainActivity.this, ProductsActivity.class);
        intent.putExtra(getString(R.string.Product_type),product);
        intent.putExtra(getString(R.string.Categories),Categories);

        startActivity(intent);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(Categories !=null){
            outState.putSerializable(getString(R.string.Categories_tag),  Categories);
        }
        super.onSaveInstanceState(outState);
    }


}
