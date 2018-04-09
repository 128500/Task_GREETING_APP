package com.kudin.alex.adras.greeting_app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.Assert.*;

/**
 * Created by homeuser on 07.04.2018.
 */
public class ArgumentsDecipherTest {

    private static final int HOURS = 10;

    enum Zones {
        NY("New York"),
        NY_ZONE_ID("America/New_York"),
        YAKUTSK("Yakutsk"),
        YAKUTSK_ZONE_ID("Asia/Yakutsk"),
        ISLE_OF_MAN("Isle of Man"),
        ISLE_OF_MAN_ZONE_ID("Europe/Isle_of_Man"),
        EAST_SASKATCHEWAN("East-Saskatchewan"),
        EAST_SASKATCHEWAN_ZONE_ID("Canada/East-Saskatchewan"),
        SOME_CITY("Some City"),
        SOME_CITY_UTC_PLUS_6("Some City UTC+6"),
        UTC_PLUS_6_ZONE_ID("Etc/GMT-6"),
        SOME_CITY_GMT_MINUS_3("Some City GMT-3"),
        GMT_MINUS_3_ZONE_ID("Etc/GMT+3");

        private String name;

        Zones(String name) {
            this.name = name;
        }

        public String getName(){
            return name;
        }
    }

    private ArgumentsDecipher ag;

    @Before
    public void setUp() throws Exception {
        ag  = new ArgumentsDecipher();
    }

    @After
    public void tearDown() throws Exception {
        ag = null;
    }

    @Test
    public void getCity() throws Exception {
        ag.setCity(Zones.NY.getName());
        assertTrue(ag.getCity().equals(Zones.NY.getName()));
    }

    @Test
    public void setCity() throws Exception {
        assertTrue(ag.getCity() == null);
        ag.setCity(Zones.SOME_CITY.getName());
        assertTrue(ag.getCity() != null);
    }

    @Test
    public void getHours() throws Exception {
        ag.setHours(HOURS);
        assertTrue(ag.getHours() == HOURS);
    }

    @Test
    public void setHours() throws Exception {
        assertTrue(ag.getHours() == 0);
        ag.setHours(HOURS);
        assertTrue(ag.getHours() == HOURS);
    }

    @Test
    public void decipher_NY() throws Exception {
        String [] args = Zones.NY.getName().split(" ");
        ag.decipher(args);
        int hour = ZonedDateTime.now(ZoneId.of(Zones.NY_ZONE_ID.getName())).getHour();
        assertTrue(ag.getCity().equals(Zones.NY.getName()));
        assertTrue(ag.getHours() == hour);
    }

    @Test
    public void decipher_Yakutsk() throws Exception {
        String [] args = Zones.YAKUTSK.getName().split(" ");
        ag.decipher(args);
        int hour = ZonedDateTime.now(ZoneId.of(Zones.YAKUTSK_ZONE_ID.getName())).getHour();
        assertTrue(ag.getCity().equals(Zones.YAKUTSK.getName()));
        assertTrue(ag.getHours() == hour);
    }

    @Test
    public void decipher_Isle_of_Man() throws Exception {
        String [] args = Zones.ISLE_OF_MAN.getName().split(" ");
        ag.decipher(args);
        int hour = ZonedDateTime.now(ZoneId.of(Zones.ISLE_OF_MAN_ZONE_ID.getName())).getHour();
        assertTrue(ag.getCity().equals(Zones.ISLE_OF_MAN.getName()));
        assertTrue(ag.getHours() == hour);
    }

    @Test
    public void decipher_East_Saskatchewan() throws Exception {
        String [] args = Zones.EAST_SASKATCHEWAN.getName().split(" ");
        ag.decipher(args);
        int hour = ZonedDateTime.now(ZoneId.of(Zones.EAST_SASKATCHEWAN_ZONE_ID.getName())).getHour();
        assertTrue(ag.getCity().equals(Zones.EAST_SASKATCHEWAN.getName()));
        assertTrue(ag.getHours() == hour);
    }

    @Test
    public void decipher_UTC_PLUS_6() throws Exception {
        String [] args = Zones.SOME_CITY_UTC_PLUS_6.getName().split(" ");
        ag.decipher(args);
        int hour = ZonedDateTime.now(ZoneId.of(Zones.UTC_PLUS_6_ZONE_ID.getName())).getHour();
        assertTrue(ag.getCity().equals(Zones.SOME_CITY.getName()));
        assertTrue(ag.getHours() == hour);
    }

