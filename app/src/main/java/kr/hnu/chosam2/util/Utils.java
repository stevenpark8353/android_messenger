package kr.hnu.chosam2.util;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static final String getDate() {
        // Date추가를 위한 코드
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today = sdf.format(new Date());

        return today;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}
