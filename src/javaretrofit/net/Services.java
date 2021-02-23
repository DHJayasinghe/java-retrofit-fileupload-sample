/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaretrofit.net;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javaretrofit.net.models.LoginResponses;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.Part;

/**
 *
 * @author Ezequiel
 */
public interface Services {
    @FormUrlEncoded
    @POST("signin")
    Call<LoginResponses> login(@Field("email") String email, @Field("password") String password);

    @Multipart
    @POST("using/model/simple")
    Call<GlobalResponse> sendSimpleModel(@Part MultipartBody.Part somefile, @Part("Id") int id,
            @Part("Name") String name);

    @Multipart
    @POST("using/model/complex")
    Call<GlobalResponse> sendComplexModel(@Part("Id") int id, @Part("Files") List<Map<String, RequestBody>> data);
}
