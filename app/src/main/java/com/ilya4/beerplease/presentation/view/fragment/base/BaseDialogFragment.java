package com.ilya4.beerplease.presentation.view.fragment.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class BaseDialogFragment extends DialogFragment {
    private static final String TAG = "BaseFragment";

    public static final String FRAGMENT_ID = "FRAGMENT_ID";

    private String onDismissDialogListenerKey;

    private int fragmentId = -1;

    public BaseDialogFragment newInstance(Bundle args) {
        BaseDialogFragment fragment = this;
        if (args != null) {
            fragmentId = args.getInt(FRAGMENT_ID);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
//    public void showDevDialog(String msg) {
//        if (getActivity() != null) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            builder.setTitle(R.string.dialog_dev_title)
//                    .setMessage(msg)
//                    .setCancelable(false)
//                    .setNegativeButton(R.string.dialog_dev_ok_btn, (dialog, which) -> {
//                        dialog.dismiss();
//                    });
//            AlertDialog dialog = builder.create();
//            dialog.show();
//        }
//    }
//
//    public void showChildErrorDialog() {
//        if (getActivity() != null)
//            ChildErrorDialog.newInstance((BaseActivity) getActivity());
//    }
//
//    public void showParentErrorDialog() {
//        if (getActivity() != null) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            builder.setTitle(R.string.parent_dialog_error_title)
//                    .setMessage(R.string.parent_dialog_error_message)
//                    .setCancelable(false)
//                    .setNegativeButton(R.string.parent_dialog_error_ok, (dialog, which) -> {
//                        dialog.dismiss();
//                    });
//            AlertDialog dialog = builder.create();
//            dialog.show();
//        }
//    }
//
//    public void showNeedUpdateDialog() {
//        if (getActivity() != null) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            builder.setTitle(R.string.need_update_dialog_title)
//                    .setMessage(R.string.need_update_dialog_subtitle)
//                    .setCancelable(true)
//                    .setPositiveButton(R.string.need_update_dialog_button, (dialog, which) -> {
//                        final String appPackageName = getActivity().getPackageName();
//                        try {
//                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
//                        } catch (android.content.ActivityNotFoundException anfe) {
//                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
//                        }
//                        dialog.dismiss();
//                    });
//            AlertDialog dialog = builder.create();
//            dialog.show();
//        }
//    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
//        dismissDialog(null);
    }

    public void showMainActivityAfterChildBlocked() {

    }

//    protected void dismissDialog(OnDismissDialogListener.RequestAction requestAction) {
//        dismissAllowingStateLoss();
//        if (onDismissDialogListener != null)
//            onDismissDialogListener.onDismissDialog(onDismissDialogListenerKey, requestAction);
//    }
//
//    protected void dismissDialog(Bundle bundle) {
//        dismiss();
//        if (onDismissDialogListener != null)
//            onDismissDialogListener.onDismissDialog(onDismissDialogListenerKey, bundle);
//    }
//
//    protected void dismissDialog(OnDismissDialogListener.RequestAction requestAction, WishCardDto wishCardDto) {
//        dismiss();
//        if (onDismissDialogListener != null)
//            onDismissDialogListener.onDismissDialog(onDismissDialogListenerKey, requestAction, wishCardDto);
//    }
//
//    @Override
//    public void setOnDismissDialogListener(OnDismissDialogListener listener, String key) {
//        onDismissDialogListener = listener;
//        onDismissDialogListenerKey = key;
//    }
}
