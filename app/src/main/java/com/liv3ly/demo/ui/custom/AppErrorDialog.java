package com.liv3ly.demo.ui.custom;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import androidx.annotation.StringRes;

import com.liv3ly.demo.databinding.DialogAppErrorBinding;

public class AppErrorDialog {

    private DialogAppErrorBinding binding;

    private AlertDialog alertDialog = null;

    private static AppErrorDialog instance;

    private Context context;

    public static AppErrorDialog getInstance(Context context) {
        if (instance != null) {
            return instance;
        }
        instance = new AppErrorDialog(context);
        return instance;
    }

    public AppErrorDialog(Context context) {
        this.context = context;
        if (context == null || (alertDialog != null && alertDialog.isShowing())) {
            return;
        }
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DialogAppErrorBinding.inflate(inflater);

        View view = binding.getRoot();
        dialogBuilder.setView(view);
        dialogBuilder.setCancelable(true);
        alertDialog = dialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding.btnOk.setOnClickListener(v -> {
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
        });
    }

    public void show(String message) {
        binding.tvContent.setText(message);
        if (alertDialog != null && !alertDialog.isShowing()) {
            alertDialog.show();
        }
    }

    public void show(@StringRes int messageID) {
        binding.tvContent.setText(context.getResources().getString(messageID));
        if (alertDialog != null && !alertDialog.isShowing()) {
            alertDialog.show();
        }
    }

    public void onDestroy() {
        instance = null;
    }
}
