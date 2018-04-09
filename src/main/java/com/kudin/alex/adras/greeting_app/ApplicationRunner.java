package com.kudin.alex.adras.greeting_app;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * TODO deal with arguments
 * Created by homeuser on 28.03.2018.
 */
public class ApplicationRunner {

    public static void main(String[] args) {

        Message message = new Message();
        MyControl control = new MyControl();
        ArgumentsDecipher ag = new ArgumentsDecipher();
        ag.decipher(args);

        System.out.println("Hours = " + ag.getHours());
        System.out.println("City = " + ag.getCity());

        Locale locale = Locale.getDefault();

        ResourceBundle currentLocale = ResourceBundle.getBundle("bundles.GreetingBundle", locale, control);

        String mes = message.getMessage(currentLocale, ag.getHours()) + " " + ag.getCity() ;

        EventQueue.invokeLater(() -> new MessageFrame(null, mes));
    }
}
