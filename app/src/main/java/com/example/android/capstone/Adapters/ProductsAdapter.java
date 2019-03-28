package com.example.android.capstone.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.capstone.Model.Product;
import com.example.android.capstone.ProductsList.ProductsFragment;
import com.example.android.capstone.R;
import com.example.android.capstone.UI.FavoritesListActivity;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private Context context;
    private static ProductsFragment.OnProductClickListener onProductClickListener;
    //private static FavoritesListActivity.OnFavoriteClickListener onFavoriteClickListener;

    private String mProductType;
    private List<Product> PRODUCTS;

    public ProductsAdapter(List<Product>PRODUCTS, ProductsFragment.OnProductClickListener onProductClickListener, String mProductType) {

        this.PRODUCTS=PRODUCTS;
        ProductsAdapter.onProductClickListener=onProductClickListener;
        this.mProductType=mProductType;
    }
    public ProductsAdapter(List<Product>PRODUCTS){
        this.PRODUCTS=PRODUCTS;
       // this.onFavoriteClickListener=onFavoriteClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final boolean shouldAttachToParentImmediately = false;

        View view = layoutInflater
                .inflate(R.layout.product_item, viewGroup, shouldAttachToParentImmediately);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


       String ProductTitle =PRODUCTS.get(i).getName();

        viewHolder.ProductTitle.setText(ProductTitle);

    }
    @Override
    public int getItemCount() {
        if(PRODUCTS==null){
            return 0;
        }
        return PRODUCTS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView ProductTitle;

        ViewHolder(View itemView) {
            super(itemView);
            ProductTitle=itemView.findViewById(R.id.tv_product);
            ProductTitle.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {

            String adapterPosition= String.valueOf(getAdapterPosition());
            onProductClickListener.onProductSelected(adapterPosition);
           // onFavoriteClickListener.onFavoriteSelected(adapterPosition);

        }



    }

}
