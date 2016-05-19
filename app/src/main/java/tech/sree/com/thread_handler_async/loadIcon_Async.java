package tech.sree.com.thread_handler_async;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ananth on 5/19/2016.
 */
public class loadIcon_Async extends AsyncTask<Integer,Integer,Bitmap> {
    Context context;
    loadIcon_Async(Context context){
        this.context = context;
    }
    @Override
    protected void onPreExecute() {
        MainActivity.mprogressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Bitmap doInBackground(Integer... params) {
        Bitmap mBitmap = BitmapFactory.decodeResource(context.getResources(),
                params[0]);
        for(int i =1 ; i <11;i++)
        {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(i*10);
        }

        return mBitmap;
    }

    @Override
    protected void onProgressUpdate(Integer ...values) {
    MainActivity.mprogressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        MainActivity.mprogressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(context,"Image is Loaded SuccessFully !!!",Toast.LENGTH_LONG).show();
        MainActivity.imgView.setImageBitmap(bitmap);

    }
}
