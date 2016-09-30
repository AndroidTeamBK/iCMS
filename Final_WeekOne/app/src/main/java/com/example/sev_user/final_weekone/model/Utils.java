package com.example.sev_user.final_weekone.model;

/**
 * Created by toan on 14-Sep-16.
 */
public class Utils {
    public static String mToken = "";
    public static final String SERVER_URL = "http://icms-dev.tk/";
    public static final int LOGIN_ADMIN = 1;
    public static final int LOGIN_RETAILER = 2;
    public static final int LOGIN_INVALID_ACCOUNT = 3;
    public static final int LOGIN_NETWORK_ERROR = 4;

    public static final int LOGOUT_SUCCESS = 5;
    public static final int LOGOUT_ERROR = 6;


    public static void setToken(String token){
        mToken = token;
    }

}
