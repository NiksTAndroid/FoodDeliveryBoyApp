package com.prometteur.fooddeliveryboy.PojoModels;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LoginUserDetailsPojo {



    @SerializedName("status")
    private Integer status;
    @SerializedName("msg")
    private String msg;
    @SerializedName("result")
    private List<Result> result = null;

    @SerializedName("status")
    public Integer getStatus() {
        return status;
    }

    @SerializedName("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    @SerializedName("msg")
    public String getMsg() {
        return msg;
    }

    @SerializedName("msg")
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @SerializedName("result")
    public List<Result> getResult() {
        return result;
    }

    @SerializedName("result")
    public void setResult(List<Result> result) {
        this.result = result;
    }


    public class Result implements Serializable
    {

        @SerializedName("id")
        private String id;
        @SerializedName("google_id")
        private String googleId;
        @SerializedName("fb_id")
        private String fbId;
        @SerializedName("name")
        private String name;
        @SerializedName("role")
        private String role;
        @SerializedName("email")
        private String email;
        @SerializedName("profile_image")
        private String profileImage;
        @SerializedName("email_verified_at")
        private String emailVerifiedAt;
        @SerializedName("password")
        private String password;
        @SerializedName("fcm_key")
        private String fcmKey;
        @SerializedName("api_token")
        private String apiToken;
        @SerializedName("otp")
        private String otp;
        @SerializedName("phone")
        private String phone;
        @SerializedName("aadhar_num")
        private String aadharNum;
        @SerializedName("license_num")
        private String licenseNum;
        @SerializedName("vehicle_type")
        private String vehicleType;
        @SerializedName("vehicle_num")
        private String vehicleNum;
        @SerializedName("aadhar_image")
        private String aadharImage;
        @SerializedName("license_image")
        private String licenseImage;
        @SerializedName("remember_token")
        private String rememberToken;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private String updatedAt;
        @SerializedName("active")
        private String active;
        @SerializedName("stripe_id")
        private String stripeId;
        @SerializedName("card_brand")
        private String cardBrand;
        @SerializedName("card_last_four")
        private String cardLastFour;
        @SerializedName("trial_ends_at")
        private String trialEndsAt;
        @SerializedName("verification_code")
        private String verificationCode;
        @SerializedName("phone_verified_at")
        private String phoneVerifiedAt;
        @SerializedName("plan_id")
        private String planId;
        @SerializedName("plan_status")
        private String planStatus;
        @SerializedName("driver_status")
        private String driverStatus;
        @SerializedName("cancel_url")
        private String cancelUrl;
        @SerializedName("update_url")
        private String updateUrl;
        @SerializedName("checkout_id")
        private String checkoutId;
        @SerializedName("subscription_plan_id")
        private String subscriptionPlanId;
        @SerializedName("stripe_account")
        private String stripeAccount;
        @SerializedName("birth_date")
        private String birthDate;
        @SerializedName("lat")
        private String lat;
        @SerializedName("lng")
        private String lng;
        @SerializedName("working")
        private String working;
        @SerializedName("onorder")
        private String onorder;
        @SerializedName("numorders")
        private String numorders;
        @SerializedName("rejectedorders")
        private String rejectedorders;
        @SerializedName("paypal_subscribtion_id")
        private String paypalSubscribtionId;
        @SerializedName("mollie_customer_id")
        private String mollieCustomerId;
        @SerializedName("mollie_mandate_id")
        private String mollieMandateId;
        @SerializedName("tax_percentage")
        private String taxPercentage;
        @SerializedName("extra_billing_information")
        private String extraBillingInformation;
        @SerializedName("mollie_subscribtion_id")
        private String mollieSubscribtionId;
        @SerializedName("paystack_subscribtion_id")
        private String paystackSubscribtionId;
        @SerializedName("paystack_trans_id")
        private String paystackTransId;

        @SerializedName("id")
        public String getId() {
            return id;
        }

        @SerializedName("id")
        public void setId(String id) {
            this.id = id;
        }

        @SerializedName("google_id")
        public String getGoogleId() {
            return googleId;
        }

        @SerializedName("google_id")
        public void setGoogleId(String googleId) {
            this.googleId = googleId;
        }

        @SerializedName("fb_id")
        public String getFbId() {
            return fbId;
        }

        @SerializedName("fb_id")
        public void setFbId(String fbId) {
            this.fbId = fbId;
        }

        @SerializedName("name")
        public String getName() {
            return name;
        }

        @SerializedName("name")
        public void setName(String name) {
            this.name = name;
        }

        @SerializedName("role")
        public String getRole() {
            return role;
        }

        @SerializedName("role")
        public void setRole(String role) {
            this.role = role;
        }

        @SerializedName("email")
        public String getEmail() {
            return email;
        }

        @SerializedName("email")
        public void setEmail(String email) {
            this.email = email;
        }

        @SerializedName("profile_image")
        public String getProfileImage() {
            return profileImage;
        }

        @SerializedName("profile_image")
        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }

        @SerializedName("email_verified_at")
        public String getEmailVerifiedAt() {
            return emailVerifiedAt;
        }

        @SerializedName("email_verified_at")
        public void setEmailVerifiedAt(String emailVerifiedAt) {
            this.emailVerifiedAt = emailVerifiedAt;
        }

        @SerializedName("password")
        public String getPassword() {
            return password;
        }

        @SerializedName("password")
        public void setPassword(String password) {
            this.password = password;
        }

        @SerializedName("fcm_key")
        public String getFcmKey() {
            return fcmKey;
        }

        @SerializedName("fcm_key")
        public void setFcmKey(String fcmKey) {
            this.fcmKey = fcmKey;
        }

        @SerializedName("api_token")
        public String getApiToken() {
            return apiToken;
        }

        @SerializedName("api_token")
        public void setApiToken(String apiToken) {
            this.apiToken = apiToken;
        }

        @SerializedName("otp")
        public String getOtp() {
            return otp;
        }

        @SerializedName("otp")
        public void setOtp(String otp) {
            this.otp = otp;
        }

        @SerializedName("phone")
        public String getPhone() {
            return phone;
        }

        @SerializedName("phone")
        public void setPhone(String phone) {
            this.phone = phone;
        }

        @SerializedName("aadhar_num")
        public String getAadharNum() {
            return aadharNum;
        }

        @SerializedName("aadhar_num")
        public void setAadharNum(String aadharNum) {
            this.aadharNum = aadharNum;
        }

        @SerializedName("license_num")
        public String getLicenseNum() {
            return licenseNum;
        }

        @SerializedName("license_num")
        public void setLicenseNum(String licenseNum) {
            this.licenseNum = licenseNum;
        }

        @SerializedName("vehicle_type")
        public String getVehicleType() {
            return vehicleType;
        }

        @SerializedName("vehicle_type")
        public void setVehicleType(String vehicleType) {
            this.vehicleType = vehicleType;
        }

        @SerializedName("vehicle_num")
        public String getVehicleNum() {
            return vehicleNum;
        }

        @SerializedName("vehicle_num")
        public void setVehicleNum(String vehicleNum) {
            this.vehicleNum = vehicleNum;
        }

        @SerializedName("aadhar_image")
        public String getAadharImage() {
            return aadharImage;
        }

        @SerializedName("aadhar_image")
        public void setAadharImage(String aadharImage) {
            this.aadharImage = aadharImage;
        }

        @SerializedName("license_image")
        public String getLicenseImage() {
            return licenseImage;
        }

        @SerializedName("license_image")
        public void setLicenseImage(String licenseImage) {
            this.licenseImage = licenseImage;
        }

        @SerializedName("remember_token")
        public String getRememberToken() {
            return rememberToken;
        }

        @SerializedName("remember_token")
        public void setRememberToken(String rememberToken) {
            this.rememberToken = rememberToken;
        }

        @SerializedName("created_at")
        public String getCreatedAt() {
            return createdAt;
        }

        @SerializedName("created_at")
        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        @SerializedName("updated_at")
        public String getUpdatedAt() {
            return updatedAt;
        }

        @SerializedName("updated_at")
        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        @SerializedName("active")
        public String getActive() {
            return active;
        }

        @SerializedName("active")
        public void setActive(String active) {
            this.active = active;
        }

        @SerializedName("stripe_id")
        public String getStripeId() {
            return stripeId;
        }

        @SerializedName("stripe_id")
        public void setStripeId(String stripeId) {
            this.stripeId = stripeId;
        }

        @SerializedName("card_brand")
        public String getCardBrand() {
            return cardBrand;
        }

        @SerializedName("card_brand")
        public void setCardBrand(String cardBrand) {
            this.cardBrand = cardBrand;
        }

        @SerializedName("card_last_four")
        public String getCardLastFour() {
            return cardLastFour;
        }

        @SerializedName("card_last_four")
        public void setCardLastFour(String cardLastFour) {
            this.cardLastFour = cardLastFour;
        }

        @SerializedName("trial_ends_at")
        public String getTrialEndsAt() {
            return trialEndsAt;
        }

        @SerializedName("trial_ends_at")
        public void setTrialEndsAt(String trialEndsAt) {
            this.trialEndsAt = trialEndsAt;
        }

        @SerializedName("verification_code")
        public String getVerificationCode() {
            return verificationCode;
        }

        @SerializedName("verification_code")
        public void setVerificationCode(String verificationCode) {
            this.verificationCode = verificationCode;
        }

        @SerializedName("phone_verified_at")
        public String getPhoneVerifiedAt() {
            return phoneVerifiedAt;
        }

        @SerializedName("phone_verified_at")
        public void setPhoneVerifiedAt(String phoneVerifiedAt) {
            this.phoneVerifiedAt = phoneVerifiedAt;
        }

        @SerializedName("plan_id")
        public String getPlanId() {
            return planId;
        }

        @SerializedName("plan_id")
        public void setPlanId(String planId) {
            this.planId = planId;
        }

        @SerializedName("plan_status")
        public String getPlanStatus() {
            return planStatus;
        }

        @SerializedName("plan_status")
        public void setPlanStatus(String planStatus) {
            this.planStatus = planStatus;
        }

        @SerializedName("driver_status")
        public String getDriverStatus() {
            return driverStatus;
        }

        @SerializedName("driver_status")
        public void setDriverStatus(String driverStatus) {
            this.driverStatus = driverStatus;
        }

        @SerializedName("cancel_url")
        public String getCancelUrl() {
            return cancelUrl;
        }

        @SerializedName("cancel_url")
        public void setCancelUrl(String cancelUrl) {
            this.cancelUrl = cancelUrl;
        }

        @SerializedName("update_url")
        public String getUpdateUrl() {
            return updateUrl;
        }

        @SerializedName("update_url")
        public void setUpdateUrl(String updateUrl) {
            this.updateUrl = updateUrl;
        }

        @SerializedName("checkout_id")
        public String getCheckoutId() {
            return checkoutId;
        }

        @SerializedName("checkout_id")
        public void setCheckoutId(String checkoutId) {
            this.checkoutId = checkoutId;
        }

        @SerializedName("subscription_plan_id")
        public String getSubscriptionPlanId() {
            return subscriptionPlanId;
        }

        @SerializedName("subscription_plan_id")
        public void setSubscriptionPlanId(String subscriptionPlanId) {
            this.subscriptionPlanId = subscriptionPlanId;
        }

        @SerializedName("stripe_account")
        public String getStripeAccount() {
            return stripeAccount;
        }

        @SerializedName("stripe_account")
        public void setStripeAccount(String stripeAccount) {
            this.stripeAccount = stripeAccount;
        }

        @SerializedName("birth_date")
        public String getBirthDate() {
            return birthDate;
        }

        @SerializedName("birth_date")
        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        @SerializedName("lat")
        public String getLat() {
            return lat;
        }

        @SerializedName("lat")
        public void setLat(String lat) {
            this.lat = lat;
        }

        @SerializedName("lng")
        public String getLng() {
            return lng;
        }

        @SerializedName("lng")
        public void setLng(String lng) {
            this.lng = lng;
        }

        @SerializedName("working")
        public String getWorking() {
            return working;
        }

        @SerializedName("working")
        public void setWorking(String working) {
            this.working = working;
        }

        @SerializedName("onorder")
        public String getOnorder() {
            return onorder;
        }

        @SerializedName("onorder")
        public void setOnorder(String onorder) {
            this.onorder = onorder;
        }

        @SerializedName("numorders")
        public String getNumorders() {
            return numorders;
        }

        @SerializedName("numorders")
        public void setNumorders(String numorders) {
            this.numorders = numorders;
        }

        @SerializedName("rejectedorders")
        public String getRejectedorders() {
            return rejectedorders;
        }

        @SerializedName("rejectedorders")
        public void setRejectedorders(String rejectedorders) {
            this.rejectedorders = rejectedorders;
        }

        @SerializedName("paypal_subscribtion_id")
        public String getPaypalSubscribtionId() {
            return paypalSubscribtionId;
        }

        @SerializedName("paypal_subscribtion_id")
        public void setPaypalSubscribtionId(String paypalSubscribtionId) {
            this.paypalSubscribtionId = paypalSubscribtionId;
        }

        @SerializedName("mollie_customer_id")
        public String getMollieCustomerId() {
            return mollieCustomerId;
        }

        @SerializedName("mollie_customer_id")
        public void setMollieCustomerId(String mollieCustomerId) {
            this.mollieCustomerId = mollieCustomerId;
        }

        @SerializedName("mollie_mandate_id")
        public String getMollieMandateId() {
            return mollieMandateId;
        }

        @SerializedName("mollie_mandate_id")
        public void setMollieMandateId(String mollieMandateId) {
            this.mollieMandateId = mollieMandateId;
        }

        @SerializedName("tax_percentage")
        public String getTaxPercentage() {
            return taxPercentage;
        }

        @SerializedName("tax_percentage")
        public void setTaxPercentage(String taxPercentage) {
            this.taxPercentage = taxPercentage;
        }

        @SerializedName("extra_billing_information")
        public String getExtraBillingInformation() {
            return extraBillingInformation;
        }

        @SerializedName("extra_billing_information")
        public void setExtraBillingInformation(String extraBillingInformation) {
            this.extraBillingInformation = extraBillingInformation;
        }

        @SerializedName("mollie_subscribtion_id")
        public String getMollieSubscribtionId() {
            return mollieSubscribtionId;
        }

        @SerializedName("mollie_subscribtion_id")
        public void setMollieSubscribtionId(String mollieSubscribtionId) {
            this.mollieSubscribtionId = mollieSubscribtionId;
        }

        @SerializedName("paystack_subscribtion_id")
        public String getPaystackSubscribtionId() {
            return paystackSubscribtionId;
        }

        @SerializedName("paystack_subscribtion_id")
        public void setPaystackSubscribtionId(String paystackSubscribtionId) {
            this.paystackSubscribtionId = paystackSubscribtionId;
        }

        @SerializedName("paystack_trans_id")
        public String getPaystackTransId() {
            return paystackTransId;
        }

        @SerializedName("paystack_trans_id")
        public void setPaystackTransId(String paystackTransId) {
            this.paystackTransId = paystackTransId;
        }

    }
}
