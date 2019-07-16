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

public class SignUpActivity extends AppCompatActivity {

    private UserSingleton userSingleton = UserSingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button btSignUp = findViewById(R.id.btSignUp);
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etUserName = findViewById(R.id.etUserNameSignUp);
                EditText etMail = findViewById(R.id.etEmailSignUp);
                EditText etPassword = findViewById(R.id.etPasswordSignUp);
                String userName = etUserName.getText().toString();
                String email = etMail.getText().toString();
                String password = etPassword.getText().toString();

                if (userName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    new AlertDialog.Builder(SignUpActivity.this)
                            .setMessage("Veuillez remplir tous les champs")
                            .show();
                }
                User user = new User();
                user.setUserName(userName);
                user.setEmail(email);
                user.setPassword(password);
                VolleySingleton.getInstance(SignUpActivity.this).signUp(user, new Consumer<User>() {
                    @Override
                    public void accept(User user) {
                        UserSingleton.getInstance().setUser(user);
                        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

        Button btGotoSingIn = findViewById(R.id.btGoToSignIn);
        btGotoSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSignIn = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(goToSignIn);
            }
        });
    }
}
