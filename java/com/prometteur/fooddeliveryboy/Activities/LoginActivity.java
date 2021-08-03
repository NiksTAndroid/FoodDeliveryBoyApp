package com.prometteur.fooddeliveryboy.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.Gson;
import com.prometteur.fooddeliveryboy.PojoModels.LoginUserDetailsPojo;
import com.prometteur.fooddeliveryboy.R;
import com.prometteur.fooddeliveryboy.RetroFit.ApiConfig;
import com.prometteur.fooddeliveryboy.Utils.CommonMethods;
import com.prometteur.fooddeliveryboy.Utils.LoadingDialog;
import com.prometteur.fooddeliveryboy.Utils.Preference;
import com.prometteur.fooddeliveryboy.Utils.StringConstants;
import com.prometteur.fooddeliveryboy.databinding.ActivityLoginBinding;
import com.prometteur.fooddeliveryboy.databinding.DialogForgotPasswordBinding;
import com.prometteur.fooddeliveryboy.databinding.DialogOrderRequestBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.prometteur.fooddeliveryboy.Utils.CommonMethods.isNetworkAvailable;
import static com.prometteur.fooddeliveryboy.Utils.CommonMethods.showFailToast;
import static com.prometteur.fooddeliveryboy.Utils.CommonMethods.showInfoToast;
import static com.prometteur.fooddeliveryboy.Utils.StringConstants.USERDETAILS;
import static com.prometteur.fooddeliveryboy.Utils.StringConstants.USERID;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    ActivityLoginBinding viewBinding;
    Context nContext;
    Dialog loadingDialog;
    String fcmToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        viewBinding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        nContext=this;
        loadingDialog= LoadingDialog.getLoadingDialog(nContext);

        viewBinding.btnSIGNIN.setOnClickListener(this);
        viewBinding.tvForgotPassword.setOnClickListener(this);
        if(isNetworkAvailable(nContext)) {
            getFCMToken();
        }else{
            showFailToast(nContext,nContext.getResources().getString(R.string.no_internet));
        }
        if(Preference.isLogin(nContext)){
            Gson gson = new Gson();
            String userdata = Preference.getPrefData(nContext, USERDETAILS);
            StringConstants.DRIVERID = Preference.getPrefData(nContext, USERID);
            StringConstants.userApiDetails = gson.fromJson(userdata, LoginUserDetailsPojo.Result.class);
            startActivity(new Intent(nContext,HomeActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSIGNIN:
                //startActivity(new Intent(nContext,HomeActivity.class));

                ApiCall();

                break;
            case R.id.tvForgotPassword:
                    showForgotDialog();
                break;

        }
    }

    private void showForgotDialog() {

        Dialog dialog = new Dialog(nContext);
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        DialogForgotPasswordBinding forgotDialogBinding = DialogForgotPasswordBinding.inflate(((Activity)nContext).getLayoutInflater());


        dialog.setContentView(R.layout.dialog_forgot_password);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        forgotDialogBinding.tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                startActivity(new Intent(nContext,ForgotPasswordActivity.class));
                finish();
            }
        });
        forgotDialogBinding.ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private void ApiCall(){
        loadingDialog.show();
        if(viewBinding.edtEmail.getText().toString().isEmpty()){
            viewBinding.edtEmail.setError(nContext.getResources().getString(R.string.required));
            return;

        }
        if(viewBinding.edtPassword.getText().toString().isEmpty()){
            viewBinding.edtPassword.setError(nContext.getResources().getString(R.string.required));
            return;

        }

        if(CommonMethods.isNetworkAvailable(nContext)) {
            ApiConfig.getApiClient()
                    .loginUser(viewBinding.edtEmail.getText().toString(), viewBinding.edtPassword.getText().toString(), fcmToken)
                    .enqueue(new Callback<LoginUserDetailsPojo>() {
                        @Override
                        public void onResponse(Call<LoginUserDetailsPojo> call, Response<LoginUserDetailsPojo> response) {
                            LoginUserDetailsPojo loginResponse = response.body();
                            if (loginResponse.getStatus() == 0 && loginResponse.getMsg().equalsIgnoreCase("Invalid Credentials")) {
                                showFailToast(nContext,loginResponse.getMsg());

                            }
                            if (loginResponse.getStatus() == 1) {
                                Preference.userDetails(nContext,loginResponse.getResult().get(0),true);
                                StringConstants.userApiDetails=loginResponse.getResult().get(0);
                                loadingDialog.cancel();
                                startActivity(new Intent(nContext,HomeActivity.class));
                                finish();

                            }else{
                                loadingDialog.cancel();
                                showInfoToast(nContext,nContext.getResources().getString(R.string.try_again));
                            }

                        }

                        @Override
                        public void onFailure(Call<LoginUserDetailsPojo> call, Throwable t) {
                            showFailToast(nContext,nContext.getResources().getString(R.string.went_Wrong));
                            Log.e(TAG, "onFailure: "+t.getMessage() );
                            Log.e(TAG, "onFailure: "+t.getStackTrace() );
                            loadingDialog.cancel();
                        }
                    });

        }else{
            showFailToast(nContext,nContext.getResources().getString(R.string.no_internet));
        }

    }

    private void getFCMToken(){
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener((Activity) nContext, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                fcmToken=instanceIdResult.getToken();
                Log.e(TAG, "onSuccess: "+fcmToken );
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "onFailure: "+ e.getMessage() );
                e.printStackTrace();
            }
        });

    }

}