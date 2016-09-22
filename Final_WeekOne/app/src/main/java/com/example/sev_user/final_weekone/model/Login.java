package com.example.sev_user.final_weekone.model;

import com.example.sev_user.final_weekone.myinterface.LoginInterface;

/**
 * Created by toan on 14-Sep-16.
 */
public class Login {

    LoginInterface loginCallBack;

    final String adminName = "a";
    final String adminPassword = "";

    final String retailerName = "r";
    final String retailerPassword = "";

    public Login(LoginInterface loginInterface){
        this.loginCallBack = loginInterface;
    }

    public void isValidateAccount(String userName, String passWord) {
        if (userName.equals(adminName) && passWord.equals(adminPassword)) {
            loginCallBack.loginAsAdmin();
            return;
        }
        if (userName.equals(retailerName) && passWord.equals(retailerPassword)) {
            loginCallBack.loginAsRetailer();
            return;
        }
        loginCallBack.loginError();
    }

}
