package tech.sree.com.thread_handler_async;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;


/**
 * Created by ananth on 5/19/2016.
 */
public class Handler_loadIcon_post implements Runnable {
    Context context;
    Handler handler =  MainActivity.handler;
    ProgressBar progressBar = MainActivity.mprogressBar;
    ImageView imageView = MainActivity.imgView;
    int imageId;
    Handler_loadIcon_post(Context context, int ID){
        this.context =  context;
        this.imageId =  ID ;
    }
    @Override
    public void run() {

        handler.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(View.VISIBLE);
            }
        });

        final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),imageId);

        for(int i  = 1 ; i<11 ;i++) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final int ii = i;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    progressBar.setProgress(ii * 10);
                }
            });
        }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                }
            });

            handler.post(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });
        }
}
