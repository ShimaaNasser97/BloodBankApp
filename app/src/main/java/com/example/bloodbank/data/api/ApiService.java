package com.example.bloodbank.data.api;

import android.app.Notification;

import com.example.bloodbank.data.model.donation.Donation;
import com.example.bloodbank.data.model.generalResponse.GeneralResponse;
import com.example.bloodbank.data.model.login.Login;
import com.example.bloodbank.data.model.new_password.NewPassword;
import com.example.bloodbank.data.model.notifications.Notifications;
import com.example.bloodbank.data.model.posts.Posts;
import com.example.bloodbank.data.model.reset_password.ResetPassword;
import com.example.bloodbank.data.model.settings.Settings;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("signup")
    @FormUrlEncoded
    Call<Login> getSignup(@Field("name") String name,
                          @Field("email") String email,
                          @Field("birth_date") String birth_date,
                          @Field("city_id") int city_id,
                          @Field("phone") String phone,
                          @Field("donation_last_date") String donation_last_date,
                          @Field("password") String password,
                          @Field("password_confirmation") String password_confirmation,
                          @Field("blood_type_id") int blood_type_id);

    @POST("login")
    @FormUrlEncoded
    Call<Login> getLogin(@Field("phone") String phone,
                         @Field("password") String password);

    @GET("governorates")
    Call<GeneralResponse> getGavervment();

    @GET("cities")
    Call<GeneralResponse> getCites(@Query("governorate_id") int gavernmentId);

    @GET("blood-types")
    Call<GeneralResponse> getPloodTypes();

    @GET("categories")
    Call<GeneralResponse> getCategories();

    @GET("posts")
    Call<Posts> getPosts(@Query("api_token") String apiToken,
                         @Query("page") int page);

    @GET("posts")
    Call<Posts> getPostsFilter(@Query("api_token") String apiToken,
                               @Query("page") int page,
                               @Query("keyword") String keyword,
                               @Query("category_id") int categoryId);

    @GET("post")
    Call<Posts> getPostDetails(@Query("api_token") String apiToken,
                               @Query("post_id") int postId,
                               @Query("page") int page);

    @GET("my-favourites")
    Call<Posts> getMyFavourites(@Query("api_token") String apiToken,
                                @Query("page") int page);


    @GET("donation-requests")
    Call<Donation> getDonationRequests(@Query("api_token") String apiToken,
                                       @Query("blood_type_id") int bloodTypeId,
                                       @Query("city_id") int cityId,
                                       @Query("page") int page);

    @GET("donation-requests")
    Call<Donation> getDonationRequests(@Query("api_token") String apiToken,

                                       @Query("page") int page);

    @POST("notifications-settings")
    @FormUrlEncoded
    Call<GeneralResponse> getNotificationsSettings(@Field("api_token") String apiToken);


    @GET("settings")
    Call<Settings> getSettings(@Query("api_token") String apiToken);

    @POST("contact")
    @FormUrlEncoded
    Call<GeneralResponse> getConect(@Field("api_token") String apiToken,
                                    @Field("title") String Title,
                                    @Field("message") String Message);

    @POST("donation-request/create")
    @FormUrlEncoded
    Call<GeneralResponse> getCreate(@Field("api_token") String apiToken,
                                    @Field("patient_name") String patientName,
                                    @Field("patient_age") String patientAge,
                                    @Field("blood_type_id") int bloodTypeId,
                                    @Field("bags_num") String bagsNum,
                                    @Field("hospital_name") String hospitalName,
                                    @Field("hospital_address") String hospitalAddress,
                                    @Field("city_id") int cityId,
                                    @Field("phone") String phone,
                                    @Field("notes") String notes,
                                    @Field("latitude") Double latitude,
                                    @Field("longitude") Double longitude);

@POST("reset-password")
    @FormUrlEncoded
    Call<ResetPassword> getResetPassword(@Field("phone") String phone);

    @POST("new-password")
    @FormUrlEncoded
    Call<NewPassword> getNewPassword(@Field("password") String password,
                                     @Field("password_confirmation") String passwordConfirmation,
                                     @Field("pin_code") String pinCode,
                                     @Field("phone") String phone);

    @GET("notifications")
    Call<Notifications> getNotification(@Query("api_token") String apitoken,
                                        @Query("page") int page);


}
