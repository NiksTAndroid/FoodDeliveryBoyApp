package com.prometteur.fooddeliveryboy.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.prometteur.fooddeliveryboy.PojoModels.CommonResponse;
import com.prometteur.fooddeliveryboy.PojoModels.LoginUserDetailsPojo;
import com.prometteur.fooddeliveryboy.R;
import com.prometteur.fooddeliveryboy.RetroFit.ApiConfig;
import com.prometteur.fooddeliveryboy.Utils.CommonMethods;
import com.prometteur.fooddeliveryboy.Utils.LoadingDialog;
import com.prometteur.fooddeliveryboy.Utils.Preference;
import com.prometteur.fooddeliveryboy.Utils.StringConstants;
import com.prometteur.fooddeliveryboy.databinding.ActivityEditProfileBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

import static com.prometteur.fooddeliveryboy.Utils.CommonMethods.showFailToast;
import static com.prometteur.fooddeliveryboy.Utils.CommonMethods.showInfoToast;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityEditProfileBinding viewBinding;
    Context nContext;
    Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        nContext = this;
        setData();
        loadingDialog= LoadingDialog.getLoadingDialog(nContext);
        viewBinding.btnUPDATE.setOnClickListener(this);
    }

    private void setData() {
        if(StringConstants.userApiDetails!=null) {
            viewBinding.edtName.setText(StringConstants.userApiDetails.getName());
            viewBinding.edtAadharCardNum.setText(StringConstants.userApiDetails.getAadharNum());
            viewBinding.edtLicenceNum.setText(StringConstants.userApiDetails.getLicenseNum());
            viewBinding.edtVehicleType.setText(StringConstants.userApiDetails.getVehicleType());
            viewBinding.edtVehicleNumber.setText(StringConstants.userApiDetails.getVehicleNum());
            viewBinding.edtEmail.setText(StringConstants.userApiDetails.getEmail());
            viewBinding.edtMobileNumber.setText(StringConstants.userApiDetails.getPhone());
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnUPDATE:
                if (verifyAadhar()){
                    viewBinding.edtAadharCardNum.setError("Aadhar Number Too short.");
                    return;
                }
                updateProfileApiCall();
                break;

        }
    }

    private boolean verifyAadhar(){
        if(getTextFromEditText(viewBinding.edtAadharCardNum).length()!=12){
            return false;
        }
        return true;
    }


    private String getTextFromEditText(EditText editText) {
        if (!editText.getText().toString().isEmpty()) {
            return editText.getText().toString();
        } else {
            return "";
        }

    }


    private void updateProfileApiCall() {

        loadingDialog.show();
        if (CommonMethods.isNetworkAvailable(nContext)) {
            ApiConfig.getApiClient().updateProfile(getTextFromEditText(viewBinding.edtName),
                    Preference.getPrefData(nContext, StringConstants.USERID),
                    getTextFromEditText(viewBinding.edtAadharCardNum),
                    getTextFromEditText(viewBinding.edtLicenceNum),
                    getTextFromEditText(viewBinding.edtVehicleType),
                    getTextFromEditText(viewBinding.edtVehicleNumber))
                    .enqueue(new Callback<CommonResponse>() {
                        @Override
                        public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                            CommonResponse apiResponse=response.body();
                            if(apiResponse!=null && apiResponse.getStatus()==1){
                                loadingDialog.cancel();
                                startActivity(new Intent(nContext,HomeActivity.class));
                                finish();
                            }else{
                                loadingDialog.cancel();
                                showInfoToast(nContext,nContext.getResources().getString(R.string.try_again));
                            }
                        }

                        @Override
                        public void onFailure(Call<CommonResponse> call, Throwable t) {
                            loadingDialog.cancel();
                            showFailToast(nContext,nContext.getResources().getString(R.string.went_Wrong));
                        }
                    });
        } else {
            loadingDialog.cancel();
            showFailToast(nContext,nContext.getResources().getString(R.string.no_internet));
        }
    }
}