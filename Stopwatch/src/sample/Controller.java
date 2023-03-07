package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Controller {

    int flag = 0;
    Timer timer;
    Timer timer2;
    private long millis = 0;
    private int seconds;
    private int minutes;
    private int hours;
    private long startTime = System.currentTimeMillis();
    @FXML
    private Text text;
    @FXML
    private Text realTime;



    @FXML
    void pressButtonStart(ActionEvent event) {
        timer.start();
        timer2.start();
        flag = 1;
        System.out.println("Start");
    }

    @FXML
    void pressButtonStop(ActionEvent event) {
        timer.stop();
        flag = 1 - flag;
        System.out.println("Stop");
    }
    @FXML
    void pressButtonReset(ActionEvent event) {
        startTime = System.currentTimeMillis();
        millis = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        text.setText(second());
    }
    public Controller(){
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(second());
                    }
                });
            }
        };
        timer = new Timer(100, taskPerformer);

        ActionListener taskPerformer2 = new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        LocalDateTime myDateObj = LocalDateTime.now();
                        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
                        String formattedDate = myDateObj.format(myFormatObj);
                        realTime.setText(formattedDate);
                    }
                });
            }
        };
        timer2 = new Timer(1000, taskPerformer2);
    }
    private String second(){
        long time = System.currentTimeMillis();
        millis = time - startTime;
        if(millis >= 1000){
            seconds++;
            millis = 0;
            startTime = time;
            if(seconds >= 60){
                minutes++;
                seconds = 0;
                if(minutes >= 60){
                    hours++;
                    minutes = 0;
                    if(hours >= 24){
                        hours = 0;
                    }
                }
            }
        }
        return (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds) + ":" + millis;
    }

}
