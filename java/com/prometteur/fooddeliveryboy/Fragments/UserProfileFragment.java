package com.prometteur.fooddeliveryboy.Fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.prometteur.fooddeliveryboy.Activities.EditProfileActivity;
import com.prometteur.fooddeliveryboy.Activities.HomeActivity;
import com.prometteur.fooddeliveryboy.Activities.LoginActivity;
import com.prometteur.fooddeliveryboy.PojoModels.CommonResponse;
import com.prometteur.fooddeliveryboy.PojoModels.LoginUserDetailsPojo;
import com.prometteur.fooddeliveryboy.R;
import com.prometteur.fooddeliveryboy.RetroFit.ApiConfig;
import com.prometteur.fooddeliveryboy.Utils.CommonMethods;
import com.prometteur.fooddeliveryboy.Utils.LoadingDialog;
import com.prometteur.fooddeliveryboy.Utils.Preference;
import com.prometteur.fooddeliveryboy.Utils.StringConstants;
import com.prometteur.fooddeliveryboy.databinding.FragmentUserProfileBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.content.FileProvider.getUriForFile;
import static com.prometteur.fooddeliveryboy.Utils.CommonMethods.isNetworkAvailable;
import static com.prometteur.fooddeliveryboy.Utils.CommonMethods.showFailToast;
import static com.prometteur.fooddeliveryboy.Utils.CommonMethods.showInfoToast;
import static com.prometteur.fooddeliveryboy.Utils.CommonMethods.showSuccessToast;


