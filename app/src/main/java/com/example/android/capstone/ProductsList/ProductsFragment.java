package com.example.android.capstone.ProductsList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.capstone.Adapters.ProductsAdapter;
import com.example.android.capstone.R;


public class ProductsFragment extends Fragment {
    String product_type;

    public ProductsFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInsance) {
        ProductsActivity productsActivity = (ProductsActivity) getActivity();

        final View rootView = inflater.inflate(R.layout.fragment_products, container, false);
        initRecyclerView(productsActivity, rootView);

        //TODO viewdivder
        return rootView;
    }

    private void initRecyclerView(ProductsActivity productsActivity, View rootView){
        RecyclerView recyclerView=rootView.findViewById(R.id.rv_Products_List);

        ProductsAdapter productsAdapter=new ProductsAdapter((productsActivity.Categories).get(productsActivity.mProductType),productsActivity,productsActivity.mProductType);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(productsAdapter);
        recyclerView.setHasFixedSize(false);
    }


        public interface OnProductClickListener {

        void onProductSelected(String ProductIndex);
    }
   }
