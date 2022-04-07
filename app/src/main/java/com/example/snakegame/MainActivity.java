package com.example.snakegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    gamecode gc;
    final int ti=280;
    Handler hd=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gc=new gamecode(this);
        setContentView(gc);
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                hd.post(new Runnable() {
                    @Override
                    public void run() {
                         gc.invalidate();
                    }
                });
            }
        },0,ti);
    }

}