    @Test
    public void decipher_GMT_MINUS_3() throws Exception {
        String [] args = Zones.SOME_CITY_GMT_MINUS_3.getName().split(" ");
        ag.decipher(args);
        int hour = ZonedDateTime.now(ZoneId.of(Zones.GMT_MINUS_3_ZONE_ID.getName())).getHour();
        assertTrue(ag.getCity().equals(Zones.SOME_CITY.getName()));
        assertTrue(ag.getHours() == hour);
    }

    @Test
    public void decipher_Some_City() throws Exception {
        String [] args = Zones.SOME_CITY.getName().split(" ");
        ag.decipher(args);
        int hour = LocalDateTime.now().getHour();
        assertTrue(ag.getCity().equals(Zones.SOME_CITY.getName()));
        assertTrue(ag.getHours() == hour);
    }

    @Test (expected = IllegalStateException.class)
    public void decipher_No_Arguments() throws Exception {
        String [] args = new String[0];
        ag.decipher(args);
    }


    @Test
    public void getZoneHour_NY() throws Exception {
        int h = ag.getZoneHour(Zones.NY.getName());
        int hour = ZonedDateTime.now(ZoneId.of(Zones.NY_ZONE_ID.getName())).getHour();
        assertEquals(h ,hour);
    }

    @Test
    public void getZoneHour_Yakutsk() throws Exception {
        int h = ag.getZoneHour(Zones.YAKUTSK.getName());
        int hour = ZonedDateTime.now(ZoneId.of(Zones.YAKUTSK_ZONE_ID.getName())).getHour();
        assertEquals(h ,hour);
    }

    @Test
    public void getLocalHour() throws Exception {
        int h = ag.getLocalHour();
        int hour = LocalDateTime.now().getHour();
        assertEquals(h, hour);
    }

    @Test
    public void checkCity_Isle_Of_Man() throws Exception {
        String[] parts = Zones.ISLE_OF_MAN.getName().split(" ");
        assertTrue(ag.checkParts(parts, Zones.ISLE_OF_MAN_ZONE_ID.getName()));
    }

    @Test
    public void checkCity_Not_Match_of_Isle_Of_Man() throws Exception {
        String[] parts = Zones.ISLE_OF_MAN.getName().split(" ");
        assertFalse(ag.checkParts(parts, Zones.NY_ZONE_ID.getName()));
    }

    @Test
    public void determineCityNameAndHour_NY() throws Exception {

        String[] parts = Zones.NY.getName().split(" ");
        ag.determineCityNameAndHour(parts);
        int hour = ZonedDateTime.now(ZoneId.of(Zones.NY_ZONE_ID.getName())).getHour();
        assertTrue(ag.getCity().equals(Zones.NY.getName()));
        assertTrue(ag.getHours() == hour);
    }

    @Test
    public void determineCityNameAndHour_Isle_of_Man() throws Exception {
        String [] parts = Zones.ISLE_OF_MAN.getName().split(" ");
        ag.determineCityNameAndHour(parts);
        int hour = ZonedDateTime.now(ZoneId.of(Zones.ISLE_OF_MAN_ZONE_ID.getName())).getHour();
        assertTrue(ag.getCity().equals(Zones.ISLE_OF_MAN.getName()));
        assertTrue(ag.getHours() == hour);
    }

    @Test
    public void determineCityNameAndHour_UTC_PLUS_6() throws Exception {
        String [] parts = Zones.SOME_CITY_UTC_PLUS_6.getName().split(" ");
        ag.determineCityNameAndHour(parts);
        int hour = ZonedDateTime.now(ZoneId.of(Zones.UTC_PLUS_6_ZONE_ID.getName())).getHour();
        assertTrue(ag.getCity().equals(Zones.SOME_CITY.getName()));
        assertTrue(ag.getHours() == hour);
    }

    @Test
    public void determineCityNameAndHour_SOME_CITY() throws Exception {
        String [] parts = Zones.SOME_CITY.getName().split(" ");
        ag.determineCityNameAndHour(parts);
        int hour = LocalDateTime.now().getHour();
        assertTrue(ag.getCity().equals(Zones.SOME_CITY.getName()));
        assertTrue(ag.getHours() == hour);
    }
}