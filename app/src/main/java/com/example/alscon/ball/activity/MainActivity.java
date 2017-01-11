package com.example.alscon.ball.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.alscon.ball.Constants;
import com.example.alscon.ball.Preferences;
import com.example.alscon.ball.R;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Constants {
    private ImageView ballAnimations;
    private String[] listAnswer;
    private TextView answer, background;
    private Handler handler;
    private Drawable drawable;
    private RelativeLayout mRelativeLayout;
    private int eightBall, downloadBall, ball, back, textColor;
    private Preferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ballAnimations = (ImageView) findViewById(R.id.ball);
        answer = (TextView) findViewById(R.id.answer);
        background = (TextView) findViewById(R.id.background);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relative_layout);
        mPreferences = new Preferences(getApplicationContext());
        getIntentInformations();
        checkPreferences();
        handler(eightBall, downloadBall, ball, textColor);
        mRelativeLayout.setBackgroundResource(back);
        ballAnimations.setOnClickListener(this);
        background.setOnClickListener(this);
        fonts();

    }

    private void checkPreferences() {
        try {
            if (mPreferences.load(PREFERENCES_EIGHT_BALL) != 0 || mPreferences.load(PREFERENCES_BALL) != 0
                    || mPreferences.load(PREFERENCES_DOWNLOAD_BALL) != 0 || mPreferences.load(PREFERENCES_BACKGROUND) != 0 ||
                    mPreferences.load(PREFERENCES_TEXT_COLOR) != 0) {
                eightBall = mPreferences.load(PREFERENCES_EIGHT_BALL);
                ball = mPreferences.load(PREFERENCES_BALL);
                downloadBall = mPreferences.load(PREFERENCES_DOWNLOAD_BALL);
                back = mPreferences.load(PREFERENCES_BACKGROUND);
                textColor = mPreferences.load(PREFERENCES_TEXT_COLOR);

            }
        } catch (NullPointerException e) {
            back = R.drawable.first_background;
            eightBall = R.drawable.ball_eight_first_anim;
            downloadBall = R.drawable.ball_download_first_anim;
            ball = R.drawable.ball_first;
            textColor = R.color.yellow;
        }
    }

    private void getIntentInformations() {
        Intent intent = getIntent();
        eightBall = intent.getIntExtra("background_eight_ball", 0);
        ball = intent.getIntExtra("ball", 0);
        downloadBall = intent.getIntExtra("background_download_ball", 0);
        back = intent.getIntExtra("background", 0);
        textColor = intent.getIntExtra("text_color", 0);
        save();
    }

    private void save() {
        if (eightBall != 0 || downloadBall != 0 || ball != 0 || back != 0 || textColor != 0) {
            mPreferences.save(eightBall, PREFERENCES_EIGHT_BALL);
            mPreferences.save(ball, PREFERENCES_BALL);
            mPreferences.save(downloadBall, PREFERENCES_DOWNLOAD_BALL);
            mPreferences.save(back, PREFERENCES_BACKGROUND);
            mPreferences.save(textColor, PREFERENCES_TEXT_COLOR);
        }
    }

    public void fonts() {
        Typeface mtypeface = Typeface.createFromAsset(getAssets(), "PFDinDisplayPro-Bold.ttf");
        answer.setTypeface(mtypeface);

    }

    private void handler(final int eight, final int download, final int ball, final int textColor) {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case STATUS_PULSE_ANIMATION:
                        ballAnimations.setImageResource(eight);
                        drawable = ballAnimations.getDrawable();
                        if (drawable instanceof Animatable) {
                            ((Animatable) drawable).start();
                        }
                        break;

                    case STATUS_DOWNLOAD_ANIMATION:
                        background.setEnabled(false);
                        ballAnimations.setEnabled(false);
                        ballAnimations.setImageResource(download);
                        drawable = ballAnimations.getDrawable();
                        if (drawable instanceof Animatable) {
                            ((Animatable) drawable).start();
                        }
                        break;
                    case STATUS_RANDOM:
                        answer.setTextColor(getResources().getColor(textColor));
                        listAnswer = getResources().getStringArray(R.array.answer);
                        int numberAnswer = (int) (Math.random() * 19);
                        answer.setText(listAnswer[numberAnswer]);
                        ballAnimations.setImageResource(ball);
                        background.setEnabled(true);
                        ballAnimations.setEnabled(true);
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
                            handler.sendEmptyMessage(STATUS_DOWNLOAD_ANIMATION);
                            TimeUnit.SECONDS.sleep(3);
                            handler.sendEmptyMessage(STATUS_RANDOM);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
                break;
            case R.id.background:
                Intent intent = new Intent(MainActivity.this, BackgroundActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_up, R.anim.no_changes);
                break;


        }
    }
}

