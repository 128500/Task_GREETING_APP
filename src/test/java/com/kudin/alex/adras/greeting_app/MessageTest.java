package com.kudin.alex.adras.greeting_app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by homeuser on 08.04.2018.
 */
public class MessageTest {

    private static final ResourceBundle RU = ResourceBundle.getBundle("bundles.GreetingBundle", new Locale("ru", "RU"), new MyControl());
    private static final ResourceBundle EN = ResourceBundle.getBundle("bundles.GreetingBundle", new Locale("en", "US"));

    private Message message;

    @Before
    public void setUp() throws Exception {
        message  = new Message();
    }

    @After
    public void tearDown() throws Exception {
        message = null;
    }

    @Test
    public void getMessage_Morning() throws Exception {
        int morning_hours = (int)Math.round(Math.random() * 2) + 6;
        String ru_mes = message.getMessage(RU, morning_hours);
        assertTrue(ru_mes.equals("Доброе утро"));

        String en_mes = message.getMessage(EN, morning_hours);
        assertTrue(en_mes.equals("Good morning"));
    }

    @Test
    public void getMessage_Day() throws Exception {
        int day_hours = (int)Math.round(Math.random() * 9) + 9;
        String ru_mes = message.getMessage(RU, day_hours);
        assertTrue(ru_mes.equals("Добрый день"));

        String en_mes = message.getMessage(EN, day_hours);
        assertTrue(en_mes.equals("Good day"));
    }


    @Test
    public void getMessage_Evening() throws Exception {
        int eve_hours = (int)Math.round(Math.random() * 3) + 19;
        String ru_mes = message.getMessage(RU, eve_hours);
        assertTrue(ru_mes.equals("Добрый вечер"));

        String en_mes = message.getMessage(EN, eve_hours);
        assertTrue(en_mes.equals("Good evening"));
    }

    @Test
    public void getMessage_Night() throws Exception {
        int night_hours = (int)Math.round(Math.random() * 5);

        String ru_mes = message.getMessage(RU, night_hours);
        assertTrue(ru_mes.equals("Доброй ночи"));

        ru_mes = message.getMessage(RU, 23);
        assertTrue(ru_mes.equals("Доброй ночи"));

        String en_mes = message.getMessage(EN, night_hours);
        assertTrue(en_mes.equals("Good night"));

        en_mes = message.getMessage(EN, 23);
        assertTrue(en_mes.equals("Good night"));
    }
}