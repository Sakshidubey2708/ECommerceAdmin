package com.bytes.tech.awizom.ecommerceadmin.configure;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HelperApi extends AppCompatActivity {

    public static final class  PostSentPriceRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String PriceRequestId = params[0];
            String RequestUserId = params[1];
            String ProductId = params[2];
            String RequestStatus = params[3];
            String json = "";
            try {
                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/PriceRequestPost/"+ProductId);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                FormBody.Builder parameters = new FormBody.Builder();

                parameters.add("PriceRequestId", PriceRequestId);
                parameters.add("RequestUserId", RequestUserId);
                parameters.add("RequestDate", "");
                parameters.add("ProductId", ProductId);
                parameters.add("RequestStatus", RequestStatus);

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

    public static final class GetAllProductList extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder
                        ();
                builder.url(AppConfig.BASE_URL_API + "/GetProductsList");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
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

    public static final class  PostPriceRating extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String ProductRaitingId = params[0];
            String ProductId = params[1];
            String Raiting = params[2];
            String IsRaiting = params[3];
            String json = "";
            try {
                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/PriceRatingPost");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                FormBody.Builder parameters = new FormBody.Builder();

                parameters.add("ProductRaitingId", ProductRaitingId);
                parameters.add("ProductId", ProductId);
                parameters.add("Raiting", Raiting);
                parameters.add("IsRaiting", IsRaiting);

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

    public static final class GETPriceRatingGet extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            String ProductRaitingId = strings[0];
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GETPriceRatingGet/" + ProductRaitingId);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
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
    public static final class GetAllSubCategoriesList extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GetSubCategoriesList");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
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
    public static final class GetSubCategoriesListByMAinCAtID extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            String mID = strings[0];
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GetSelectedMainCatagories/" + mID);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
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


    public static final class GetChat extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            String userid = strings[0];
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GETChatingClient/"+ userid);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
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
    public static final class GetAllGetMainCategoriesList extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GetMainCategoriesList");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
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

    public static final class  PostChating extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String ClientUserId = params[0];
            String ChatContain = params[1];

            String json = "";
            try {
                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/ChatingClientPost/"+ClientUserId);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                FormBody.Builder parameters = new FormBody.Builder();

                parameters.add("ClientUserId", ClientUserId);
                parameters.add("AdminUserId", "Messages");
                parameters.add("RoleMessageBy", "Admin");
                parameters.add("ChatContain", ChatContain);

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
    public static final class  PostBuiltyUpload extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String Image = params[0];
            String BuiltyName = params[1];
            String BuiltyDetail = params[2];
            String json = "";
            try {
                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/PostUploadBuilty");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                FormBody.Builder parameters = new FormBody.Builder();

                parameters.add("UploadBuiltyID", "0");
                parameters.add("Image", Image);
                parameters.add("BuiltyName", BuiltyName);
                parameters.add("BuiltyDetail", BuiltyDetail);

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

    public static final class  PostClient extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String FirstName = params[0];
            String ContactNo = params[1];
            String Address = params[2];
            String EmailId = params[3];
            String Business = params[4];

            String json = "";
            try {
                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/AddClientPost");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                FormBody.Builder parameters = new FormBody.Builder();

                parameters.add("FirstName", FirstName);
                parameters.add("ContactNo", ContactNo);
                parameters.add("Address", Address);
                parameters.add("EmailId", EmailId);
                parameters.add("Business", Business);

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

    public static final class GETPriceRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            String id = strings[0];
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder
                        ();
                builder.url(AppConfig.BASE_URL_API + "/GETPriceRequest/"+ id);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
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

    public static final class GETUsers extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GETUsers");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
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

    public static final class GetSubscriberUsers extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            String subscribeID=strings[0];
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GetSubscriberUsers/"+subscribeID);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
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

    public static final class GetStockItems extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            String CreatedBy=strings[0];
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GetStockProduct/"+CreatedBy);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
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
