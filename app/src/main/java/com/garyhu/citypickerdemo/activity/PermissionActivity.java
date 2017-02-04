package com.garyhu.citypickerdemo.activity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.garyhu.citypickerdemo.R;
import com.garyhu.citypickerdemo.widget.permission.BasePermissionActivity;
import com.garyhu.citypickerdemo.widget.permission.PermissionCallBackM;

/**
 * 作者： garyhu.
 * 时间： 2017/2/4.
 * 权限申请界面
 */

public class PermissionActivity extends BasePermissionActivity {

    private static final int RC_CAMERA_PERM = 123;
    private static final int RC_LOCATION_CONTACTS_PERM = 124;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        // Button click listener that will request one permission.
        findViewById(R.id.button_camera).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                cameraTask();
            }
        });

        // Button click listener that will request two permissions.
        findViewById(R.id.button_location_and_wifi).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                locationAndContactsTask();
            }
        });
    }

    public void cameraTask() {
        requestPermission(RC_CAMERA_PERM, new String[] { Manifest.permission.CAMERA },
                getString(R.string.rationale_camera), new PermissionCallBackM() {
                    @Override public void onPermissionGrantedM(int requestCode, String... perms) {
                        Toast.makeText(PermissionActivity.this, "TODO: Camera Granted", Toast.LENGTH_LONG)
                                .show();
                    }

                    @Override public void onPermissionDeniedM(int requestCode, String... perms) {
                        onPermissionDenied(requestCode, perms);
                    }
                });

    }

    public void locationAndContactsTask() {
        requestPermission(RC_LOCATION_CONTACTS_PERM, new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_CONTACTS
        }, getString(R.string.rationale_location_contacts), new PermissionCallBackM() {
            @Override public void onPermissionGrantedM(int requestCode, String... perms) {
                Toast.makeText(PermissionActivity.this, "TODO: LOCATION Granted", Toast.LENGTH_LONG)
                        .show();

            }

            @Override public void onPermissionDeniedM(int requestCode, String... perms) {
                onPermissionDenied(requestCode, perms);

            }
        });
    }

    public void onPermissionDenied(int requestCode, String... perms) {
        Toast.makeText(PermissionActivity.this, "onPermissionDenied:" + requestCode + ":" + perms.length, Toast.LENGTH_SHORT)
                .show();
    }
}
