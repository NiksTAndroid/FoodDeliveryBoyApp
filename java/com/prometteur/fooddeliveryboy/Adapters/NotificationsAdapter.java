package com.prometteur.fooddeliveryboy.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prometteur.fooddeliveryboy.databinding.RecycleNotifyBinding;
import com.prometteur.fooddeliveryboy.databinding.RecyclerAllordersBinding;

import java.util.ArrayList;
import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {

    Context nContext;


    public NotificationsAdapter(Context nContext) {
        this.nContext = nContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RecycleNotifyBinding.inflate(((Activity) nContext).getLayoutInflater(),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecycleNotifyBinding viewBinding;

        public ViewHolder(@NonNull RecycleNotifyBinding itemView) {
            super(itemView.getRoot());
            viewBinding=itemView;
        }
    }
}
