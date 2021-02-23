/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaretrofit;

import java.io.File;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javaretrofit.net.RetrofitClient;
import javaretrofit.net.Services;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javaretrofit.net.GlobalResponse;

/**
 *
 * @author Ezequiel
 */
public class JavaRetrofit {
    List<MultipartBody.Part> parts = new ArrayList<>();

    File mCurrentPhotoPath = new File("C:\\Users\\Dhanuka Jayasinghe\\Downloads\\shipment_registry_before.png");
    String path = "C:\\Users\\Dhanuka Jayasinghe\\Downloads\\shipment_registry_before.png";

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

    public void addtoArray() {
        parts.add(prepareFilePart("photo", mCurrentPhotoPath.getAbsolutePath()));
    }

    public void upload() {
        Services api = RetrofitClient.getAPIService();

        // MultipartBody.Part filepart = prepareFilePart("Somefile", mCurrentPhotoPath.getAbsolutePath());

        List<Map<String, RequestBody>> myMap = new ArrayList<Map<String, RequestBody>>();

        Map<String, RequestBody> map = new HashMap<>();
        map.put("Somefile", prepareFile(mCurrentPhotoPath.getAbsolutePath()));
        map.put("Id", RequestBody.create(MediaType.parse("multipart/form-data"), "1"));
        map.put("Name", RequestBody.create(MediaType.parse("multipart/form-data"), "Jane Doe"));

        myMap.add(0, map);

        api.sendComplexModel(1, myMap).enqueue(new Callback<GlobalResponse>() {
            @Override
            public void onResponse(Call<GlobalResponse> call, Response<GlobalResponse> rspns) {
                GlobalResponse r = rspns.body();
                System.out.println("Success");
                // throw new UnsupportedOperationException("Not supported yet."); // To change
                // body of generated methods,
                // // choose Tools | Templates.
            }

            @Override
            public void onFailure(Call<GlobalResponse> call, Throwable thrwbl) {

                throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods,
                                                                               // choose Tools | Templates.
            }
        });

    }

}
