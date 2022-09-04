package com.example.bankapp.authenticate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bankapp.MainActivity;
import com.example.bankapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mlogin,notRegistered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mlogin = findViewById(R.id.login);
        mlogin.setOnClickListener(this);
        notRegistered = findViewById(R.id.notRegistered);
        notRegistered.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                break;
            case R.id.notRegistered:
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                finish();
                break;
        }
    }
}