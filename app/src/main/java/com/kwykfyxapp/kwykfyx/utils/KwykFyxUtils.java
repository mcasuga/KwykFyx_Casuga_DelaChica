package com.kwykfyxapp.kwykfyx.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.kwykfyxapp.kwykfyx.MainActivity;
import com.kwykfyxapp.kwykfyx.search.SearchActivity;

public class KwykFyxUtils {
    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void sendToMainActivity(Context context, Class<?> className) {
        context.startActivity(new Intent(context, className));
        Toast.makeText(context, "Invalid Activity Invocation", Toast.LENGTH_SHORT).show();
    }
}
