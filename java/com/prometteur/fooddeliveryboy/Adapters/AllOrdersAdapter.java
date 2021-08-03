package com.prometteur.fooddeliveryboy.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prometteur.fooddeliveryboy.Activities.OrderDetailsActivity;
import com.prometteur.fooddeliveryboy.Activities.RideDetailsActivity;
import com.prometteur.fooddeliveryboy.R;
import com.prometteur.fooddeliveryboy.Utils.StringConstants;
import com.prometteur.fooddeliveryboy.databinding.DialogOrderRequestBinding;
import com.prometteur.fooddeliveryboy.databinding.RecyclerAllordersBinding;

import java.util.ArrayList;
import java.util.List;

import static com.prometteur.fooddeliveryboy.Utils.StringConstants.HOMEFLOW;
import static com.prometteur.fooddeliveryboy.Utils.StringConstants.RIDEFLOW;

public class AllOrdersAdapter extends RecyclerView.Adapter<AllOrdersAdapter.ViewHolder> {

    Context nContext;
    List<Boolean> hidden=new ArrayList<>();
    List<String> status=new ArrayList<>();
    OrdersPerDayAdapter ordersPerDayAdapter;
    String flowFrom;


    public AllOrdersAdapter(Context nContext,String flowFrom) {
        this.nContext = nContext;
        this.flowFrom = flowFrom;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RecyclerAllordersBinding.inflate(((Activity) nContext).getLayoutInflater(),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ordersPerDayAdapter=new OrdersPerDayAdapter(nContext);
        holder.viewBinding.recyclerOrdersPerDay.setLayoutManager(new LinearLayoutManager(nContext, RecyclerView.VERTICAL,false));
        holder.viewBinding.recyclerOrdersPerDay.setAdapter(ordersPerDayAdapter);
        for (int i=0;i<5;i++){
            if(i==0){
            hidden.add(false);
            }else{
                hidden.add(true);
            }
        }
        for (int i=0;i<5;i++){
            if(i==2 || i==4){
                status.add("Delivered");
            }else{
                status.add("Picked Up");
            }
        }
        if(flowFrom.equalsIgnoreCase(RIDEFLOW)) {
            if(status.get(position).equalsIgnoreCase("Delivered")){
                holder.viewBinding.tvStatus.setTextColor(nContext.getResources().getColor(R.color.green_56));
            }
            holder.viewBinding.tvStatus.setText(status.get(position));

        }
        if(!hidden.get(position)){
            holder.viewBinding.ivShowHide.setImageDrawable(nContext.getResources().getDrawable(R.drawable.iv_white_uparrow));
            holder.viewBinding.tvTitleOrderList.setVisibility(View.VISIBLE);
            holder.viewBinding.view1.setVisibility(View.VISIBLE);
            holder.viewBinding.recyclerOrdersPerDay.setVisibility(View.VISIBLE);
        }else{
            holder.viewBinding.ivShowHide.setImageDrawable(nContext.getResources().getDrawable(R.drawable.iv_white_arrow_down));
            holder.viewBinding.tvTitleOrderList.setVisibility(View.GONE);
            holder.viewBinding.view1.setVisibility(View.GONE);
            holder.viewBinding.recyclerOrdersPerDay.setVisibility(View.GONE);
        }


        holder.viewBinding.ivShowHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hidden.get(position)){
                    hidden.set(position,false);
                    holder.viewBinding.ivShowHide.setImageDrawable(nContext.getResources().getDrawable(R.drawable.iv_white_uparrow));
                    holder.viewBinding.tvTitleOrderList.setVisibility(View.VISIBLE);
                    holder.viewBinding.recyclerOrdersPerDay.setVisibility(View.VISIBLE);
                    holder.viewBinding.view1.setVisibility(View.VISIBLE);

                }else{
                    hidden.set(position,true);
                    holder.viewBinding.ivShowHide.setImageDrawable(nContext.getResources().getDrawable(R.drawable.iv_white_arrow_down));
                    holder.viewBinding.tvTitleOrderList.setVisibility(View.GONE);
                    holder.viewBinding.recyclerOrdersPerDay.setVisibility(View.GONE);
                    holder.viewBinding.view1.setVisibility(View.GONE);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flowFrom.equalsIgnoreCase(RIDEFLOW)){
                    if(status.get(position).equalsIgnoreCase("Delivered")){
                        nContext.startActivity(new Intent(nContext, RideDetailsActivity.class));
                    }else {
                        nContext.startActivity(new Intent(nContext, OrderDetailsActivity.class));
                    }
                }
                else if(flowFrom.equalsIgnoreCase(HOMEFLOW)){
                    showOrderRequestDialog();
                }
            }
        });

    }

    private void showOrderRequestDialog() {
        Dialog dialog = new Dialog(nContext);
        DialogOrderRequestBinding orderReqBinding = DialogOrderRequestBinding.inflate(((Activity)nContext).getLayoutInflater());
        dialog.setContentView(R.layout.dialog_order_request);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.findViewById(R.id.tvBtnAccept).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.findViewById(R.id.tvBtnReject).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerAllordersBinding viewBinding;

        public ViewHolder(@NonNull RecyclerAllordersBinding itemView) {
            super(itemView.getRoot());
            viewBinding=itemView;
        }
    }
}
