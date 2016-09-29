package com.example.sev_user.final_weekone.myinterface;

/**
 * Created by toan on 15-Sep-16.
 */
public interface LoginInterface {
    void loginAsAdmin(String name);
    void loginAsRetailer(String name);
    void loginInvalidAccount();
    void loginNetworkError();
}
