package com.kudin.alex.adras.greeting_app;

import javax.swing.*;
import java.awt.*;

/**
 * Created by homeuser on 29.03.2018.
 */
public class MessageFrame extends JDialog {

    MessageFrame(){};

    MessageFrame(Frame owner, String message){
        super(owner, "Greeting message", true);
        try{
            JTextField messageField = new JTextField(message);
            messageField.setFont(new Font("Times New Roman", 1, 60));
            messageField.setForeground(Color.DARK_GRAY);
            messageField.setBackground(Color.GREEN);
            messageField.setEditable(false);

            this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            this.add(messageField);
            this.pack();
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            this.setAlwaysOnTop(true);
            this.setVisible(true);

        } catch (HeadlessException e){
            e.printStackTrace();
        }

    }
}
