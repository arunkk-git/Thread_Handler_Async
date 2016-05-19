package tech.sree.com.thread_handler_async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    public static ImageView imgView;

    Button load,other;
    Bitmap mBitmap ;
    public static ProgressBar mprogressBar;
    public static final Handler handler =  new Handler();

    public static final int SET_PROGRESS_VISIBLILITY = 1 ;
    public static final int SET_IMAGE = 2 ;
    public static final int SET_PROGRESS_UPDATE = 3 ;
    public static final int SET_BITMAP = 4 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgView = (ImageView) findViewById(R.id.imageView);
        load = (Button) findViewById(R.id.imagebtn);
        other = (Button) findViewById(R.id.other);
        EditText editText = (EditText) findViewById(R.id.editText);
        mprogressBar = (ProgressBar) findViewById(R.id.progressBar);
        Log.d("ARUN", "UI thread : " + Thread.currentThread().getId() + "  Name : " + Thread.currentThread().getName());
    }

    public void loadImage(View V)  {
        // loadIcon(); // simple thread it will crash due to UI toolkit is accessed in non UI thread
        //new loadIcon_Async(this).execute(R.drawable.screen2); //Async Task
        //  new Thread(new Handler_loadIcon_post(this,R.drawable.screen2)).start();
//Handler with messages
        new Thread(new Handler_loadIcon_Message(this,R.drawable.screen2,handlerMsg)).start();
    }
    public void loadIcon(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(25000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("ARUN","Runnable thread : "+Thread.currentThread().getId()+"  Name : " +Thread.currentThread().getName());
                mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.screen2);
//                imgView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        imgView.setImageBitmap(mBitmap);
//                    }
//                });
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imgView.setImageBitmap(mBitmap);
                    }
                });
            }
        }).start();

    }
    public void clickOther(View V){
        Toast.makeText(getApplicationContext(),"I'm Working....",Toast.LENGTH_LONG).show();
    }

    public Handler handlerMsg = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SET_PROGRESS_VISIBLILITY:
                    mprogressBar.setVisibility((Integer) msg.obj);
                    break;
                case SET_BITMAP:
                    imgView.setImageBitmap((Bitmap)msg.obj);
                    break;

                case SET_PROGRESS_UPDATE:
                    mprogressBar.setProgress((Integer) msg.obj);
                    break;

                default:
                    super.handleMessage(msg);
            }
        }
    };
}
