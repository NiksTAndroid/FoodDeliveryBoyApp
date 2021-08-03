package com.prometteur.fooddeliveryboy.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prometteur.fooddeliveryboy.Adapters.AllOrdersAdapter;
import com.prometteur.fooddeliveryboy.R;
import com.prometteur.fooddeliveryboy.Utils.CommonMethods;
import com.prometteur.fooddeliveryboy.Utils.StringConstants;
import com.prometteur.fooddeliveryboy.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {
    FragmentHomeBinding viewBinding;
    AllOrdersAdapter allOrdersAdapter;
    Context nContext;


    public HomeFragment(Context nContext) {
        this.nContext=nContext;
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewBinding=FragmentHomeBinding.inflate(inflater);
        initRecyclerView();
        setData();
        return viewBinding.getRoot();
    }

    private void setData() {
        CommonMethods.loadImage(viewBinding.civProfileImage,StringConstants.userApiDetails.getProfileImage());
    }

    private void initRecyclerView() {
        allOrdersAdapter=new AllOrdersAdapter(nContext, StringConstants.HOMEFLOW);
        viewBinding.recyclerAllOrders.setLayoutManager(new LinearLayoutManager(nContext, RecyclerView.VERTICAL,false));
        viewBinding.recyclerAllOrders.setAdapter(allOrdersAdapter);

    }
}