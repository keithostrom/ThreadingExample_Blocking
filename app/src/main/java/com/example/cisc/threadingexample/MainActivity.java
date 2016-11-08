package com.example.cisc.threadingexample;



        import android.os.Handler;
        import android.os.Message;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.TextView;

        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.Locale;

public class MainActivity extends ActionBarActivity {
    int clickNumber = 0;

    public class ThreadExampleActivity extends ActionBarActivity {

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                String string = bundle.getString("myKey");
                TextView myTextView =
                        (TextView)findViewById(R.id.myTextView);
                myTextView.setText(string);
            }
        };
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    public void buttonClick(View view) {
//        long endTime = System.currentTimeMillis() + 20*1000;
//
//        while (System.currentTimeMillis() < endTime) {
//            synchronized (this) {
//                try {
//                    wait(endTime - System.currentTimeMillis());
//                } catch (Exception e) {
//                }
//            }
//        }
//        TextView myTextView =
//                (TextView)findViewById(R.id.myTextView);
//        myTextView.setText("Button Pressed");
//    }
public void buttonClick(View view)
{
    Runnable runnable = new Runnable() {
        public void run() {
            Message msg = handler.obtainMessage();
            Bundle bundle = new Bundle();
            SimpleDateFormat dateformat =
                    new SimpleDateFormat("HH:mm:ss MM/dd/yyyy",
                            Locale.US);
            String dateString =
                    dateformat.format(new Date());
            bundle.putString("myKey", dateString);
            msg.setData(bundle);
            handler.sendMessage(msg);
        }
    };
    Thread mythread = new Thread(runnable);
    mythread.start();
        } // End buttonClick
    } // End ThreadExampleActivity
} // End MainActivity
