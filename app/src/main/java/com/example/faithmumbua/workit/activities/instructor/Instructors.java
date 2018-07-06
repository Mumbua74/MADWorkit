package com.example.faithmumbua.workit.activities.instructor;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.faithmumbua.workit.ApiController;
import com.example.faithmumbua.workit.R;
import com.example.faithmumbua.workit.interfaces.InstructorClient;
import com.example.faithmumbua.workit.models.Instructor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Instructors extends AppCompatActivity
{
    private List<Instructor> instructorList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter instructorAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructors);

        recyclerView = findViewById(R.id.recyclerView);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        InstructorClient instructorClient = ApiController.createService(InstructorClient.class);
        Call<List<Instructor>> instructorCall = instructorClient.instructors();
        instructorCall.enqueue(new Callback<List<Instructor>>() {
            @Override
            public void onResponse(@NonNull Call<List<Instructor>> call, @NonNull retrofit2.Response<List<Instructor>> response) {
                instructorList = response.body();
                Log.d("TAG", response.toString());
                instructorAdapter = new InstructorAdapter(instructorList);
                recyclerView.setAdapter(instructorAdapter);
            }

            @Override
            public void onFailure(Call<List<Instructor>> call, Throwable t) {

                Log.d("ERROR", t.toString());

            }
        });

    }
}
