package com.ilya4.beerplease.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity;
import com.ilya4.beerplease.presentation.view.fragment.base.BaseDialogFragment;
import com.ilya4.beerplease.presentation.view.fragment.base.BaseFragment;


import static android.Manifest.permission.*;
import static com.ilya4.beerplease.presentation.app.Constants.*;

public class PermissionHelper {
    private static final String TAG = "PermissionHelper";

    public static boolean checkGranted(int[] grantResults) {
        for (int grantResult : grantResults)
            if (grantResult == PackageManager.PERMISSION_DENIED) return false;
        return true;
    }

    public static void requestLocationPermission(BaseActivity activity) {
        Log.d(TAG, "requestLocationPermission: ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(new String[]{ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
    }


    public static boolean mayRequestPlace(BaseActivity activity) {
        Log.d(TAG, "mayRequestPlace: ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                return true;
            }
            if (activity.shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                requestLocationPermission(activity);
            } else {
                requestLocationPermission(activity);
            }
        } else {
            return true;
        }
        return false;
    }

    public static boolean mayRequestCamera(BaseActivity activity, BaseFragment fragment, BaseDialogFragment dialogFragment) {
        Log.d(TAG, "mayRequestCamera: ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED) {
                return true;
            }
            if (dialogFragment != null) {
                if (activity.shouldShowRequestPermissionRationale(CAMERA)) {
                    requestCameraPermission(dialogFragment);
                } else {
                    requestCameraPermission(dialogFragment);
                }
            } else if (fragment != null) {
                if (fragment.shouldShowRequestPermissionRationale(CAMERA)) {
                    requestCameraPermission(fragment);
                } else {
                    requestCameraPermission(fragment);
                }
            } else {
                if (activity.shouldShowRequestPermissionRationale(CAMERA)) {
                    requestCameraPermission(activity);
                } else {
                    requestCameraPermission(activity);
                }
            }
        } else {
            return true;
        }
        return false;
    }

    public static void requestCameraPermission(BaseDialogFragment dialogFragment) {
        Log.d(TAG, "requestCameraPermission: ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dialogFragment.requestPermissions(new String[]{CAMERA}, REQUEST_CAMERA);
        }
    }

    public static void requestCameraPermission(BaseActivity activity) {
        Log.d(TAG, "requestCameraPermission: ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(new String[]{CAMERA}, REQUEST_CAMERA);
        }
    }

    public static void requestCameraPermission(BaseFragment fragment) {
        Log.d(TAG, "requestCameraPermission: ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fragment.requestPermissions(new String[]{CAMERA}, REQUEST_CAMERA);
        }
    }

    public static boolean mayRequestStorage(BaseActivity activity, BaseFragment fragment, BaseDialogFragment dialogFragment) {
        Log.d(TAG, "mayRequestStorage: ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((activity.checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                    (activity.checkSelfPermission(READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                return true;
            }
            if (dialogFragment != null) {
                if (activity.shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE) &&
                        activity.shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) {
                    requestReadWriteExternalStoragePermission(dialogFragment);
                } else {
                    requestReadWriteExternalStoragePermission(dialogFragment);
                }
            } else if (fragment != null) {
                if (fragment.shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE) &&
                        fragment.shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) {
                    requestReadWriteExternalStoragePermission(fragment);
                } else {
                    requestReadWriteExternalStoragePermission(fragment);
                }
            }

            else {
                if (activity.shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE) &&
                        activity.shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) {
                    requestReadWriteExternalStoragePermission(activity);
                } else {
                    requestReadWriteExternalStoragePermission(activity);
                }
            }
        } else {
            return true;
        }
        return false;
    }

    public static void requestReadWriteExternalStoragePermission(BaseActivity activity) {
        Log.d(TAG, "requestReadWriteExternalStoragePermission: ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, REQUEST_READ_WRITE_STORAGE);
        }
    }

    public static void requestReadWriteExternalStoragePermission(BaseDialogFragment dialogFragment) {
        Log.d(TAG, "requestReadWriteExternalStoragePermission: ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dialogFragment.requestPermissions(new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, REQUEST_READ_WRITE_STORAGE);
        }
    }

    public static void requestReadWriteExternalStoragePermission(BaseFragment fragment) {
        Log.d(TAG, "requestReadWriteExternalStoragePermission: ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fragment.requestPermissions(new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, REQUEST_READ_WRITE_STORAGE);
        }
    }

    public static boolean checkLocationPermissions(BaseActivity activity) {
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestLocationPermissions(BaseActivity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION);
    }
}
