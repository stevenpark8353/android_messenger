package kr.hnu.chosam2.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static final String getDate() {
        // Date추가를 위한 코드
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today = sdf.format(new Date());

        return today;
    }
}
