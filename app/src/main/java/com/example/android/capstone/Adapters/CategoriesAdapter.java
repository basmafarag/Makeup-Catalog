package com.example.android.capstone.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.capstone.Model.Product;
import com.example.android.capstone.R;

import java.util.HashMap;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

        public static HashMap<String, List<Product>> Categories=new HashMap<>();
        public static List<String> Categories_names;
        private static CategoriesOnClickHandler categoriesOnClickHandler;

    public CategoriesAdapter(CategoriesOnClickHandler categoriesOnClickHandler){
        CategoriesAdapter.categoriesOnClickHandler=categoriesOnClickHandler;
    }

    @NonNull
    @Override
    public CategoriesAdapter.CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        int layoutIdForMovieItem= R.layout.category_item;
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        final boolean shouldAttachToParentImmediately= false;
        View itemView=layoutInflater.inflate(layoutIdForMovieItem, parent, shouldAttachToParentImmediately);
        return new CategoriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.CategoriesViewHolder holder, int position) {
       String category_name= Categories_names.get(position);
       // Log.d("adapterrr", String.valueOf(category));

        holder.categoryTitleTextView.setText(category_name);

    }

    @Override
    public int getItemCount() {
        return Categories_names==null? 0: Categories_names.size();
    }
    public interface CategoriesOnClickHandler {

        void onClickM(String product);
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final CardView categoryCardView;
        final TextView categoryTitleTextView;



        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryCardView=itemView.findViewById(R.id.category_item);
            categoryTitleTextView=itemView.findViewById(R.id.tv_category_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String Category=Categories_names.get(adapterPosition);
            categoriesOnClickHandler.onClickM(Category);
        }
    }
}
