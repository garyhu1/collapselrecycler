package com.garyhu.citypickerdemo.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.garyhu.citypickerdemo.R;
import com.garyhu.citypickerdemo.widget.permission.BasePermissionFragment;
import com.garyhu.citypickerdemo.widget.permission.PermissionCallBackM;
import com.garyhu.citypickerdemo.widget.permission.easyPermission.EasyPermission;

/**
 * 作者： garyhu.
 * 时间： 2017/2/4.
 */

public class PermissinFragment extends BasePermissionFragment {

    private static final int RC_SMS_PERM = 122;

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Create view
        View v = inflater.inflate(R.layout.fragment_permission, container);

        // Button click listener
        v.findViewById(R.id.button_sms)
                .setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        smsTask();
                    }
                });

        return v;
    }

    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Do something after user returned from app settings screen. User may be
        // changed/updated the permissions. Let's check whether the user has changed sms
        // permission or not after returned from settings screen
        if (requestCode == EasyPermission.SETTINGS_REQ_CODE) {
            boolean hasReadSmsPermission = EasyPermission.hasPermissions(getContext(), Manifest.permission.READ_SMS);
            String hasReadSmsPermissionText = getString(R.string.has_read_sms_permission, hasReadSmsPermission);

            Toast.makeText(getContext(), hasReadSmsPermissionText, Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void smsTask() {
        // @formatter:off
        requestPermission(
                RC_SMS_PERM,
                new String[] { Manifest.permission.READ_SMS },
                getString(R.string.rationale_sms),
                new PermissionCallBackM() {
                    @Override public void onPermissionGrantedM(int requestCode, String... perms) {
                        Toast.makeText(getActivity(), "TODO: SMS Granted", Toast.LENGTH_SHORT).show();
                    }

                    @Override public void onPermissionDeniedM(int requestCode, String... perms) {
                        Toast.makeText(getActivity(), "TODO: SMS Denied", Toast.LENGTH_SHORT).show();
                    }
                });
        // @formatter:on
    }
}
