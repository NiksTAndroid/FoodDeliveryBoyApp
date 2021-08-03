package com.prometteur.fooddeliveryboy.RetroFit;



import com.prometteur.fooddeliveryboy.PojoModels.CommonResponse;
import com.prometteur.fooddeliveryboy.PojoModels.LoginUserDetailsPojo;
import com.prometteur.fooddeliveryboy.Utils.CommonMethods;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {



    @POST("mLogin")
    @FormUrlEncoded
    Call<LoginUserDetailsPojo> loginUser(@Field("email") String email,
                                         @Field("password") String password,
                                         @Field("fcm_key") String fcm_key);
    @POST("mLogout")
    @FormUrlEncoded
    Call<CommonResponse> logOutUser(@Field("driver_id") String driver_id);



    @POST("fields/driver")
    @FormUrlEncoded
    Call<LoginUserDetailsPojo> userDetails(@Field("driver_id") String driver_id);

    @POST("mAdd/driver")
    @FormUrlEncoded
    Call<CommonResponse> updateProfile(@Field("name") String name,
                                      @Field("driver_id") String driver_id,
                                      @Field("aadhar_num") String aadhar_num,
                                      @Field("license_num") String license_num,
                                      @Field("vehicle_type") String vehicle_type,
                                      @Field("vehicle_num") String vehicle_num);


    @POST("mAdd/update_profile_photo") ///("profile_image") key for image
    @Multipart
    Call<CommonResponse> updateProfilePhoto(@Part MultipartBody.Part userImage,
                                         @Part("driver_id") String driver_id
                                         );

    @POST("sendOtp")
    @FormUrlEncoded
    Call<LoginUserDetailsPojo> sendOtp(@Field("driver_id") String driver_id,
                                         @Field("mobile") String mobile,
                                         @Field("fcm_key") String fcm_key);


    @POST("changeMobile")
    @FormUrlEncoded
    Call<LoginUserDetailsPojo> changeMobileNum(@Field("driver_id") String driver_id,
                                         @Field("phone") String phoneNum,
                                         @Field("otp") String otp);


    @POST("fields/driver_notifications")
    @FormUrlEncoded
    Call<LoginUserDetailsPojo> getNotifications(@Field("driver_id") String driverId);



}