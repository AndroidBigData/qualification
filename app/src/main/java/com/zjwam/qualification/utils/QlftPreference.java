package com.zjwam.qualification.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class QlftPreference {
    private static final String LOGIN_NAME = "loginName"; //登录名
    private static final String PASSWORD = "password";  //密码
    private static final String IS_FLAG = "isFlag"; //是否已经登录
    private static final String ISREFRESH = "isRefresh"; //记录是否刷新页面，用于传值比较麻烦的页面
    private static final String UID = "uid"; //记录用户id
    private static final String VIDEO_TIME = "videoTime";//记录播放时长
    private static final String VIDEO_ID = "videoId";//记录当前播放那个的章节id
    private static final String SITE = "site";

    private static QlftPreference preference = null;
    private SharedPreferences sharedPreference;
    private String packageName = "";


    public static synchronized QlftPreference getInstance(Context context){
        if(preference == null)
            preference = new QlftPreference(context);
        return preference;
    }


    public QlftPreference(Context context){
        packageName = context.getPackageName() + "_preferences";
        sharedPreference = context.getSharedPreferences(
                packageName, Context.MODE_PRIVATE);
    }

    public String getLoginName(){
        String loginName = sharedPreference.getString(LOGIN_NAME, "");
        return loginName;
    }


    public void SetLoginName(String loginName){
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(LOGIN_NAME, loginName);
        editor.commit();
    }


    public String getPassword(){
        String password = sharedPreference.getString(PASSWORD, "");
        return password;
    }


    public void SetPassword(String password){
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(PASSWORD, password);
        editor.commit();
    }



    public boolean IsFlag(){
        Boolean isFlag = sharedPreference.getBoolean(IS_FLAG, false);
        return isFlag;
    }


    public void SetIsFlag(Boolean isFlag){
        SharedPreferences.Editor edit = sharedPreference.edit();
        edit.putBoolean(IS_FLAG, isFlag);
        edit.commit();
    }

    public String getUid(){
        String uid = sharedPreference.getString(UID, "");
        return uid;
    }
    public void SetUid(String uid){
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(UID, uid);
        editor.commit();
    }

    public boolean IsRefresh(){
        Boolean isRefresh = sharedPreference.getBoolean(ISREFRESH, false);
        return isRefresh;
    }

    public void setIsRefresh(Boolean isRefresh){
        SharedPreferences.Editor edit = sharedPreference.edit();
        edit.putBoolean(ISREFRESH, isRefresh);
        edit.commit();
    }

    public String getViteoTime() {
        String time = sharedPreference.getString(VIDEO_TIME,"");
        return time;
    }

    public void setViteoTime(String time){
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(VIDEO_TIME, time);
        editor.commit();
    }
    public String getVideoId(){
        String id = sharedPreference.getString(VIDEO_ID,"");
        return id;
    }
    public void setVideoId (String id){
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(VIDEO_ID, id);
        editor.commit();
    }
    public String getSite(){
        String id = sharedPreference.getString(SITE,"");
        return id;
    }
    public void setSite (String site){
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(SITE, site);
        editor.commit();
    }
}
