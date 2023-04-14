package com.fund.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class ClearingUtil {

    public static Date getDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.getTime();
    }

    public static Date setDate(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static Date getNewDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        do {
            cal.add(Calendar.DATE, 1);
        } while (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY);
        return cal.getTime();
    }

    public static BigDecimal getNewNetWorth(BigDecimal netWorth) {
        BigDecimal minRate = new BigDecimal("0.9");
        BigDecimal maxRate = new BigDecimal("1.1");
        BigDecimal minPrice = netWorth.multiply(minRate);
        BigDecimal maxPrice = netWorth.multiply(maxRate);
        BigDecimal random = BigDecimal.valueOf(Math.random());
        return random.multiply(maxPrice.subtract(minPrice)).add(minPrice);
    }

}
