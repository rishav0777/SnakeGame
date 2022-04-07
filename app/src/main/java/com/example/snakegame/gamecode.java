package com.example.snakegame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class gamecode extends View {

    int l=2;
    int sx=0,sy=0;
    int flag1=1,flag2=1;
    Bitmap bg;
    Paint black= new Paint();
    private int cy,cx;
    int x[]=new int[1000000];
    int y[]=new int[1000000];
    Paint ball=new Paint();
    int bx=-100,by;
    Paint up=new Paint();
    Paint down=new Paint();
    Paint right=new Paint();
    Paint left=new Paint();
    Paint rec=new Paint();

    float ri,le,to,bo;

    boolean px=true;
    boolean nx=false;
    boolean py=false;
    boolean ny=false;

    MediaPlayer my,myy;




    public gamecode(Context context) {
        super(context);

        bg=BitmapFactory.decodeResource(getResources(),Color.WHITE);
        black.setColor(Color.RED);
         ball.setColor(Color.BLUE);

        up.setColor(Color.RED);
        down.setColor(Color.RED);
        right.setColor(Color.RED);
        left.setColor(Color.RED);
        rec.setColor(Color.YELLOW);

        x[0]=40;
        y[0]=40;
        x[1]=0;
        y[1]=40;

        bx = (int) Math.floor(Math.random() * ((cx - 40) + 1) + 40);
        by = (int) Math.floor(Math.random() * ((cy - 40) + 1) + 40);
        bx=(bx/40)*40;
        by=(by/40)*40;

        my=MediaPlayer.create(getContext(),R.raw.hjh);
        myy=MediaPlayer.create(getContext(),R.raw.hjh);

    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        cx=canvas.getWidth();
        cy=canvas.getHeight();
        cy-=300;
        canvas.drawRect(0,cy-190,cx,cy+500,rec);
        canvas.drawCircle((cx/2),cy,50,up);
        canvas.drawCircle((cx/2),(cy+250),50,down);
        canvas.drawCircle((cx/2-120),(cy+150),50,left);
        canvas.drawCircle((cx/2+120),(cy+150),50,right);

      // if(bx==(-100)) {
          // bx = (int) Math.floor(Math.random() * (cx - 0 + 20) + 0);
           // by = (int) Math.floor(Math.random() * (cy - 10 + 20) + 10);

            canvas.drawCircle(bx, by, 20, ball);
       // }

       int k=l;
     for (int j=0;j<k;j++) {
         sx = x[j];
         sy = y[j];


         canvas.drawCircle(sx, sy, 20, black);
         if (j == 0 && sx == bx && sy == by) {
             my.start();
             bx = (int) Math.floor(Math.random() * ((cx - 40) + 1) + 40);
             by = (int) Math.floor(Math.random() * ((cy - 240) + 1) + 40);
             bx = (bx / 40) * 40;
             by = (by / 40) * 40;
             canvas.drawCircle(bx, by, 20, ball);

             l++;

         }
     }



       k=l;

        for (int j=(k-1);j>=0;j--) {

            if (j != 0) {
                x[j] = x[j - 1];
                y[j] = y[j - 1];
            }
            else{
                if (px) {
                    x[j] = x[j] + 40;
                    y[j] = y[j];
                }
                if (nx) {
                    x[j] = x[j] - 40;
                    y[j] = y[j];
                }
                if (py) {
                    y[j] = y[j] - 40;
                    x[j] = x[j];
                }
                if (ny) {
                    y[j] = y[j] + 40;
                    x[j] = x[j];
                }

            }

        }
        if(x[0]>cx){
            x[0]=0;
        }
        if(y[0]>cy-200){
            y[0]=0;
        }
        if(x[0]<0){
            x[0]=cx;
        }
        if(y[0]<0){
            y[0]=cy-200;
        }

        for(int j=1;j<k;j++) {
            if (x[0] == x[j] && y[0] == y[j]) {
                myy.start();
                Intent itt=new Intent(getContext(),gameover.class);
                getContext().startActivity(itt);

            }
        }





    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float xx=event.getX();
        float yy=event.getY();

        if(xx>=(cx/2-40) && yy>=(cy-40) && xx<=(cx/2+40) && yy<=(cy+40)) {
            if (px || nx) {
                px = false;
                nx = false;
                py = true;
            }
        }
        if(xx>=(cx/2+80) && yy>=(cy+110) && xx<=(cx/2+160) && yy<=(cy+190)) {
            if (py || ny) {
                py = false;
                ny = false;
                px = true;
            }
        }



      if(xx>=(cx/2-40) && yy>=(cy+210) && xx<=(cx/2+40) && yy<=(cy+290)) {
          if (px || nx) {
              px = false;
              nx = false;
              ny = true;
          }
      }
      if(xx>=(cx/2-160) && yy>=(cy+110) && xx<=(cx/2-80) && yy<=(cy+190)){
           if(py || ny){
                py=false;
                ny=false;
               nx=true;
            }


        }

        return  true;
    }


}
