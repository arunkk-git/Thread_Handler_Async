package tech.sree.com.thread_handler_async;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.View;

/**
 * Created by ananth on 5/19/2016.
 */
public class Handler_loadIcon_Message implements Runnable {
    int resId;
    Handler handler;
    Context context;

    Handler_loadIcon_Message(Context context, int resId,Handler handler){
        this.handler = handler;
        this.resId = resId;
        this.context = context;

    }
    @Override
    public void run() {
        Message msg;
        msg = handler.obtainMessage(MainActivity.SET_PROGRESS_VISIBLILITY, View.VISIBLE);
        handler.sendMessage(msg);

        Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(),resId);
        for (int i  =1 ;i<11 ;i++){
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            msg = handler.obtainMessage(MainActivity.SET_PROGRESS_UPDATE, i*10);
            handler.sendMessage(msg);
        }

        msg = handler.obtainMessage(MainActivity.SET_BITMAP, bitmap);
        handler.sendMessage(msg);

        msg = handler.obtainMessage(MainActivity.SET_PROGRESS_VISIBLILITY, View.INVISIBLE);
        handler.sendMessage(msg);


    }
}
