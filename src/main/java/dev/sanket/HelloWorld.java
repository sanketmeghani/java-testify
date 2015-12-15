package dev.sanket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;



public class HelloWorld
{
    public static void main(String args[]) throws Exception
    {
        DateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.ENGLISH);
        System.out.println(dateParser.parse("2015-10-26T19:26:14+05:30"));
        System.out.println(dateParser.format(Calendar.getInstance().getTime()));
    }
}

