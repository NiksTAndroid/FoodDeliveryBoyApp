package com.prometteur.fooddeliveryboy.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.prometteur.fooddeliveryboy.Adapters.OrderedProductsAdapter;
import com.prometteur.fooddeliveryboy.R;
import com.prometteur.fooddeliveryboy.databinding.ActivityOrderDetailsBinding;

public class OrderDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "OrderDetailsActivity";
    ActivityOrderDetailsBinding viewBinding;
    OrderedProductsAdapter orderedProductsAdapter;
    Context nContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding=ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        nContext=OrderDetailsActivity.this;
        viewBinding.ivBackPress.setOnClickListener(this);
        initRecyclerView();
    }


    private void initRecyclerView() {
        orderedProductsAdapter=new OrderedProductsAdapter(nContext);
        viewBinding.recycleOrderedProducts.setLayoutManager(new LinearLayoutManager(nContext, RecyclerView.VERTICAL,false));
        viewBinding.recycleOrderedProducts.setAdapter(orderedProductsAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivBackPress:
                finish();
                break;
        }
    }
}