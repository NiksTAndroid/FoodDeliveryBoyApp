package com.prometteur.fooddeliveryboy.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.prometteur.fooddeliveryboy.Activities.LoginActivity;
import com.prometteur.fooddeliveryboy.R;

import java.io.File;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CommonMethods {

    public static void loadImage(ImageView imageView, String url) {
        if (url == null || url.isEmpty()) {
            imageView.setImageDrawable(imageView.getContext().getResources().getDrawable(R.drawable.sampleprofile));
            return;
        }
        /*if (!url.startsWith(ConstantStrings.imagesBaseURL)) {
            url = Constants.imagesBaseURL + url;
        }*/
        Glide.with(imageView.getContext()).load(url)
                .skipMemoryCache(true)
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    public static RequestBody toRequestBody(String st) {
        if (st == null) {
            return null;
        } else {
            return RequestBody.create(MediaType.parse("text/plain"), st);
        }
    }

    public static MultipartBody.Part prepareFilePart(String partName, File file) {
        if (file == null) {
            return null;
        } else {
            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
            return MultipartBody.Part.createFormData(partName, file.getName(), requestBody);
        }
    }

    public static void logoutDueToSession(Context nContext){
        Preference.clearAllPref(nContext);
        Intent intent = new Intent(nContext, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        nContext.startActivity(intent);

    }

    public static void setLocale(Activity activity, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }


    public static boolean languageChange(){
        String lang_code;
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = Resources.getSystem().getConfiguration().getLocales().get(0);
            lang_code=locale.toString();
        } else {
            //noinspection deprecation
            locale = Resources.getSystem().getConfiguration().locale;
            lang_code=locale.toString();
        }
        if(lang_code.contains("fr")|| lang_code.contains("FR")){
            return true;
        }
        else {
            return false;
        }
    }

    public static void setLanguageForApp(String lang, Context context){

        LocaleHelper.setLocale(context, lang);

        Resources activityRes = context.getResources();
        Configuration activityConf = activityRes.getConfiguration();
        Locale newLocale = new Locale(lang)
                ;
        activityConf.setLocale(newLocale);
        activityRes.updateConfiguration(activityConf, activityRes.getDisplayMetrics());

        Resources applicationRes = context.getResources();
        Configuration applicationConf = applicationRes.getConfiguration();
        applicationConf.setLocale(newLocale);
        applicationRes.updateConfiguration(applicationConf,
                applicationRes.getDisplayMetrics());


    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showFailToast(Context context, String msg) {
        // Toasty.error(context, msg, Toast.LENGTH_SHORT, true).show();
        Toasty.custom(context, msg, context.getDrawable(R.drawable.ic_clear_white_24dp), context.getResources().getColor(R.color.red), context.getResources().getColor(R.color.white), Toast.LENGTH_SHORT, true, true).show();
    }

    public static void showFailToast(Context context, int msg) {
        // Toasty.error(context, msg, Toast.LENGTH_SHORT, true).show();
        Toasty.custom(context, context.getResources().getString(msg), context.getDrawable(R.drawable.ic_clear_white_24dp), context.getResources().getColor(R.color.red), context.getResources().getColor(R.color.white), Toast.LENGTH_SHORT, true, true).show();
    }

    public static void showSuccessToast(Context context, String msg) {
        Toasty.success(context, msg, Toast.LENGTH_SHORT, true).show();
        Toasty.custom(context, msg, context.getDrawable(R.drawable.ic_check_white_24dp), context.getResources().getColor(R.color.green_03b), context.getResources().getColor(R.color.white), Toast.LENGTH_SHORT, true, true).show();
    }

    public static void showInfoToast(Context context, String msg) {
        Toasty.info(context, msg, Toast.LENGTH_SHORT, true).show();

    }

    public static void showInfoToast(Context context, int msg) {
        Toasty.info(context, context.getResources().getString(msg), Toast.LENGTH_SHORT, true).show();

    }


    public static String getPath (Context nContext, Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = nContext.getContentResolver().query(uri,
                projection,
                null,
                null,
                null);
        if (cursor == null)
            return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s = cursor.getString(column_index);
        cursor.close();
        return s;


    }

}
