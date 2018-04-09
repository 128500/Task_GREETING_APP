package com.kudin.alex.adras.greeting_app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * TODO deal with arguments
 * Created by homeuser on 28.03.2018.
 */
public class ApplicationRunner {

    private final static Logger logger = LoggerFactory.getLogger(ApplicationRunner.class);

    public static void main(String[] args) {

        try{
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
        } catch(Exception e){
            logger.error("Error occurred at runtime of the application!", e);
            e.printStackTrace();
        }

    }
}
