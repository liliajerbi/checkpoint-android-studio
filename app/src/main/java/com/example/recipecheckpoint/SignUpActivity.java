package com.example.recipecheckpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button btSignUp = findViewById(R.id.btSignUp);
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHome = new Intent(SignUpActivity.this, HomeActivity.class);
                startActivity(goToHome);
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
