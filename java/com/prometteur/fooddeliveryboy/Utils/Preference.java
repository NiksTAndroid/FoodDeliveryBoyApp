package com.prometteur.fooddeliveryboy.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.prometteur.fooddeliveryboy.PojoModels.LoginUserDetailsPojo;

import java.util.List;

public class Preference {

    public static boolean isLogin(Context context){
        SharedPreferences prefs=context.getSharedPreferences(StringConstants.PREFNAME,
                Context.MODE_PRIVATE);
        if(prefs.getBoolean(StringConstants.ISLOGGEDIN,false)){
            return true;
        }else {
            return false;
        }
    }

    public static String getPrefData(Context nContext,String Key){
        SharedPreferences prefs=nContext.getSharedPreferences(StringConstants.PREFNAME,
                Context.MODE_PRIVATE);
        if(prefs.getString(Key,"")!=null){
            return prefs.getString(Key,"");
        }else {
            return "";
        }

    }

    public static String userDetails(Context context, LoginUserDetailsPojo.Result response, boolean islogin) {

        SharedPreferences prefs = context.getSharedPreferences(StringConstants.PREFNAME,
                Context.MODE_PRIVATE);
        if (response == null) {
            return prefs.getString("USERDETAILS", null);
        } else {
            SharedPreferences.Editor edit = prefs.edit();
            Gson gson = new Gson();
            String st = gson.toJson(response);
            edit.putString(StringConstants.USERDETAILS, st);
            edit.putString(StringConstants.USERID, response.getId());
            edit.putBoolean(StringConstants.ISLOGGEDIN,islogin);
            edit.apply();
            return null;
        }

    }


    public static String languageCode(Context context,String langcode){
        SharedPreferences prefs = context.getSharedPreferences(StringConstants.PREFNAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString(StringConstants.LANGUAGECODE,langcode);
        edit.apply();
        return null;
    }

    public static void clearAllPref(Context context) {
        SharedPreferences preferences =context.getSharedPreferences(StringConstants.PREFNAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(StringConstants.USERDETAILS);
        editor.remove(StringConstants.USERID);
        editor.remove(StringConstants.ISLOGGEDIN);

        editor.commit();
    }
}
