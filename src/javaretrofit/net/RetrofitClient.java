package javaretrofit.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://localhost:81/api/fileupload/";

    private RetrofitClient() {
    }

    private static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            final OkHttpClient client = new OkHttpClient.Builder().build();

            retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl)
                    .client(client).build();
        }
        return retrofit;
    }

    public static FileUploadService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(FileUploadService.class);
    }
}
