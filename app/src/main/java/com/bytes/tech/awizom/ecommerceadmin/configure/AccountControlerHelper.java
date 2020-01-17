package com.bytes.tech.awizom.ecommerceadmin.configure;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class AccountControlerHelper extends AppCompatActivity {

    public static final class  PostLogin extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {


            String UserName = params[0];
            String Password = params[1];

            String json = "";
            try {
                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/LogIn");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                FormBody.Builder parameters = new FormBody.Builder();

                parameters.add("UserName", UserName);
                parameters.add("Password", Password);
                builder.post(parameters.build());
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;

        }
        protected void onPostExecute(String result) {

            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
