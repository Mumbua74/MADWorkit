package com.example.faithmumbua.workit.interfaces;


import com.example.faithmumbua.workit.models.Session;
import com.example.faithmumbua.workit.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SessionClient
{
    @GET("/show_session")
    Call<List<Session>> sessions();


    @FormUrlEncoded
    @POST("/save_session")
    Call<Session> save
            (
                    @Field("exercise_name") String exercise_name,
                    @Field("rep_number") String rep_number,
                    @Field("location_name") String location_name,
                    @Field("date") String date
            );
}
