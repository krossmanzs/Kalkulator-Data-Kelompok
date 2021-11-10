package clnx.kalkulatordatakelompok.ui.helper;

import android.view.View;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import clnx.kalkulatordatakelompok.util.Utility;

public class ShowMessage {
    public static void snackBar(View view, String message, int color, float marginHeight) {
        Snackbar snackbar = Snackbar.make(view, message, BaseTransientBottomBar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        snackbarView.setTranslationY(-(Utility.convertDpToPixel(marginHeight,view.getContext())));
        snackbarView.setBackgroundResource(color);
        snackbar.show();
    }
}
