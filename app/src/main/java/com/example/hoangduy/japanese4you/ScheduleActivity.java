package com.example.hoangduy.japanese4you;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.adapters.AlarmAdapter;
import com.example.hoangduy.japanese4you.database.DataAlarm;
import com.example.hoangduy.japanese4you.decorations.GridViewDecoration;
import com.example.hoangduy.japanese4you.models.Time;
import com.example.hoangduy.japanese4you.service.MyService;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity implements AlarmAdapter.InteractionListener {

    public static final String ACTION = "vn.asiantech.training";
    public static final String KEY_BUNDLE = "bundle";
    public static final int RESULT_CODE = 1;
    public static final int RESULT_UPDATE = 2;
    public static final String KEY_ID = "id";
    public static int FLAG = 0;
    public static int id = 0;
    public static int isRunning = 1;
    private Intent mIntent;
    private ArrayList<Time> mTimes;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;
    private Toolbar mToolbar;
    public static final String SCHEDULE_TEXT = "Schedule";
    public static final String KEY_TIME = "time";
    private ImageView mImgClock;
    private ImageView mImgBack;
    private static final String STOPACTION = "stopseft";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolbar();
        mTimes = new ArrayList<>();
        DataAlarm dbHelper = new DataAlarm(this);
        mTimes = dbHelper.getAlarm();
        startService(new Intent(getBaseContext(), MyService.class));
        mLayout = new LinearLayoutManager(this);
        mAdapter = new AlarmAdapter(mTimes, this);
        mRecyclerView.setLayoutManager(mLayout);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new GridViewDecoration(20));
    }

    public void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TextView tvTitle = (TextView) mToolbar.findViewById(R.id.tvTitle);
        mImgClock = (ImageView) mToolbar.findViewById(R.id.imgClock);
        mImgClock.setVisibility(View.VISIBLE);
        mImgBack = (ImageView) mToolbar.findViewById(R.id.imgBack);
        mImgBack.setVisibility(View.VISIBLE);
        tvTitle.setText(SCHEDULE_TEXT);
        setAlarm();
        goBack();
    }

    public void goBack() {
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(STOPACTION);
                sendBroadcast(intent);
                mImgClock.setVisibility(View.INVISIBLE);
                mImgBack.setVisibility(View.INVISIBLE);
                finish();
            }
        });
    }

    public void setAlarm() {
        mImgClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScheduleActivity.this, ClockActivity.class);
                startActivityForResult(intent, RESULT_CODE);
            }
        });
    }

    public ArrayList<Time> getAlarm() {
        DataAlarm dbHelper = new DataAlarm(getApplicationContext());
        return dbHelper.getAlarm();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CODE) {
            Time time = data.getBundleExtra(KEY_BUNDLE).getParcelable(ClockActivity.KEY_TIME);
            mTimes.add(time);
            mAdapter.notifyDataSetChanged();
            mIntent = new Intent(ScheduleActivity.ACTION);
            ArrayList<Time> times = getAlarm();
            setBundle(times);
            sendBroadcast(mIntent);
        } else if (resultCode == RESULT_UPDATE) {
            Time time = data.getBundleExtra(KEY_BUNDLE).getParcelable(ClockActivity.KEY_TIME);
            mTimes.get(id - 1).setHour(time.getHour());
            mTimes.get(id - 1).setMinute(time.getMinute());
            mTimes.get(id - 1).setDayofweek(time.getDayofweek());
            mAdapter.notifyDataSetChanged();
            updateDb(time, isRunning);
            mIntent = new Intent(ScheduleActivity.ACTION);
            ArrayList<Time> times = getAlarm();
            setBundle(times);
            sendBroadcast(mIntent);
        }
    }

    public void setBundle(ArrayList<Time> times) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_TIME, times);
        mIntent.putExtra(ScheduleActivity.KEY_BUNDLE, bundle);
    }


    public void updateDb(Time time, int isRunning) {
        DataAlarm dbHelper = new DataAlarm(getApplicationContext());
        dbHelper.update(time, time.getId(), isRunning);
    }

    @Override
    public void onFragmentInteraction(int position, int flag) {
        DataAlarm dbHelper = new DataAlarm(getApplicationContext());
        dbHelper.updateFlag(position, flag);
        ArrayList<Time> times = getAlarm();
        mIntent = new Intent(ScheduleActivity.ACTION);
        setBundle(times);
        sendBroadcast(mIntent);
    }

    @Override
    public void onEditInteraction(int position) {
        id = position;
        Intent intent = new Intent(this, ClockActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_ID, position);
        intent.putExtra(KEY_BUNDLE, bundle);
        startActivityForResult(intent, RESULT_CODE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
