package com.globallogic.userMaintainer.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {

    public static String dateNow(){
        return String.valueOf(LocalDate.now());
    }

    public static String dateTimeNow(){
        return String.valueOf(LocalDateTime.now());
    }
}
