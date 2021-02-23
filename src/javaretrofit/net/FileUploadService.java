package javaretrofit.net;

import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface FileUploadService {
        @Multipart
        @POST("using/model/simple")
        Call<UsingSimpleModelResponse> sendSimpleModel(@Part MultipartBody.Part somefile, @Part("Id") RequestBody id,
                        @Part("Name") RequestBody name);

        @Multipart
        @POST("using/model/simple")
        Call<UsingSimpleModelResponse> sendSimpleModel(@Part("Somefile") RequestBody somefile,
                        @Part("Id") RequestBody id, @Part("Name") RequestBody name);

        @Multipart
        @POST("using/model/complex")
        Call<UsingComplexModelResponse> sendComplexModel(@Part("Id") RequestBody id,
                        @PartMap Map<String, RequestBody> data, @Part MultipartBody.Part[] attachment);
}
