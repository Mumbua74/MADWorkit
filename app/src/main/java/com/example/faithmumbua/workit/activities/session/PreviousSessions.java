package com.example.faithmumbua.workit.activities.session;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.faithmumbua.workit.ApiController;
import com.example.faithmumbua.workit.R;
import com.example.faithmumbua.workit.interfaces.SessionClient;
import com.example.faithmumbua.workit.models.Session;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class PreviousSessions extends AppCompatActivity {

    private List<Session> sessionList = new ArrayList<>();
    private RecyclerView show_session;
    private RecyclerView.Adapter SessionAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prev_sessions);

        show_session = findViewById(R.id.show_session);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        show_session.setLayoutManager(mLayoutManager);
        show_session.setItemAnimator(new DefaultItemAnimator());

        SessionClient sessionClient = ApiController.createService(SessionClient.class);
        Call<List<Session>> showSessCall = sessionClient.sessions();
        showSessCall.enqueue(new Callback<List<Session>>() {
            @Override
            public void onResponse(@NonNull Call<List<Session>> call, @NonNull retrofit2.Response<List<Session>> response) {
                sessionList = response.body();
                Log.d("TAG", response.toString());
                SessionAdapter = new SessionAdapter(sessionList);
                show_session.setAdapter(SessionAdapter);
            }

            @Override
            public void onFailure(Call<List<Session>> call, Throwable t) {

                Log.d("ERROR", t.toString());

            }
        });



    }
}
