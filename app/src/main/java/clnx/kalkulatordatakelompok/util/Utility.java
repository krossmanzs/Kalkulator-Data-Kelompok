package clnx.kalkulatordatakelompok.util;

import android.content.Context;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Utility {
    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * This method checks whether the string is number or not.
     *
     * @param string A String value. Which we need to convert into int;
     * @return A boolean value to represent the string is number or not.
     */
    public static boolean isNumberFormat(String string){
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method remove comma and zero after comma
     *
     * @param number A decimal number. Which we need to reformat
     * @return A reformatted of decimal number
     */
    public static String reformatDecimalNum(float number, boolean showZero) {
        if ((number == 0f) && !showZero ) {
          return "-";
        } else if(number == (long) number)
            return String.format(Locale.getDefault(),"%d",(long)number);
        else
            return String.format(Locale.getDefault(),"%s",number);
    }

    /**
     * This method get the max number from ArrayList of float.
     * It uses ascending sort algorithm to get the max number
     *
     * @param arrays A decimal number. Which we need to sort
     * @return A float type data that represent the max number
     */
    public static float getMax(ArrayList<Float> arrays) {
        Collections.sort(arrays);
        return arrays.get(arrays.size() - 1);
    }
}
