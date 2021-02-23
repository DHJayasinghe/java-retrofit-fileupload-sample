package javaretrofit;

import java.io.File;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javaretrofit.net.RetrofitClient;
import javaretrofit.net.UsingComplexModelResponse;
import javaretrofit.net.UsingSimpleModelResponse;
import javaretrofit.net.FileUploadService;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void uploadUsingComplexModel(String filePath) {
        RequestBody id = RequestBody.create(okhttp3.MultipartBody.FORM, "1");

        String[] data = new String[] { "John Doe", "Jane Doe", "Baby Doe" };

        MultipartBody.Part[] files = new MultipartBody.Part[data.length];
        Map<String, RequestBody> params = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            params.put(String.format("Files[%d].Id", i), RequestBody.create(okhttp3.MultipartBody.FORM, "1"));
            params.put(String.format("Files[%d].Name", i), RequestBody.create(okhttp3.MultipartBody.FORM, data[i]));
            files[i] = prepareFilePart(String.format("Files[%d].Somefile", i), filePath);
        }

        fileUploadApi.sendComplexModel(id, params, files).enqueue(new Callback<UsingComplexModelResponse>() {
            @Override
            public void onResponse(Call<UsingComplexModelResponse> call, Response<UsingComplexModelResponse> response) {
                UsingComplexModelResponse result = response.body();
                System.out.println("Success");
                for (String uploadedFile : result.uploadedFiles) {
                    System.out.println(uploadedFile);
                }
            }

            @Override
            public void onFailure(Call<UsingComplexModelResponse> call, Throwable thrwbl) {
                System.out.println("Error on uploading");
            }
        });

    }

    public void uploadUsingSimpleModel(String filePath) {
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
