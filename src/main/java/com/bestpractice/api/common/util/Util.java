package com.bestpractice.api.common.util;

import java.util.Calendar;
import java.util.Date;

public class Util {

    public static Date calculateDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        date = calendar.getTime();

        return date;
    }
}
