package com.kudin.alex.adras.greeting_app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Created by homeuser on 29.03.2018.
 */
public class Message {

    private static final Logger logger = LoggerFactory.getLogger(Message.class);

    /**
     * Returns the message according to given time
     * @param hour time of the day (in hours)
     * @param bundle the bundle that determines the language of the message
     * @return the message that must be shown
     */
    String getMessage(ResourceBundle bundle, int hour){
        if(hour > 23 || hour < 0) {
            IllegalStateException e   = new IllegalStateException("Number of hours must not be more than '23' or below '0'");
            logger.error("Number of hours is {}", hour, e);
            throw e;
        }
        String greeting = "";
        if (6 <= hour && hour < 9)greeting = bundle.getString("good.morning");
        if (9 <= hour && hour < 19)greeting = bundle.getString("good.day");
        if (19 <= hour && hour < 23)greeting = bundle.getString("good.evening");
        if ((0 <= hour &&  hour < 6) || hour == 23)greeting = bundle.getString("good.night");

        return greeting;
    }

}
