package com.example.recipecheckpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Consumer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.recipecheckpoint.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btSignIn = findViewById(R.id.btSignIn);
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etEmail = findViewById(R.id.etEmailSignIn);
                EditText etPassword = findViewById(R.id.etPasswordSignIn);
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("Tous les champs sont requis")
                            .show();
                }

                User user = new User();
                user.setEmail(email);
                user.setPassword(password);

                VolleySingleton.getInstance(MainActivity.this).signIn(user, new Consumer<User>() {
                    @Override
                    public void accept(User user) {
                        UserSingleton.getInstance().setUser(user);
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

        Button btGoToSignUp = findViewById(R.id.btGoToSignUp);
        btGoToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSignUp = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(goToSignUp);
            }
        });
    }
}
