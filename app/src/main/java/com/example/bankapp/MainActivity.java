 package com.example.bankapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bankapp.viewModel.MpesaViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mbtn;
    private MpesaViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbtn = findViewById(R.id.activate);
        mbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.activate) {
            Activate();
        }
    }

    private void Activate() {
        viewModel = ViewModelProviders.of(this).get(MpesaViewModel.class);
        viewModel.makeApiCall();
        showDialog();
    }

    public void showDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Response");
        alert.setMessage("Your Request has been Received, You will get a confirmation within 24hrs!!");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alert.create().show();
    }
}