package com.example.alscon.ball.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.alscon.ball.AdapterElements;
import com.example.alscon.ball.BackgroundAdapter;
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
            R.drawable.first_background, R.drawable.second_background, R.drawable.third_background,
            R.drawable.fourth_background, R.drawable.fifth_background, R.drawable.sixth_background,};
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
                Intent intent = new Intent(BackgroundActivity.this, MainActivity.class);
                preferences = new Preferences(getApplicationContext());
                remove();
                switch (i) {
                    case 0:
                        broadcastData(intent, R.drawable.first_background, R.drawable.ball_eight_first_anim, R.drawable.ball_download_first_anim, R.drawable.ball_first);

                        break;
                    case 1:
                        broadcastData(intent, R.drawable.second_background, R.drawable.ball_eight_second_anim, R.drawable.ball_download_second_anim, R.drawable.ball_second);
                        break;
                    case 2:
                        broadcastData(intent, R.drawable.third_background, R.drawable.ball_eight_third_anim, R.drawable.ball_download_third_anim, R.drawable.ball_third);
                        break;
                    case 3:
                        broadcastData(intent, R.drawable.fourth_background, R.drawable.ball_eight_fourth_anim, R.drawable.ball_download_fourth_anim, R.drawable.ball_fourth);
                        break;
                    case 4:
                        broadcastData(intent, R.drawable.fifth_background, R.drawable.ball_eight_fifth_anim, R.drawable.ball_download_fifth_anim, R.drawable.ball_fifth);
                        break;
                    case 5:
                        broadcastData(intent, R.drawable.sixth_background, R.drawable.ball_eight_sixth_anim, R.drawable.ball_download_sixth_anim, R.drawable.ball_sixth);
                        break;
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }));
    }

    private void broadcastData(Intent intent, int background, int ballEight, int ballDownload, int ball) {
        intent.putExtra("background", background);
        intent.putExtra("background_eight_ball", ballEight);
        intent.putExtra("background_download_ball", ballDownload);
        intent.putExtra("ball", ball);
    }

    private void remove() {
        preferences.remove(PREFERENCES_BALL);
        preferences.remove(PREFERENCES_BACKGROUND);
        preferences.remove(PREFERENCES_DOWNLOAD_BALL);
        preferences.remove(PREFERENCES_EIGHT_BALL);
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
        Intent intent = new Intent(BackgroundActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


    }
}
