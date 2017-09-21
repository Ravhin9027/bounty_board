package com.wehrhere.myles.bountyboard.infrastructure.constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mwehr on 8/27/17.
 */

public abstract class DateFormatter {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Date parseDate(String date) {
        if(date == null) {
            return null;
        }
        try {
            return DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String dateToString(Date date) {
        return DATE_FORMAT.format(date);
    }

}
