package com.example.todo.utils;

import com.example.todo.common.Constants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalenderUtils {

    public static Calendar getDate(String date1) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");

        Date date = null;
        try {
            date = sdf.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        return cal;
    }



    public static boolean isToday(Date date){
        Calendar today = Calendar.getInstance();
        Calendar specifiedDate  = Calendar.getInstance();
        specifiedDate.setTime(date);

        return today.get(Calendar.DAY_OF_MONTH) == specifiedDate.get(Calendar.DAY_OF_MONTH)
                &&  today.get(Calendar.MONTH) == specifiedDate.get(Calendar.MONTH)
                &&  today.get(Calendar.YEAR) == specifiedDate.get(Calendar.YEAR);
    }






    public static String convertStringDateToAnotherStringDate(String stringdate){
        try {
            Date date = new SimpleDateFormat(Constants.SERVER_DATE_FORMAT).parse(stringdate);
            String returndate = new SimpleDateFormat(Constants.REQUIRED_DATE_FORMAT).format(date);
            return returndate;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }



}
