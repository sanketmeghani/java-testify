package dev.sanket.log4j.layout;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

public class MyLayout extends Layout {
    
    @Override
    public void activateOptions() {
    }

    @Override
    public String format(LoggingEvent event) {
        //Your formatting code goes here
        //You can access all information in event and generate the format you want
        String output = "Your custome log format";
        
        return output;
    }

    @Override
    public boolean ignoresThrowable() {
        return false;
    }
}
