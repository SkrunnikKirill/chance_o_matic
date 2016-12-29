package com.example.alscon.ball;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Constants {
    private ImageView ballAnimations;
    private String[] listAnswer;
    private TextView answer;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ballAnimations = (ImageView) findViewById(R.id.ball);
        answer = (TextView) findViewById(R.id.answer);
        handler();
        ballAnimations.setOnClickListener(this);
    }

    private void handler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case STATUS_PULSE_ANIMATION:
                        ballAnimations.setImageDrawable(getResources().getDrawable(R.drawable.ball_download_sixth));
                        break;
                    case STATUS_EIGHT_ANIMATION:
                        ballAnimations.setImageDrawable(getResources().getDrawable(R.drawable.download_ball));
                        Drawable drawable2 = ballAnimations.getDrawable();
                        if (drawable2 instanceof Animatable) {
                            ((Animatable) drawable2).start();
                        }
                        break;
                    case STATUS_DOWNLOAD_ANIMATION:
                        break;
                    case STATUS_RANDOM:
                        listAnswer = getResources().getStringArray(R.array.answer);
                        int numberAnswer = (int) (Math.random() * 19);
                        answer.setText(listAnswer[numberAnswer]);
                        ballAnimations.setImageDrawable(getResources().getDrawable(R.drawable.ball_sixth));
                        break;
                    case STATUS_TEXT:
                        answer.setText("");
                        break;

                }


            }
        };
        handler.sendEmptyMessage(STATUS_PULSE_ANIMATION);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ball:
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            handler.sendEmptyMessage(STATUS_TEXT);
                            handler.sendEmptyMessage(STATUS_EIGHT_ANIMATION);
                            TimeUnit.SECONDS.sleep(3);
                            handler.sendEmptyMessage(STATUS_RANDOM);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();

                break;


        }
    }
}
