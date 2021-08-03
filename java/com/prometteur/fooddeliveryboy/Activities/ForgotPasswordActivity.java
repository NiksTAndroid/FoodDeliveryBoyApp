package com.prometteur.fooddeliveryboy.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.prometteur.fooddeliveryboy.R;
import com.prometteur.fooddeliveryboy.databinding.ActivityForgotPasswordBinding;


public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ForgotPasswordActivity";
    ActivityForgotPasswordBinding viewBinding;
    Context nContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding=ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        nContext=this;

        viewBinding.btnSUBMIT.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSUBMIT:
                startActivity(new Intent(nContext,LoginActivity.class));
                finish();
                break;
        }
    }
}