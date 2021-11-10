package clnx.kalkulatordatakelompok.ui.helper;

import android.content.Context;
import android.view.View;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import clnx.kalkulatordatakelompok.R;
import clnx.kalkulatordatakelompok.util.Utility;

public class ErrorMsg {
    public static void snackBar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, BaseTransientBottomBar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        snackbarView.setTranslationY(-(Utility.convertDpToPixel(124,view.getContext())));
        snackbarView.setBackgroundResource(R.color.red);
        snackbar.show();
    }
}
