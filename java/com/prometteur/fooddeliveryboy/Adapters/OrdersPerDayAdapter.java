package com.prometteur.fooddeliveryboy.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prometteur.fooddeliveryboy.databinding.RecyclerAllordersBinding;
import com.prometteur.fooddeliveryboy.databinding.RecyclerOrdersPerdayBinding;

import java.util.ArrayList;
import java.util.List;

public class OrdersPerDayAdapter extends RecyclerView.Adapter<OrdersPerDayAdapter.ViewHolder> {

    Context nContext;
    List<Boolean> hidden=new ArrayList<>();

    public OrdersPerDayAdapter(Context nContext) {
        this.nContext = nContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RecyclerOrdersPerdayBinding.inflate(((Activity) nContext).getLayoutInflater(),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerOrdersPerdayBinding viewBinding;

        public ViewHolder(@NonNull RecyclerOrdersPerdayBinding itemView) {
            super(itemView.getRoot());
            viewBinding=itemView;
        }
    }
}
