package javaretrofit;

import java.io.File;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javaretrofit.net.RetrofitClient;
import javaretrofit.net.FileUploadService;
import javaretrofit.net.UsingSimpleModelResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javaretrofit.net.GlobalResponse;

public class JavaRetrofit {
    private FileUploadService fileUploadApi;
    List<MultipartBody.Part> parts = new ArrayList<>();

    public JavaRetrofit() {
        fileUploadApi = RetrofitClient.getAPIService();
    }

    private MultipartBody.Part prepareFilePart(String partName, String fileUri) {
        File file = new File(fileUri);
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse(mimeType), file);
        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    private RequestBody prepareFile(String fileUri) {
        File file = new File(fileUri);
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        return RequestBody.create(MediaType.parse(mimeType), file);
    }

    public void uploadUsingComplexModel(String filePath) {
        RequestBody id = RequestBody.create(okhttp3.MultipartBody.FORM, "1");
        ArrayList<MultipartBody.Part> parts = new ArrayList<>()[1];
        parts.add(prepareFilePart("Somefile", filePath));

        // Map<String, RequestBody> map = new HashMap<>();
        // map.put("Somefile", prepareFile(filePath));
        // map.put("Id", RequestBody.create(MediaType.parse("multipart/form-data"), "1"));
        // map.put("Name", RequestBody.create(MediaType.parse("multipart/form-data"), "Jane Doe"));

        // myMap.add(0, map);

        fileUploadApi.sendComplexModel(id, parts).enqueue(new Callback<GlobalResponse>() {
            @Override
            public void onResponse(Call<GlobalResponse> call, Response<GlobalResponse> rspns) {
                GlobalResponse res = rspns.body();
                System.out.println("Success");
                // throw new UnsupportedOperationException("Not supported yet."); // To change
                // body of generated methods,
                // // choose Tools | Templates.
            }

            @Override
            public void onFailure(Call<GlobalResponse> call, Throwable thrwbl) {
                System.out.println("Error on uploading");
            }
        });

    }

    public void uploadUsingSimpleModel(String filePath) {
        RequestBody someFile = prepareFile(filePath);
        RequestBody id = RequestBody.create(okhttp3.MultipartBody.FORM, "1");
        RequestBody name = RequestBody.create(okhttp3.MultipartBody.FORM, "Dhanuka");

        fileUploadApi.sendSimpleModel(someFile, id, name).enqueue(new Callback<UsingSimpleModelResponse>() {
            @Override
            public void onResponse(Call<UsingSimpleModelResponse> call, Response<UsingSimpleModelResponse> response) {
                UsingSimpleModelResponse result = response.body();
                System.out.println("Upload complete");
                System.out.println(result.filename);
            }

            @Override
            public void onFailure(Call<UsingSimpleModelResponse> call, Throwable thrwbl) {
                System.out.println("Error on uploading");
            }
        });
    }

    public void uploadUsingSimpleModel2(String filePath) {
        MultipartBody.Part filepart = prepareFilePart("Somefile", filePath);
        RequestBody id = RequestBody.create(okhttp3.MultipartBody.FORM, "1");
        RequestBody name = RequestBody.create(okhttp3.MultipartBody.FORM, "Dhanuka");

        fileUploadApi.sendSimpleModel(filepart, id, name).enqueue(new Callback<UsingSimpleModelResponse>() {
            @Override
            public void onResponse(Call<UsingSimpleModelResponse> call, Response<UsingSimpleModelResponse> response) {
                UsingSimpleModelResponse result = response.body();
                System.out.println("Upload complete");
                System.out.println(result.filename);
            }

            @Override
            public void onFailure(Call<UsingSimpleModelResponse> call, Throwable thrwbl) {
                System.out.println("Error on uploading");
            }
        });
    }
}
