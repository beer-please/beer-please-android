package com.ilya4.beerplease.presentation.view.component;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

public class EditTextDebounce {

    private final WeakReference<EditText> editTextWeakReference;
    private final Handler debounceHandler;
    private final TextWatcher textWatcher;
    private DebounceCallback debounceCallback;
    private Runnable debounceWorker;
    private int delayMillis;
    private String temp = "";

    private boolean isRunning = false;
    private boolean isDeleting = false;

    /**
     * Private constructor of EditTextDebounce. Called in both methods of creating.
     *
     * @param editText This EditText for listening when user finish typing a text.
     * @see EditTextDebounce
     * @see EditText
     */
    private EditTextDebounce(@NonNull EditText editText) {
        this.debounceHandler = new Handler(Looper.getMainLooper());
        this.debounceWorker = new DebounceRunnable("", null);
        this.delayMillis = 300;
        this.textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //unused
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //unused
            }

            @Override
            public void afterTextChanged(Editable s) {
                temp = s.toString();
                debounceHandler.removeCallbacks(debounceWorker);
                debounceWorker = new DebounceRunnable(s.toString(), debounceCallback);
                debounceHandler.postDelayed(debounceWorker, delayMillis);
            }
        };
        this.editTextWeakReference = new WeakReference<>(editText);
        EditText editTextInternal = this.editTextWeakReference.get();
        if (editTextInternal != null) {
            editTextInternal.addTextChangedListener(textWatcher);
        }
    }


    /**
     * This constructor takes text MASK
     * @param editText EditText for listening
     * @param mask - editText mask
     */
    private EditTextDebounce(@NonNull EditText editText, Object mask) {
        this.debounceHandler = new Handler(Looper.getMainLooper());
        this.debounceWorker = new DebounceRunnable("", null);
        this.delayMillis = 300;
        this.textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                isDeleting = after < count;
                //unused
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //unused
            }

            @Override
            public void afterTextChanged(Editable s) {
                //masked editable
                if (isRunning) {
                    return;
                }
                isRunning = true;
                //////////////////
                temp = s.toString();
                debounceHandler.removeCallbacks(debounceWorker);
                debounceWorker = new DebounceRunnable(s.toString(), debounceCallback);
                debounceHandler.postDelayed(debounceWorker, delayMillis);
                isRunning = false;
            }
        };
        this.editTextWeakReference = new WeakReference<>(editText);
        EditText editTextInternal = this.editTextWeakReference.get();
        if (editTextInternal != null) {
            editTextInternal.addTextChangedListener(textWatcher);
        }
    }

    /**
     * Method to create the object of EditTextDebounce. The delay in milliseconds is set by default.
     *
     * @param editText EditText for listening
     * @return object of an EditTextDebounce class
     * @see EditTextDebounce
     * @see EditText
     */
    public static EditTextDebounce create(@NonNull EditText editText) {
        return new EditTextDebounce(editText);
    }

    /**
     * Method to create the object of EditTextDebounce. The specified delay in milliseconds.
     *
     * @param editText    EditText for listening
     * @param delayMillis The delay (in milliseconds) until the Callback will be executed
     * @return object of an EditTextDebounce class
     * @see EditTextDebounce
     * @see EditText
     */
    public static EditTextDebounce create(@NonNull EditText editText, int delayMillis) {
        EditTextDebounce editTextDebounce = new EditTextDebounce(editText);
        editTextDebounce.setDelayMillis(delayMillis);
        return editTextDebounce;
    }

    /**
     * The method binds a callback to track changes in EditText. The delay in milliseconds is set by default.
     *
     * @param debounceCallback an object of an interface DebounceCallback
     * @see EditText
     * @see DebounceCallback
     */
    public void watch(@Nullable DebounceCallback debounceCallback) {
        this.debounceCallback = debounceCallback;
    }

    /**
     * The method binds a callback to track changes in EditText. The specified delay in milliseconds.
     *
     * @param debounceCallback an object of an interface DebounceCallback
     * @param delayMillis      The delay (in milliseconds) until the Callback will be executed
     * @see EditText
     * @see DebounceCallback
     */
    public void watch(@Nullable DebounceCallback debounceCallback, int delayMillis) {
        this.debounceCallback = debounceCallback;
        this.delayMillis = delayMillis;
    }

    /**
     * This method ends the listening of changes in EditText.
     *
     * @see EditText
     */
    public void unwatch() {
        if (editTextWeakReference != null) {
            EditText editText = editTextWeakReference.get();
            if (editText != null) {
                editText.removeTextChangedListener(textWatcher);
                editTextWeakReference.clear();
                debounceHandler.removeCallbacks(debounceWorker);
            }
        }
    }

    /**
     * Sets specified delay in milliseconds.
     *
     * @param delayMillis milliseconds
     */
    private void setDelayMillis(int delayMillis) {
        this.delayMillis = delayMillis;
    }

    /**
     * This interface is used to monitor the changes in EditText introduced by the user and the method starts onFinihsed to perform further work
     * after delay.
     */
    public interface DebounceCallback {
        /**
         * This method start when delay is finish.
         *
         * @param result The last text string from EditText
         */
        void onFinished(@NonNull String result);
    }

    /**
     * This class running method onFinished from DebounceCallback.
     *
     * @see DebounceCallback
     */
    private static class DebounceRunnable implements Runnable {

        private final String result;
        private final DebounceCallback debounceCallback;

        /**
         * Instantiates a new  object of a DebounceRunnable class.
         *
         * @param result           Sets String result after delay is finish
         * @param debounceCallback an object of an interface DebounceCallback
         * @see DebounceCallback
         */
        DebounceRunnable(String result, DebounceCallback debounceCallback) {
            this.result = result;
            this.debounceCallback = debounceCallback;
        }

        @Override
        public void run() {
            if (debounceCallback != null) {
                debounceCallback.onFinished(result);
            }
        }
    }
}