package com.example.homeworkgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.Random;

class DrawThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private volatile boolean running = true;
    float x = - 100000, y = - 100000, r = 0;

    void set(float x, float y, float r){
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    public void requestStop() {
        running = false;
    }
    Random random = new Random();
    @Override
    public void run() {

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

        int colors[] = {Color.BLUE, Color.WHITE, Color.MAGENTA, Color.YELLOW,
                Color.CYAN};
        int c = random.nextInt(colors.length);


        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    c = random.nextInt(colors.length);
                    canvas.drawColor((colors[c]));
                    canvas.drawCircle(x,y,r,paint);
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
                r+=100;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
