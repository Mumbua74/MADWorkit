package com.example.faithmumbua.workit.activities.users;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faithmumbua.workit.ApiController;
import com.example.faithmumbua.workit.R;
import com.example.faithmumbua.workit.activities.session.Main2Activity;
import com.example.faithmumbua.workit.interfaces.UserClient;
import com.example.faithmumbua.workit.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btn_login;
    private EditText email, password;
    private TextView tv_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = findViewById(R.id.btn_login);
        tv_register = findViewById(R.id.tv_register);
        email = findViewById(R.id.username_login);
        password = findViewById(R.id.password_login);


        btn_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                UserClient userClient = ApiController.createService(UserClient.class);
                Call<User> LoginCall = userClient.login(
                        email.getText().toString(),
                        password.getText().toString()
                );
                LoginCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                        Log.d("TAG", "onResponse success:" + response.body().toString());
                        Toast toast = Toast.makeText(getApplicationContext(), "Successful Login",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                        toast.show();
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intent);

                    }


                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("TAG", t.toString());
                        Toast toast = Toast.makeText(getApplicationContext(), "Try again",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                });

            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
    }
}
