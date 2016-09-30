package com.example.sev_user.final_weekone.model;

import android.os.AsyncTask;
import android.util.Log;

import com.example.sev_user.final_weekone.ProductListActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by toan on 30-Sep-16.
 */
public class Logout {
    ProductListActivity productListActivity;

    public Logout(ProductListActivity main) {
        productListActivity = main;
    }

    public void exceute() {
        new AsynTaskLogout().execute();
    }

    class AsynTaskLogout extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {
            try {
                URL url = new URL(Utils.SERVER_URL + "api/v1/logout");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                String author = "Bearer " + Utils.mToken;
                httpURLConnection.setRequestProperty("Authorization", author);
                httpURLConnection.setRequestProperty("Accept", "application/json; charset=UTF-8");
                httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);


                // json object to post value to server
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("token", Utils.mToken);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                writer.write(jsonParam.toString()); // should be fine if my getQuery is encoded right yes?
                writer.flush();
                writer.close();
                outputStream.close();

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
                        JSONObject jsonMeta = jsonObject.getJSONObject("meta");
                        int userType = jsonMeta.getInt("status_code");
                        if (userType == 200)
                            return Utils.LOGOUT_SUCCESS;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return Utils.LOGOUT_ERROR;
                    }
                } else
                    return Utils.LOGOUT_ERROR;
            } catch (IOException ee) {
                ee.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return Utils.LOGOUT_ERROR;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.e("toan.tv", "return value: " + integer);
            switch (integer) {
                case Utils.LOGOUT_SUCCESS:
                    Log.e("toan.tv", "logout OK");
                    productListActivity.logoutSuccess();
                    return;
                case Utils.LOGOUT_ERROR:
                default:
                    Log.e("toan.tv", "logout ERROR");
                    productListActivity.logoutError();
                    return;
            }
        }
    }

}
