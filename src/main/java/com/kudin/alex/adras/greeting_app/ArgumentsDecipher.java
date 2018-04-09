package com.kudin.alex.adras.greeting_app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by homeuser on 02.04.2018.
 */
public class ArgumentsDecipher {

    private static final Logger logger = LoggerFactory.getLogger(ArgumentsDecipher.class);

    private String city;

    private int hours;

    String getCity() {
        return city;
    }

    void setCity(String city) {
        this.city = city;
    }

    int getHours() {
        return hours;
    }

    void setHours(int hours) {
        this.hours = hours;
    }

    void decipher(final String[] args) {
        if(args.length == 0) {
            IllegalStateException e  = new IllegalStateException("Command line has no arguments! \n" +
                    "Must contain: [name of a city in English] - required argument [time zone] - optional argument");
            logger.error("", e);
            throw e;
        }

        if(args.length == 1){
            setHours(getZoneHour(args[0]));
            setCity(args[0]);
        }

        if(args.length >= 2){
            determineCityNameAndHour(args);
        }
    }

    /**
     * Determines zoned time in hours according to the given city.
     * If there is no such city in the list of zone ids returns the
     * amount of hours according to the current location.
     * @param city the given city
     * @return the amount of hours
     */
    int getZoneHour(final String city){
        /*get available zone ids and put them into ArrayList*/
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        ArrayList<String> zoneList = new ArrayList<>(zoneIds);

        String[] parts = city.split(" ");

        String zoneId = "";

        /*check if the given name of the city is in the list of zone ids*/
        for(String id : zoneList){
            if(checkParts(parts, id)) {
                zoneId = id;
                System.out.println("ZoneId = " + id);
                break;
            }
        }

        if(!zoneId.equals("")){
            ZoneId zone = ZoneId.of(zoneId);
            ZonedDateTime zonedDateTime = ZonedDateTime.now(zone);
            return zonedDateTime.getHour();
        }
        else return getLocalHour();
    }

    /**
     * Determines local time in hours
     * @return the amount of hours
     */
    int getLocalHour(){
        return LocalDateTime.now().getHour();
    }

    /**
     * Checks if the given parts match any of zone ids (in the list of zonesIds)
     * @param parts the name of the city (breaking into pieces if it consists of more than one word)
     * @param zoneId the name of zone id
     * @return true if zone id contains the name of the city, else - false
     */
    boolean checkParts(final String [] parts, final String zoneId){
        StringBuilder name = new StringBuilder();
        for(int i =0; i < parts.length; i++){
            if(i !=0) name.append("_");
            name.append(parts[i]);
        }

        String[] splitting = zoneId.split("/");
        return splitting[splitting.length - 1].matches(name.toString());
    }

    /**
     * Determines name of the city and current amount of hours that is in it at
     * the moment. Sets the determined values to the respected fields of the class.
     * @param args arguments of the command line
     */
    void determineCityNameAndHour(final String[] args){
        String part = args[args.length - 1];
        if(part.matches("(UTC|GMT)[-+]\\d{1,2}(:\\d{2})?")){

           ZoneId zoneId = ZoneId.of(part);
           System.out.println(zoneId.toString());
           ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
           setHours(zonedDateTime.getHour());


            StringBuilder cityName = new StringBuilder();
            for(int i = 0; i < args.length - 1; i++){
               if(i != 0)cityName.append(" ");
               cityName.append(args[i]);
            }

            setCity(cityName.toString());
        }

        else{
            StringBuilder cityName = new StringBuilder();
            for(int i = 0; i < args.length; i++){
                if(i != 0)cityName.append(" ");
                cityName.append(args[i]);
            }

            setCity(cityName.toString());

            setHours(getZoneHour(cityName.toString()));
        }
    }
}
