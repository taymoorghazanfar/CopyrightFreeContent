package com.aeperfect.cfc.ui.widgets;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.aeperfect.cfc.R;

public class DialogAbout {

    public static void show(Context context) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();

        final View dialogView = layoutInflater.inflate(R.layout.dialog_about, null);
        builder.setView(dialogView);
        final AlertDialog aboutDialog = builder.create();
        aboutDialog.setCancelable(false);

        Button buttonOk = dialogView.findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(view -> aboutDialog.dismiss());

        aboutDialog.show();
    }
}
