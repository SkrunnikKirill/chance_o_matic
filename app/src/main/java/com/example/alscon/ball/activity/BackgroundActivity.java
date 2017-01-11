package com.example.alscon.ball.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.alscon.ball.AdapterElements;
import com.example.alscon.ball.Preferences;
import com.example.alscon.ball.adapters.BackgroundAdapter;
import com.example.alscon.ball.Constants;
import com.example.alscon.ball.R;
import com.example.alscon.ball.RecyclerItemClickListener;

import java.util.ArrayList;

/**
 * Created by Alscon on 04-Jan-17.
 */

public class BackgroundActivity extends Activity implements Constants {
    private final String recyclerViewTitleText[] = {
            "Android", "RecyclerView", "Android List", "GridView", "ListView", "Tutorial"
    };
    private final int recyclerViewImages[] = {
            R.drawable.first_background_eight, R.drawable.second_background_eight, R.drawable.third_background_eight,
            R.drawable.fourth_background_eight, R.drawable.fifth_background_eight, R.drawable.sixth_background_eight};
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);
        initRecyclerViews();
    }

    private void initRecyclerViews() {
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<AdapterElements> av = prepareData();
        BackgroundAdapter mAdapter = new BackgroundAdapter(getApplicationContext(), av);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

                preferences = new Preferences(getApplicationContext());
                remove();
                view.setBackgroundColor(Color.GREEN);
                switch (i) {
                    case 0:
                        broadcastData(R.drawable.first_background, R.drawable.ball_eight_first_anim, R.drawable.ball_download_first_anim, R.drawable.ball_first, R.color.yellow);
                        break;
                    case 1:
                        broadcastData(R.drawable.second_background, R.drawable.ball_eight_second_anim, R.drawable.ball_download_second_anim, R.drawable.ball_second, R.color.orange);
                        break;
                    case 2:
                        broadcastData(R.drawable.third_background, R.drawable.ball_eight_third_anim, R.drawable.ball_download_third_anim, R.drawable.ball_third, R.color.blue);
                        break;
                    case 3:
                        broadcastData(R.drawable.fourth_background, R.drawable.ball_eight_fourth_anim, R.drawable.ball_download_fourth_anim, R.drawable.ball_fourth, R.color.red);
                        break;
                    case 4:
                        broadcastData(R.drawable.fifth_background, R.drawable.ball_eight_fifth_anim, R.drawable.ball_download_fifth_anim, R.drawable.ball_fifth, R.color.dark_blue);
                        break;
                    case 5:
                        broadcastData(R.drawable.sixth_background, R.drawable.ball_eight_sixth_anim, R.drawable.ball_download_sixth_anim, R.drawable.ball_sixth, R.color.pink);
                        break;
                }

            }
        }));
    }

    private void broadcastData(int background, int ballEight, int ballDownload, int ball, int color) {
        Intent intent = new Intent(BackgroundActivity.this, MainActivity.class);
        intent.putExtra("background", background);
        intent.putExtra("background_eight_ball", ballEight);
        intent.putExtra("background_download_ball", ballDownload);
        intent.putExtra("ball", ball);
        intent.putExtra("text_color", color);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    private void remove() {
        preferences.remove(PREFERENCES_BALL);
        preferences.remove(PREFERENCES_BACKGROUND);
        preferences.remove(PREFERENCES_DOWNLOAD_BALL);
        preferences.remove(PREFERENCES_EIGHT_BALL);
        preferences.remove(PREFERENCES_TEXT_COLOR);
    }

    private ArrayList<AdapterElements> prepareData() {

        ArrayList<AdapterElements> av = new ArrayList<>();
        for (int i = 0; i < recyclerViewTitleText.length; i++) {
            AdapterElements mAndroidVersion = new AdapterElements();
            mAndroidVersion.setTitle(recyclerViewTitleText[i]);
            mAndroidVersion.setImage(recyclerViewImages[i]);
            av.add(mAndroidVersion);
        }
        return av;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


    }
}
