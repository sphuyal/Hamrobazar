package com.sandesh.hamrobazar.Api;

import com.sandesh.hamrobazar.Model.User;
import com.sandesh.hamrobazar.ServerResponse.ImageResponse;
import com.sandesh.hamrobazar.ServerResponse.RegisterResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UsersAPI {

    @POST("users/register")
    Call<RegisterResponse> registerUser(@Body User users);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @FormUrlEncoded
    @POST("users/login")
    Call<RegisterResponse> checkUser(@Field("email") String email, @Field("password") String password);


    @GET("users/me")
    Call<User> getUserDetails(@Header("Authorization") String token);



}
