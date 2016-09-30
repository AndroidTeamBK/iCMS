package com.example.sev_user.final_weekone.model;

import android.os.AsyncTask;
import android.util.Log;

import com.example.sev_user.final_weekone.myinterface.LoginInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by toan on 14-Sep-16.
 */
public class Login {

    LoginInterface loginCallBack;
    String userName = "";

    public Login(LoginInterface loginInterface) {
        this.loginCallBack = loginInterface;
    }

    public void isValidateAccount(String userName, String passWord) {
        new AsynTaskLogin().execute(userName, passWord);
    }

    class AsynTaskLogin extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            String name = params[0];
            String pass = params[1];
            Log.e("toan.tv", "name: " + name + " pass: " + pass);
            try {
                URL url = new URL(Utils.SERVER_URL + "api/v1/login");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                httpURLConnection.setRequestProperty("Accept", "application/json; charset=UTF-8");

                // json object to post value to server
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("email", name);
                jsonParam.put("password", pass);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(jsonParam.toString().getBytes("UTF-8"));
                outputStream.close();
                httpURLConnection.connect();


                int resultCode = httpURLConnection.getResponseCode();
                Log.e("toan.tv", "status: " + resultCode);
                if (resultCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null)
                        stringBuilder.append(line + "\n");
                    JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                    Log.e("toan.tv", "result login: " + stringBuilder.toString());
                    try {
                        JSONObject jsonData = jsonObject.getJSONObject("data");
                        JSONObject jsonUser = jsonData.getJSONObject("user");
                        userName = jsonUser.getString("name");
                        String userType = jsonUser.getString("type");
                        Utils.mToken = jsonData.getString("token");
                        switch (userType) {
                            case "admin":
                                return Utils.LOGIN_ADMIN;
                            case "retailer":
                                return Utils.LOGIN_RETAILER;
                            default:
                                return Utils.LOGIN_INVALID_ACCOUNT;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return Utils.LOGIN_INVALID_ACCOUNT;
                    }
                } else
                    return Utils.LOGIN_NETWORK_ERROR;
            } catch (IOException ee) {
                ee.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return Utils.LOGIN_NETWORK_ERROR;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.e("toan.tv", "return value: " + integer);
            switch (integer) {
                case Utils.LOGIN_ADMIN:
                    loginCallBack.loginAsAdmin(userName);
                    return;
                case Utils.LOGIN_RETAILER:
                    loginCallBack.loginAsRetailer(userName);
                    return;
                case Utils.LOGIN_INVALID_ACCOUNT:
                    loginCallBack.loginInvalidAccount();
                    return;
                case Utils.LOGIN_NETWORK_ERROR:
                    loginCallBack.loginNetworkError();
                    return;
                default:
                    loginCallBack.loginNetworkError();
                    return;
            }
        }
    }

}