public class UserProfileFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "UserProfileFragment";
    FragmentUserProfileBinding viewBinding;
    Context nContext;
    Dialog loadingDialog;
    LoginUserDetailsPojo.Result userResultData;
    public static int REQUEST_IMAGE = 121;
    String fileName;
    Bitmap mBitmap;
    Uri imageUri;
    String driverID;


    public UserProfileFragment(Context context) {
        this.nContext=context;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewBinding=FragmentUserProfileBinding.inflate(getLayoutInflater());
        loadingDialog= LoadingDialog.getLoadingDialog(nContext);
        viewBinding.tvLogout.setOnClickListener(this);
        viewBinding.tvEditProfile.setOnClickListener(this);
        viewBinding.ivTakePicture.setOnClickListener(this);
        if(StringConstants.userApiDetails!=null && StringConstants.userApiDetails.getId()!=null){
            driverID=StringConstants.userApiDetails.getId();
        }else{
            driverID=Preference.getPrefData(nContext, StringConstants.USERID);
        }
        getUserDetails();
        return viewBinding.getRoot();
    }

    private void getUserDetails() {
        loadingDialog.show();

        Call<LoginUserDetailsPojo> userDetails=null;

        if(isNetworkAvailable(nContext)){
            if(StringConstants.userApiDetails!=null && StringConstants.userApiDetails.getId()!=null) {
               userDetails= ApiConfig.getApiClient().userDetails(StringConstants.userApiDetails.getId());
            }else{
                userDetails=ApiConfig.getApiClient().userDetails(Preference.getPrefData(nContext,StringConstants.USERID));
            }
            if(userDetails!=null){
                userDetails.enqueue(new Callback<LoginUserDetailsPojo>() {
                    @Override
                    public void onResponse(Call<LoginUserDetailsPojo> call, Response<LoginUserDetailsPojo> response) {
                        LoginUserDetailsPojo apiResponse=response.body();
                        if (response.isSuccessful() && apiResponse != null &&  apiResponse.getStatus() == 1) {
                           userResultData=apiResponse.getResult().get(0);
                           setData();

                        }
                        loadingDialog.cancel();
                    }

                    @Override
                    public void onFailure(Call<LoginUserDetailsPojo> call, Throwable t) {

                    }
                });
            }

        }else{
            loadingDialog.cancel();
            showFailToast(nContext,nContext.getResources().getString(R.string.no_internet));
        }
    }

    private void setData() {

        CommonMethods.loadImage(viewBinding.civProfileImage,userResultData.getProfileImage());
        viewBinding.tvUserName.setText(userResultData.getName());
        viewBinding.tvDriverID.setText("ID -#"+userResultData.getId());
        viewBinding.tvName.setText(userResultData.getName());
        viewBinding.tvAadharCardNum.setText(userResultData.getAadharNum());
        viewBinding.tvLicenceNum.setText(userResultData.getLicenseNum());
        viewBinding.tvVehicleType.setText(userResultData.getVehicleType());
        viewBinding.tvVehicleNum.setText(userResultData.getVehicleNum());
        viewBinding.tvEmail.setText(userResultData.getEmail());
        viewBinding.tvPhone.setText(userResultData.getPhone());

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tvEditProfile:
                startActivity(new Intent(nContext, EditProfileActivity.class));
                break;
            case R.id.tvLogout:
                logOutApiCall();
                break;
            case R.id.ivTakePicture:
                Dexter.withActivity((Activity) nContext)
                        .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                if (report.areAllPermissionsGranted()) {
                                    showImagePickerOptions(new PickerOptionListener() {
                                        @Override
                                        public void onTakeCameraSelected() {
                                            launchCameraIntent();
                                        }

                                        @Override
                                        public void onChooseGallerySelected() {
                                            launchGalleryIntent();
                                        }
                                    });
                                } else {
                                    // TODO - handle permission denied case
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                                token.continuePermissionRequest();
                            }


                        }).check();

                break;
        }
    }

    private void logOutApiCall() {
        loadingDialog.show();
        if(CommonMethods.isNetworkAvailable(nContext)){
            ApiConfig.getApiClient().logOutUser(StringConstants.userApiDetails.getId()).enqueue(new Callback<CommonResponse>() {
                @Override
                public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                    CommonResponse apiResponse=response.body();
                    if(apiResponse.getStatus()==1) {
                        Preference.clearAllPref(nContext);
                        Intent intent = new Intent(nContext, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        nContext.startActivity(intent);
                        ((Activity)nContext).finish();
                    }
                    loadingDialog.cancel();

                }

                @Override
                public void onFailure(Call<CommonResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure: "+t.getMessage() );
                    loadingDialog.cancel();


                }
            });
        }else{
            loadingDialog.cancel();
            showFailToast(nContext,nContext.getResources().getString(R.string.no_internet));
        }
    }

    private void showImagePickerOptions(PickerOptionListener listener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(nContext);
        builder.setTitle(nContext.getResources().getString(R.string.lbl_set_profile_photo));

        // add a list
        String[] animals = {nContext.getResources().getString(R.string.lbl_take_camera_picture), nContext.getResources().getString(R.string.lbl_choose_from_gallery)};
        builder.setItems(animals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        listener.onTakeCameraSelected();
                        break;
                    case 1:
                        listener.onChooseGallerySelected();
                        break;
                }
            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public interface PickerOptionListener {
        void onTakeCameraSelected();

        void onChooseGallerySelected();
    }

    private void launchCameraIntent() {
        Dexter.withActivity((Activity) nContext)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            fileName = System.currentTimeMillis() + ".jpg";
                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, getCacheImagePath(fileName));
                            if (takePictureIntent.resolveActivity(nContext.getPackageManager()) != null) {
                                startActivityForResult(takePictureIntent, REQUEST_IMAGE);
                            }
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

    }

    private void launchGalleryIntent() {

        Dexter.withActivity((Activity) nContext)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, REQUEST_IMAGE);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }
    private Uri getCacheImagePath(String fileName) {
        File path = new File(nContext.getExternalCacheDir(), "camera");
        if (!path.exists()) path.mkdirs();
        File image = new File(path, fileName);
        return getUriForFile(nContext, nContext.getPackageName() + ".provider", image);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    imageUri = data.getData();
                    Log.e(TAG, "onActivityResult: " + imageUri);
                    viewBinding.civProfileImage.setImageURI(imageUri);

                    File file = new File(CommonMethods.getPath(nContext, imageUri));
                    uploadImageApiCall(file);
                    Log.e(TAG, "onActivityResult: " + file.getPath());
                } else {
                    imageUri = getCacheImagePath(fileName);
                    //isImageSelected=true;
                    viewBinding.civProfileImage.setImageURI(imageUri);
                    try {
                        mBitmap = MediaStore.Images.Media.getBitmap(nContext.getContentResolver(), imageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    mBitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);

                    String newpath = MediaStore.Images.Media.insertImage(nContext.getContentResolver(), mBitmap, "newBitmap", null);

                    File file = new File(CommonMethods.getPath(nContext, Uri.parse(newpath)));
                    uploadImageApiCall(file);
                }
                //binding.ivAdd.setVisibility(View.GONE);

            }
        }
    }

    private void uploadImageApiCall(File file) {

        loadingDialog.show();
        MultipartBody.Part imagepart=null ;
        if(file!=null){
            imagepart = CommonMethods.prepareFilePart("profile_image", file);
        }
        if (CommonMethods.isNetworkAvailable(nContext)) {
            ApiConfig.getApiClient().updateProfilePhoto(imagepart,driverID)
                    .enqueue(new Callback<CommonResponse>() {
                        @Override
                        public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                            CommonResponse apiResponse=response.body();
                            if(apiResponse!=null && apiResponse.getStatus()==1){
                                loadingDialog.cancel();
                                showSuccessToast(nContext,"Profile image changed successfully");
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