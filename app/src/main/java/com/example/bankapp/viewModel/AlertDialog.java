package com.example.bankapp.viewModel;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.bankapp.MainActivity;

public class AlertDialog extends Dialog implements DialogInterface {
    public AlertDialog(@NonNull Context context) {
        super(context);
    }

}
