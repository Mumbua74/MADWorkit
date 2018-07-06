package com.example.faithmumbua.workit.activities.session;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.faithmumbua.workit.ApiController;
import com.example.faithmumbua.workit.R;
import com.example.faithmumbua.workit.interfaces.SessionClient;
import com.example.faithmumbua.workit.models.Session;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSession extends AppCompatActivity implements DatePickerDialog.OnDateSetListener
{

    Button btnDate;
    TextView date;
    private Button sess_save;
    private EditText exercise_name,rep_number,location_name;

    Calendar c;
    int day,month,year;
    int dayFinal,monthFinal,yearFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessions);

        btnDate = (Button)findViewById(R.id.btnDate);
        sess_save = findViewById(R.id.sess_save);
        exercise_name = findViewById(R.id.sess_exerciseName);
        rep_number = findViewById(R.id.sess_reps);
        location_name = findViewById(R.id.sess_location);
        date = findViewById(R.id.datefetch);


        date = (TextView)findViewById(R.id.datefetch);


        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                c= Calendar.getInstance();
                day=c.get(Calendar.DAY_OF_MONTH);
                month=c.get(Calendar.MONTH);
                year=c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddSession.this, AddSession.this, year, month, day);
                datePickerDialog.show();
            }
        });

        sess_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionClient sessionClient = ApiController.createService(SessionClient.class);
                Call<Session> addSessCall = sessionClient.save(
                        exercise_name.getText().toString(),
                        rep_number.getText().toString(),
                        location_name.getText().toString(),
                        date.getText().toString()
                );
                addSessCall.enqueue(new Callback<Session>() {
                    @Override
                    public void onResponse(@NonNull Call<Session> call, @NonNull Response<Session> response) {
                        Log.d("TAG", "onResponse success:" + response.body().toString());
                        Toast toast = Toast.makeText(getApplicationContext(), "Session Added Successfully",
                                Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
                        toast.show();
                        Intent intent = new Intent(AddSession.this,PreviousSessions.class);
                        startActivity(intent);

                    }


                    @Override
                    public void onFailure(Call<Session> call, Throwable t) {
                        Log.d("TAG", t.toString());
                        Toast toast = Toast.makeText(getApplicationContext(), "Try again",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                });

            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2)
    {
        dayFinal = i;
        monthFinal = i1 + 1;
        yearFinal = i2;


        date.setText(
                yearFinal +":"+
                        monthFinal+":"+
                        +dayFinal

        );

    }


}
