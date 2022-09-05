 package com.example.bankapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bankapp.viewModel.MpesaViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mbtn;
    private MpesaViewModel viewModel;
    String m_Text;
    EditText mphone;
    private static String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbtn = findViewById(R.id.activate);
        mbtn.setOnClickListener(this);
        mphone = findViewById(R.id.phoneNumber);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.activate) {
            Activate();
        }
    }

    private void Activate() {
        String no = mphone.getText().toString();
        if (TextUtils.isEmpty(no)){
            mphone.setError("Email is required");
            mphone.requestFocus();
            return;
        }
        String number = "254"+no.substring(1,10);
        Toast.makeText(this, "The number is: "+number, Toast.LENGTH_SHORT).show();
        viewModel = ViewModelProviders.of(this).get(MpesaViewModel.class);
        viewModel.makeApiCall(number);
        showDialog();
    }

    public void showDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Response");
        alert.setMessage("Your Request has been Received Pay & You will get a confirmation within 24hrs!!");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alert.create().show();
    }
}