package com.aeperfect.cfc.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.aeperfect.cfc.R;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 69;
    private static final long SPLASH_DURATION = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        requestPermissions();
    }

    private void requestPermissions() {

        int camera = ContextCompat
                .checkSelfPermission(this, android.Manifest.permission.CAMERA);
        int storageWrite = ContextCompat
                .checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int storageRead = ContextCompat
                .checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        List<String> requestedPermissions = new ArrayList<>();

        if (camera != PackageManager.PERMISSION_GRANTED) {
            requestedPermissions.add(android.Manifest.permission.CAMERA);
        }
        if (storageWrite != PackageManager.PERMISSION_GRANTED) {
            requestedPermissions.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (storageRead != PackageManager.PERMISSION_GRANTED) {
            requestedPermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!requestedPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(this, requestedPermissions.toArray
                    (new String[0]), PERMISSION_REQUEST_CODE);
        }
        else{

            startSplash();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST_CODE) {

            boolean permissionsGranted = true;

            if (grantResults.length > 0) {

                for (int grantResult : grantResults) {

                    if (grantResult != PackageManager.PERMISSION_GRANTED) {

                        permissionsGranted = false;
                        break;
                    }
                }

                if (permissionsGranted) {

                    startSplash();

                } else {

                    showErrorDialog();
                }
            } else {

                showErrorDialog();
            }
        }
    }

    private void startSplash() {

        Thread splashThread = new Thread() {

            @Override
            public void run() {
                try {
                    sleep(SPLASH_DURATION);
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        splashThread.start();
    }

    private void showErrorDialog() {

        AlertDialog.Builder alert;
        alert = new AlertDialog.Builder(this);
        alert.setTitle("Permissions Denied");
//        alert.setIcon(R.drawable.about_icon);
        alert.setMessage("Requested permissions are necessary to use this application. Try again later");

        alert.setPositiveButton("OK", (dialog, whichButton) -> finishAffinity());

        alert.show();
    }
}