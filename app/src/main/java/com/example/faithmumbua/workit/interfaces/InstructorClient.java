package com.example.faithmumbua.workit.interfaces;

import com.example.faithmumbua.workit.models.Instructor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InstructorClient
{
    @GET("showinstructors")
    Call<List<Instructor>> instructors();
}
