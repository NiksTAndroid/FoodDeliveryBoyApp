package com.prometteur.fooddeliveryboy.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prometteur.fooddeliveryboy.databinding.RecycleOrderedProductsBinding;
import com.prometteur.fooddeliveryboy.databinding.RecyclerAllordersBinding;

import java.util.ArrayList;
import java.util.List;

public class OrderedProductsAdapter extends RecyclerView.Adapter<OrderedProductsAdapter.ViewHolder> {

    Context nContext;
    List<Boolean> hidden=new ArrayList<>();
    OrdersPerDayAdapter ordersPerDayAdapter;

    public OrderedProductsAdapter(Context nContext) {
        this.nContext = nContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RecycleOrderedProductsBinding.inflate(((Activity) nContext).getLayoutInflater(),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecycleOrderedProductsBinding viewBinding;

        public ViewHolder(@NonNull RecycleOrderedProductsBinding itemView) {
            super(itemView.getRoot());
            viewBinding=itemView;
        }
    }
}
