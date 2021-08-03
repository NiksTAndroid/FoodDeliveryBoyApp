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
import com.prometteur.fooddeliveryboy.Utils.StringConstants;
import com.prometteur.fooddeliveryboy.databinding.FragmentHomeBinding;
import com.prometteur.fooddeliveryboy.databinding.FragmentMyRidesBinding;


public class MyRidesFragment extends Fragment implements View.OnClickListener {

    FragmentMyRidesBinding viewBinding;
    AllOrdersAdapter allOrdersAdapter;
    Context nContext;

    public MyRidesFragment(Context context) {
        // Required empty public constructor
        this.nContext=context;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewBinding= FragmentMyRidesBinding.inflate(inflater);
        initRecyclerView();
        viewBinding.ivSearch.setOnClickListener(this);
        return viewBinding.getRoot();
    }
    private void initRecyclerView() {
        allOrdersAdapter=new AllOrdersAdapter(nContext, StringConstants.RIDEFLOW);
        viewBinding.recyclerAllOrders.setLayoutManager(new LinearLayoutManager(nContext, RecyclerView.VERTICAL,false));
        viewBinding.recyclerAllOrders.setAdapter(allOrdersAdapter);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivSearch:
                viewBinding.edtSearch.setVisibility(View.VISIBLE);
                viewBinding.tvBarTitle.setVisibility(View.GONE);
                viewBinding.ivSearch.setVisibility(View.GONE);


                break;
        }
    }
}