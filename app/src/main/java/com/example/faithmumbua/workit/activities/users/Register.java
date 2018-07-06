package com.example.faithmumbua.workit.activities.users;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.faithmumbua.workit.ApiController;
import com.example.faithmumbua.workit.R;
import com.example.faithmumbua.workit.interfaces.UserClient;
import com.example.faithmumbua.workit.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Register extends AppCompatActivity {

    private Button btn_reg;
    private EditText firstname, lastname, email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_reg = findViewById(R.id.btn_reg);

        firstname = findViewById(R.id.reg_fname);
        lastname = findViewById(R.id.reg_lname);
        email = findViewById(R.id.reg_email);
        password = findViewById(R.id.reg_pass);



        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserClient userClient = ApiController.createService(UserClient.class);
                Call<User> RegisterCall = userClient.register(
                        firstname.getText().toString(),
                        lastname.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString()
                );
                RegisterCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                        Log.d("TAG", "onResponse success:" + response.body().toString());
                        Toast toast = Toast.makeText(getApplicationContext(), "Successful Registration",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
                        toast.show();
                        Intent intent = new Intent(Register.this,MainActivity.class);
                        startActivity(intent);

                    }


                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
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
}
